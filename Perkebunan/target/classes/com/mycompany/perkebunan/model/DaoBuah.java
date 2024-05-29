/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perkebunan.model;

import com.mycompany.perkebunan.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class DaoBuah implements InterfaceDaoBuah{
    Connection connection;
    final String insert = "INSERT INTO buah (nama_buah, berat_buah, tgl_tanam, tgl_panen) VALUES (?, ?, ?, ?);";
    final String update = "UPDATE buah SET nama_buah=?, berat_buah=?, tgl_tanam=?, tgl_panen=? WHERE id_buah=? ;";
    final String delete = "DELETE FROM buah WHERE id_buah=? ;";
    final String getSatu = "SELECT * FROM buah WHERE id_buah=? ;";
    final String select = "SELECT * FROM buah ORDER BY id_buah DESC;";
    
    public DaoBuah() {
        connection = Koneksi.connection();
    }

    @Override
    public void insert(Buah buah) {
        PreparedStatement statement = null;
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-M-dd");
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, buah.getNama());
            statement.setDouble(2, buah.getBerat());
//            statement.setString(3, newFormat.format(buah.getTanggalTanam()));
//            statement.setString(4, newFormat.format(buah.getTanggalPanen()));
            statement.setDate(3, buah.getTanggalTanam());
            statement.setDate(4, buah.getTanggalPanen());
            
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(Buah buah) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, buah.getNama());
            statement.setDouble(2, buah.getBerat());
            statement.setString(3, buah.getTanggalTanam().toString());
            statement.setString(4, buah.getTanggalPanen().toString());
            statement.setDouble(5, buah.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Double id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setDouble(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    @Override
    public Buah getOne(Double id){
        Buah buah = null;
        
        try {
            PreparedStatement st = connection.prepareStatement(getSatu);
            st.setDouble(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                buah = new Buah();
                buah.setId(rs.getDouble("id_buah"));
                buah.setNama(rs.getString("nama_buah"));
                buah.setBerat(rs.getDouble("berat_buah"));
                buah.setTanggalTanam(rs.getDate("tgl_tanam"));
                buah.setTanggalPanen(rs.getDate("tgl_panen"));
                       
            }
            return buah;
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Buah> getData() {
        List<Buah> buahs = null;
        try {
            buahs = new ArrayList<Buah>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Buah buah = new Buah();
                buah.setId(rs.getDouble("id_buah"));
                buah.setNama(rs.getString("nama_buah"));
                buah.setBerat(rs.getDouble("berat_buah"));
                buah.setTanggalTanam(rs.getDate("tgl_tanam"));
                buah.setTanggalPanen(rs.getDate("tgl_panen"));
                buahs.add(buah);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return buahs;
    }
}
