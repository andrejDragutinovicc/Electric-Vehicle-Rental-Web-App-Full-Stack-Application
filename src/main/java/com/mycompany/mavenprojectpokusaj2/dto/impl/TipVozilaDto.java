/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.dto.impl;

import com.mycompany.mavenprojectpokusaj2.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 *
 * @author andre
 */
public class TipVozilaDto implements Dto {

    private Long id;
    @NotEmpty(message = "Naziv tipa je obavezan")
    private String ime;
    @Size(max = 400,message = "Maksimalan broj karaktera je 400")
    private String opis;

    public TipVozilaDto(Long id, String ime, String opis) {
        this.id = id;
        this.ime = ime;
        this.opis = opis;
    }

    public Long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    
     
}
