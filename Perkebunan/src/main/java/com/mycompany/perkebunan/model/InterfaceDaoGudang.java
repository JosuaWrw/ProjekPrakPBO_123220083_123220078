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
public interface InterfaceDaoGudang {

    public void insert(Gudang gudang);

    public void update(Gudang gudang);

    public void delete(Double id);
    
    public Gudang getOne(Double id);

    public List<Gudang> getData();
}
