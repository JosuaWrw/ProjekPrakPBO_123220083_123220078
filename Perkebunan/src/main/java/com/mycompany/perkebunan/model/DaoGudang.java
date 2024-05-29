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
public class DaoGudang implements InterfaceDaoGudang{
    Connection connection;
    final String insert = "INSERT INTO gudang (nama_gudang, kapasitas) VALUES (?, ?);";
    final String update = "UPDATE gudang SET nama_gudang=?, kapasitas=? WHERE id_gudang=? ;";
    final String delete = "DELETE FROM gudang WHERE id_gudang=? ;";
    final String getSatu = "SELECT * FROM gudang WHERE id_gudang=? ;";
    final String select = "SELECT * FROM gudang ORDER BY id_gudang DESC;";
    
    public DaoGudang() {
        connection = Koneksi.connection();
    }

    @Override
    public void insert(Gudang gudang) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, gudang.getNamaGudang());
            statement.setDouble(2, gudang.getKapasitas());
            
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
    public void update(Gudang gudang) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, gudang.getNamaGudang());
            statement.setDouble(2, gudang.getKapasitas());
            statement.setDouble(3, gudang.getId());
            
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
    public Gudang getOne(Double id){
        Gudang gudang = null;
        
        try {
            PreparedStatement st = connection.prepareStatement(getSatu);
            st.setDouble(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                gudang = new Gudang();
                gudang.setId(rs.getDouble("id_gudang"));
                gudang.setNamaGudang(rs.getString("nama_gudang"));
                gudang.setKapasitas(rs.getDouble("kapasitas"));
               
            }
            return gudang;
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Gudang> getData() {
        List<Gudang> gudangs = null;
        try {
            gudangs = new ArrayList<Gudang>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Gudang gudang = new Gudang();
                gudang.setId(rs.getDouble("id_gudang"));
                gudang.setNamaGudang(rs.getString("nama_gudang"));
                gudang.setKapasitas(rs.getDouble("kapasitas"));
                
                gudangs.add(gudang);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gudangs;
    }
}
