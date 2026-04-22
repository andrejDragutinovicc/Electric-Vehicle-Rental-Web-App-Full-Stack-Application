/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.entity.impl;

import com.mycompany.mavenprojectpokusaj2.entity.MyEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "grad")
public class Grad implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String naziv;
    private String postanskiBroj;

    @OneToMany(mappedBy = "grad", cascade = CascadeType.ALL)
    private List<Voznja> voznje;

    public Grad() {
    }

    public Grad(Long id, String naziv, String postanskiBroj, List<Voznja> voznje) {
        this.id = id;
        this.naziv = naziv;
        this.postanskiBroj = postanskiBroj;
        this.voznje = voznje;
    }

    public Grad(Long gradID) {
        id = gradID;
    }

    public List<Voznja> getVoznje() {
        return voznje;
    }

    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

}
