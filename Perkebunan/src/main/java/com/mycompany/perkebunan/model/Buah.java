/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perkebunan.model;

import java.sql.Date;

/**
 *
 * @author andre
 */
public class Buah {
    private Double id;
    private String nama;
    private Double berat;
    private Date tanggalTanam;
    private Date tanggalPanen;

    public void setBerat(Double berat) {
        this.berat = berat;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTanggalPanen(Date tanggalPanen) {
        this.tanggalPanen = tanggalPanen;
    }

    public void setTanggalTanam(Date tanggalTanam) {
        this.tanggalTanam = tanggalTanam;
    }

    public Double getBerat() {
        return berat;
    }

    public Double getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public Date getTanggalPanen() {
        return tanggalPanen;
    }

    public Date getTanggalTanam() {
        return tanggalTanam;
    }
    
    
}
