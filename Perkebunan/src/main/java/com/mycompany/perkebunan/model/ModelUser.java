/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perkebunan.model;

import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModelUser extends AbstractTableModel{
    List<User> users = new ArrayList<User>();
    
    public ModelUser(List<User> users) {
        this.users = users;
    }
    
    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nama";
            case 1:
                return "Username";
            case 2:
                return "Password";
            
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return users.get(row).getNama();
            case 1:
                return users.get(row).getUsername();
            case 2:
                return users.get(row).getPassword();
            default:
                return null;
        }
    }
    
    
}
