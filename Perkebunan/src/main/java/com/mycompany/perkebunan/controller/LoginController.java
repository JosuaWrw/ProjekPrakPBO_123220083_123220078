/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perkebunan.controller;

import com.mycompany.perkebunan.view.Login;
import com.mycompany.perkebunan.model.User;
import com.mycompany.perkebunan.model.DaoUser;
import com.mycompany.perkebunan.view.PanenBuah;
import java.awt.Frame;
import javax.swing.JOptionPane;

public class LoginController {
    
    DaoUser dao = new DaoUser();
    String nama, username, password;
    
    public LoginController(){
    }
    
    public void DaftarAkun(Login view){
        
        nama = view.daftarNama.getText();
        username = view.daftarUname.getText();
        password = new String(view.daftarPass.getPassword());
        if (nama.isEmpty() || username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(view, "Tolong masukan data dengan benar", "try again!", JOptionPane.INFORMATION_MESSAGE);
        }else{
            User user = dao.getOne(username);
            if (user == null){
                user = new User();
                user.setNama(nama);
                user.setUsername(username);
                user.setPassword(password);
                dao.insert(user);
                JOptionPane.showMessageDialog(view, "AKUN BERHASIL DIBUAT", "information!", JOptionPane.INFORMATION_MESSAGE);
            }else{
                // username sudah digunakan
                JOptionPane.showMessageDialog(view, "Username sudah digunakan, siakan ganti username", "try again!", JOptionPane.INFORMATION_MESSAGE);
            }
            user = new User();
            user.setNama(nama);
            user.setUsername(username);
            user.setPassword(password);
            dao.insert(user);
        }
        
    }
    public void masukLogin(Login view){
        username = view.loginUname.getText();
        password =  new String(view.loginPass.getPassword());
        if (username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(view, "Tolong masukan data dengan benar", "try again!", JOptionPane.INFORMATION_MESSAGE);
        }else{
            User user = dao.getOne(username);
            if (user == null){
                JOptionPane.showMessageDialog(view, "Akun yang anda masukan tidak ada", "try again!", JOptionPane.INFORMATION_MESSAGE);
            }else{
                if (password.equals(user.getPassword())){
                    // masuk ke halaman selanjutnya
                    // mematikan halaman ini
                    view.dispose();
                    PanenBuah panen = new PanenBuah();     
                }else{
                    JOptionPane.showMessageDialog(view, "Password anda keliru", "try again!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
