/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.dto.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.mavenprojectpokusaj2.dto.Dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 *
 * @author andre
 */
public class VoznjaDto implements Dto {

    private Long id;
    @Positive(message = "Cena mora da bude pozitivan broj")
    @NotNull(message = "Cena mora da postoji")
    private double cena;
    @Positive(message = "trajanjeVoznje mora da bude pozitivan broj")
    private double trajanjeVoznje;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime vreme;

    private Long korisnikId;
    private Long gradID;
    private Long voziloId;

    public VoznjaDto() {
    }

    public VoznjaDto(Long id, double cena, double trajanjeVoznje, LocalDateTime vreme, Long korisnikId, Long gradID, Long voziloId) {
        this.id = id;
        this.cena = cena;
        this.trajanjeVoznje = trajanjeVoznje;
        this.vreme = vreme;
        this.korisnikId = korisnikId;
        this.gradID = gradID;
        this.voziloId = voziloId;
    }

    public VoznjaDto(Long id, double cena, double trajanjeVoznje, LocalDateTime vreme) {
        this.id = id;
        this.cena = cena;
        this.trajanjeVoznje = trajanjeVoznje;
        this.vreme = vreme;
    }

    public Long getId() {
        return id;
    }

    public double getCena() {
        return cena;
    }

    public double getTrajanjeVoznje() {
        return trajanjeVoznje;
    }

    public LocalDateTime getVreme() {
        return vreme;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public Long getGradID() {
        return gradID;
    }

    public Long getVoziloId() {
        return voziloId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setTrajanjeVoznje(double trajanjeVoznje) {
        this.trajanjeVoznje = trajanjeVoznje;
    }

    public void setVreme(LocalDateTime vreme) {
        this.vreme = vreme;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public void setGradID(Long gradID) {
        this.gradID = gradID;
    }

    public void setVoziloId(Long voziloId) {
        this.voziloId = voziloId;
    }

}
