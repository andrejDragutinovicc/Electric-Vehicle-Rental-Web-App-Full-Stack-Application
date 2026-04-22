/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.entity.impl;

import com.mycompany.mavenprojectpokusaj2.entity.MyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 *
 * @author andre
 */
@Entity
public class Voznja implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime vreme;
    private Double cena;
    private Double trajanjeVoznje;

    @ManyToOne
    @JoinColumn(name = "korisnikId")
    private Korisnik korisnik;

    @ManyToOne
    @JoinColumn(name = "gradId")
    private Grad grad;

    @ManyToOne
    @JoinColumn(name = "voziloId")
    private Vozilo vozilo;

    public Voznja() {
    }

    public Voznja(Long id, LocalDateTime vreme, Double cena, Double trajanjeVoznje, Korisnik korisnik, Grad grad, Vozilo vozilo) {
        this.id = id;
        this.vreme = vreme;
        this.cena = cena;
        this.trajanjeVoznje = trajanjeVoznje;
        this.korisnik = korisnik;
        this.grad = grad;
        this.vozilo = vozilo;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getVreme() {
        return vreme;
    }

    public Double getCena() {
        return cena;
    }

    public Double getTrajanjeVoznje() {
        return trajanjeVoznje;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public Grad getGrad() {
        return grad;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVreme(LocalDateTime vreme) {
        this.vreme = vreme;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public void setTrajanjeVoznje(Double trajanjeVoznje) {
        this.trajanjeVoznje = trajanjeVoznje;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;   
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }
    
    
    
    
}
