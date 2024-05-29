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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class DaoUser implements InterfaceDaoUser{
    Connection connection;
    final String insert = "INSERT INTO user (nama_user,username,password) VALUES (?, ?, ?);";
    final String update = "UPDATE user SET nama_user=?, password=? WHERE username=? ;";
    final String delete = "DELETE FROM user WHERE username=? ;";
    final String getSatu = "SELECT * FROM user WHERE username=? ;";
    final String select = "SELECT * FROM user ORDER BY username DESC;";
    
    public DaoUser() {
        connection = Koneksi.connection();
    }

    @Override
    public void insert(User user) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, user.getNama());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            
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
    public void update(User user) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, user.getNama());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUsername());
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
    public void delete(String username) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, username);
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
    public User getOne(String username){
        User user = null;
        
        try {
            PreparedStatement st = connection.prepareStatement(getSatu);
            st.setString(1, username);
            ResultSet hasil = st.executeQuery();
            if (hasil.next()){
                user = new User();
                user.setNama(hasil.getString("nama_user"));
                user.setUsername(hasil.getString("username"));
                user.setPassword(hasil.getString("password"));
                       
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<User> getData() {
        List<User> listUser = null;
        try {
            listUser = new ArrayList<User>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                User user = new User();
                user.setNama(rs.getString("nama_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                listUser.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listUser;
    }
}
