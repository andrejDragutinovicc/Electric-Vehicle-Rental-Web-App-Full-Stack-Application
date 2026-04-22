/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.entity.impl;

import java.util.List;
import jakarta.persistence.*;
import com.mycompany.mavenprojectpokusaj2.entity.MyEntity;

/**
 *
 * @author andre
 */
    @Entity
    @Table(name = "tipvozila")
    public class TipVozila implements MyEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String ime;
        private String opis;

        @OneToMany(mappedBy = "tipVozila", cascade = CascadeType.ALL)
        private List<Vozilo> vozila;

        public TipVozila() {
        }

        public TipVozila(Long id, String ime, String opis, List<Vozilo> vozila) {
            this.id = id;
            this.ime = ime;
            this.opis = opis;
            this.vozila = vozila;
        }

        public TipVozila(Long tipVozilaID) {

            this.id = tipVozilaID;
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

        public List<Vozilo> getVozila() {
            return vozila;
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

        public void setVozila(List<Vozilo> vozila) {
            this.vozila = vozila;
        }

    }
