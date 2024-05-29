/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perkebunan.model;

import java.util.List;

/**
 *
 * @author andre
 */
public interface InterfaceDaoUser {
    public void insert(User user);

    public void update(User user);

    public void delete(String username);
    
    public User getOne(String username);

    public List<User> getData();
}
