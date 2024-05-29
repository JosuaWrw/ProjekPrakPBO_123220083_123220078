/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perkebunan.model;

/**
 *
 * @author andre
 */
public class Gudang {
    private Double id;
    private String namaGudang;
    private Double kapasitas;

    public void setId(Double id) {
        this.id = id;
    }

    public void setKapasitas(Double kapasitas) {
        this.kapasitas = kapasitas;
    }

    public void setNamaGudang(String namaGudang) {
        this.namaGudang = namaGudang;
    }

    public Double getId() {
        return id;
    }

    public Double getKapasitas() {
        return kapasitas;
    }

    public String getNamaGudang() {
        return namaGudang;
    }
    
}
