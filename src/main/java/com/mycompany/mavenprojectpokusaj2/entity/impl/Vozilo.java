/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.entity.impl;

import com.mycompany.mavenprojectpokusaj2.entity.MyEntity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import java.util.List;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "vozilo")
public class Vozilo implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double baterija;
    private double kilometraza;
    private double cenaPoMinutu;

    @OneToMany(mappedBy = "vozilo", cascade = CascadeType.ALL)
    private List<Voznja> voznje;

    @ManyToOne
    @JoinColumn(name = "tip_vozila_id")
    private TipVozila tipVozila;

    public Vozilo() {
    }

    public Vozilo(Long id, double baterija, double kilometraza, double cenaPoMinutu, TipVozila tipVozila) {
        this.id = id;
        this.baterija = baterija;
        this.kilometraza = kilometraza;
        this.cenaPoMinutu = cenaPoMinutu;
        this.tipVozila = tipVozila;
    }

    public Vozilo(Long voziloId) {
        id = voziloId;
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

    public TipVozila getTipVozila() {
        return tipVozila;
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

    public void setTipVozila(TipVozila tipVozila) {
        this.tipVozila = tipVozila;
    }

}
