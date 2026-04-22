/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.dto.impl;

import com.mycompany.mavenprojectpokusaj2.dto.Dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author andre
 */
public class VoziloDto implements Dto {

     private Long id;
     @Positive(message = "baterija mora da bude pozitivan broj")
    private double baterija;
    @Positive(message = "Kilometraza mora da bude pozitivan broj")
    private double kilometraza;
    @Positive(message = "Cena mora da bude pozitivan broj")
    @NotNull(message = "Cena mora da postoji")
    private double cenaPoMinutu;
    
    private Long tipVozilaID;

    public VoziloDto(Long id, double baterija, double kilometraza, double cenaPoMinutu, Long TipVozilaID) {
        this.id = id;
        this.baterija = baterija;
        this.kilometraza = kilometraza;
        this.cenaPoMinutu = cenaPoMinutu;
        this.tipVozilaID = TipVozilaID;
    }
    

    
    public VoziloDto(Long id, double baterija, double kilometraza, double cenaPoMinutu) {
        this.id = id;
        this.baterija = baterija;
        this.kilometraza = kilometraza;
        this.cenaPoMinutu = cenaPoMinutu;
    }

    public Long getId() {
        return id;
    }

    public double getBaterija() {
        return baterija;
    }

    public double getKilometraza() {
        return kilometraza;
    }

    public double getCenaPoMinutu() {
        return cenaPoMinutu;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBaterija(double baterija) {
        this.baterija = baterija;
    }

    public void setKilometraza(double kilometraza) {
        this.kilometraza = kilometraza;
    }

    public void setCenaPoMinutu(double cenaPoMinutu) {
        this.cenaPoMinutu = cenaPoMinutu;
    }

    public Long getTipVozilaID() {
        return tipVozilaID;
    }

    public void setTipVozilaID(Long TipVozilaID) {
        this.tipVozilaID = TipVozilaID;
    }
    
    
    
}
