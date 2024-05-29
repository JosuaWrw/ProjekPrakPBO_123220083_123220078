/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perkebunan.controller;

import com.mycompany.perkebunan.model.Gudang;
import com.mycompany.perkebunan.model.DaoGudang;
import com.mycompany.perkebunan.view.PanenBuah;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andre
 */
public class GudangController {
    Double id_target = 0d;
    DaoGudang dao = new DaoGudang();
    
    public void setModeUpdate(PanenBuah view){
        view.btnCancelGudang.setVisible(true);
        view.btnHapusGudang.setVisible(true);
        view.btnTambahGudang.setText("Update Data");
    }
    public void setModeTambah(PanenBuah view){
        view.btnCancelGudang.setVisible(false);
        view.btnHapusGudang.setVisible(false);
        view.btnTambahGudang.setText("Tambah Data");
        id_target = 0d;
        
        view.namaGudang.setText("");
        view.kapasitasGudang.setText("");
        
        // clear row terpiih di tabel
        view.tabelGudang.clearSelection();
    }
    public void reloadIsiTable(PanenBuah view){
        DefaultTableModel tabel = (DefaultTableModel) view.tabelGudang.getModel();
        tabel.setRowCount(0);
        int i = 1;
        for(Gudang a : dao.getData()){
            tabel.addRow(new Object[] {a.getId(), a.getNamaGudang(), a.getKapasitas()});
            i++;
        }
    }
    
    public void eventKlikRowTable(PanenBuah view, java.awt.event.MouseEvent evt){
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        
        Gudang data = new  Gudang();
        if (row >= 0) {
            // Ambil data dari row yang diklik
            data.setId((Double)table.getValueAt(row, 0));
//            System.out.println(data.getId());
            data.setNamaGudang((String) table.getValueAt(row, 1));
            data.setKapasitas((Double) table.getValueAt(row, 2));
                        
            id_target = (Double) table.getValueAt(row, 0);
        }
        view.namaGudang.setText(data.getNamaGudang());
        view.kapasitasGudang.setText(data.getKapasitas().toString());
       
        setModeUpdate(view);
    }
    public void tambahData(PanenBuah view){
        String nama = view.namaGudang.getText();
        Double kapasitas = Double.parseDouble(view.kapasitasGudang.getText());

        if (nama.isEmpty() || view.kapasitasGudang.getText().isEmpty() ){
            JOptionPane.showMessageDialog(view, "Lengkapilah data yang diperlukan.", "try again!", JOptionPane.INFORMATION_MESSAGE);
        }else{
            // mulai tambah dataatau update data
            if (view.btnTambahGudang.getText().equalsIgnoreCase("Tambah Data")){
                Gudang gudang = new Gudang();
                gudang.setNamaGudang(nama);
                gudang.setKapasitas(kapasitas);
                
                dao.insert(gudang);

                reloadIsiTable(view);
                JOptionPane.showMessageDialog(view, "Data berhasil ditambahkan.", "try again!", JOptionPane.INFORMATION_MESSAGE);

            }else{
                // update data
                Gudang gudang = new Gudang();
                gudang.setId(id_target);
                gudang.setNamaGudang(nama);
                gudang.setKapasitas(kapasitas);
                
                dao.update(gudang);

                reloadIsiTable(view);
                JOptionPane.showMessageDialog(view, "Data berhasil diUPDATE.", "try again!", JOptionPane.INFORMATION_MESSAGE);
                view.btnTambah.setText("Tambah Data");
                setModeTambah(view);
            }
        }
                        
        
    }
    
    public void deleteData(PanenBuah view){
        Gudang gudang = dao.getOne(id_target);
        int y = JOptionPane.showConfirmDialog(view, "Apakah anda yakin ingin menghapus buah "+gudang.getNamaGudang(), "Butuh Konfirmasi!", JOptionPane.YES_NO_OPTION);
        if (y == JOptionPane.YES_OPTION){
            System.out.println("id sebelum "+id_target);
            dao.delete(id_target);
        }
        reloadIsiTable(view);
        setModeTambah(view);
        
    }
}
