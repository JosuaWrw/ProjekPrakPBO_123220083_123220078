/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perkebunan.controller;

import com.mycompany.perkebunan.model.DaoBuah;
import com.mycompany.perkebunan.model.Buah;
import com.mycompany.perkebunan.view.PanenBuah;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andre
 */
public class BuahController {
    Double id_target = 0d;
    DaoBuah dao = new DaoBuah();
    SimpleDateFormat originalFormat = new SimpleDateFormat("dd/M/yyyy");
    
    public void setModeUpdate(PanenBuah view){
        view.btnCancel.setVisible(true);
        view.btnHapus.setVisible(true);
        view.btnTambah.setText("Update Data");
    }
    public void setModeTambah(PanenBuah view){
        view.btnCancel.setVisible(false);
        view.btnHapus.setVisible(false);
        view.btnTambah.setText("Tambah Data");
        id_target = 0d;
        
        view.namaBuah.setText("");
        view.beratBuah.setText("");
        view.tanggalTanam.setText("");
        view.tanggalPanen.setText("");
        // clear row terpiih di tabel
        view.tabelBuah.clearSelection();
    }
    public void reloadIsiTable(PanenBuah view){
        DefaultTableModel tabel = (DefaultTableModel) view.tabelBuah.getModel();
        tabel.setRowCount(0);
        int i = 1;
        for(Buah a : dao.getData()){
            tabel.addRow(new Object[] {a.getId(), a.getNama(), a.getBerat(), a.getTanggalTanam(), a.getTanggalPanen()});
            i++;
        }
    }
    
    public void eventKlikRowTable(PanenBuah view, java.awt.event.MouseEvent evt){
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        
        Buah data = new  Buah();
        if (row >= 0) {
            // Ambil data dari row yang diklik
            data.setId((Double)table.getValueAt(row, 0));
//            System.out.println(data.getId());
            data.setNama((String) table.getValueAt(row, 1));
            data.setBerat((Double) table.getValueAt(row, 2));
            
                data.setTanggalTanam((java.sql.Date) table.getValueAt(row, 3));
                data.setTanggalPanen((java.sql.Date) table.getValueAt(row, 3));
            
            id_target = (Double) table.getValueAt(row, 0);
        }
        view.namaBuah.setText(data.getNama());
        view.beratBuah.setText(data.getBerat().toString());
        view.tanggalTanam.setText(originalFormat.format(data.getTanggalTanam()));
        view.tanggalPanen.setText(originalFormat.format(data.getTanggalPanen()));
        
        setModeUpdate(view);
    }
    public void tambahData(PanenBuah view){
        String nama = view.namaBuah.getText();
        Double berat = Double.parseDouble(view.beratBuah.getText());
        String tglTanam = view.tanggalTanam.getText();
        String tglPanen = view.tanggalPanen.getText();
        
        try {
            Date dateTanam = originalFormat.parse(tglTanam);
            Date datePanen = originalFormat.parse(tglPanen);
            
            if (nama.isEmpty() || view.beratBuah.getText().isEmpty() || tglTanam.isEmpty() || tglPanen.isEmpty()){
                JOptionPane.showMessageDialog(view, "Lengkapilah data yang diperlukan.", "try again!", JOptionPane.INFORMATION_MESSAGE);
            }else{
                // mulai tambah dataatau update data
                if (view.btnTambah.getText().equalsIgnoreCase("Tambah Data")){
                    Buah buah = new Buah();
                    buah.setNama(nama);
                    buah.setBerat(berat);
                    // konversi dari util.Date ke sql.Date
                    long miliSec = dateTanam.getTime();
                    buah.setTanggalTanam(new java.sql.Date(miliSec));
                    // konvert again
                    miliSec = datePanen.getTime();
                    buah.setTanggalPanen(new java.sql.Date(miliSec));
                    dao.insert(buah);

                    reloadIsiTable(view);
                    JOptionPane.showMessageDialog(view, "Data berhasil ditambahkan.", "try again!", JOptionPane.INFORMATION_MESSAGE);
                    
                }else{
                    // update data
                    Buah buah = new Buah();
                    buah.setId(id_target);
                    buah.setNama(nama);
                    buah.setBerat(berat);
                    // konversi dari util.Date ke sql.Date
                    long miliSec = dateTanam.getTime();
                    buah.setTanggalTanam(new java.sql.Date(miliSec));
                    // konvert again
                    miliSec = datePanen.getTime();
                    buah.setTanggalPanen(new java.sql.Date(miliSec));
                    dao.update(buah);

                    reloadIsiTable(view);
                    JOptionPane.showMessageDialog(view, "Data berhasil diUPDATE.", "try again!", JOptionPane.INFORMATION_MESSAGE);
                    view.btnTambah.setText("Tambah Data");
                    setModeTambah(view);
                }
            }
                        
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Masukan format tanggal dengan benar", "try again!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void deleteData(PanenBuah view){
        Buah buah = dao.getOne(id_target);
        int y = JOptionPane.showConfirmDialog(view, "Apakah anda yakin ingin menghapus buah "+buah.getNama(), "Butuh Konfirmasi!", JOptionPane.YES_NO_OPTION);
        if (y == JOptionPane.YES_OPTION){
            System.out.println("id sebelum "+id_target);
            dao.delete(id_target);
        }
        reloadIsiTable(view);
        setModeTambah(view);
        
    }
}
