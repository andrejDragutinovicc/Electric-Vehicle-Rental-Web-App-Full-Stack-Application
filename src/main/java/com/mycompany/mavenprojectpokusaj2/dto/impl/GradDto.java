/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.dto.impl;

import com.mycompany.mavenprojectpokusaj2.dto.Dto;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author andre
 */
public class GradDto implements Dto{
    
    private Long id;
    @NotNull(message = "Naziv grada je obavezan")
    private String naziv;
    private String postanskiBRoj;

    public GradDto(Long id, String naziv, String postanskiBRoj) {
        this.id = id;
        this.naziv = naziv;
        this.postanskiBRoj = postanskiBRoj;
    }

    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getPostanskiBRoj() {
        return postanskiBRoj;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setPostanskiBRoj(String postanskiBRoj) {
        this.postanskiBRoj = postanskiBRoj;
    }
    
    
    
}
