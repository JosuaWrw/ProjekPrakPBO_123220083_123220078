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
public interface InterfaceDaoBuah{
    public void insert(Buah buah);

    public void update(Buah buah);

    public void delete(Double id);
    
    public Buah getOne(Double id);

    public List<Buah> getData();
}
