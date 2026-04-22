/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.mapper.impl;

import com.mycompany.mavenprojectpokusaj2.dto.impl.VoznjaDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Grad;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Korisnik;
import com.mycompany.mavenprojectpokusaj2.entity.impl.TipVozila;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Vozilo;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Voznja;
import com.mycompany.mavenprojectpokusaj2.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author andre
 */
@Component
public class VoznjaMapper implements DtoEntityMapper<VoznjaDto, Voznja> {

    @Override
    public VoznjaDto toDto(Voznja e) {
        Long korisnikId = e.getKorisnik() != null ? e.getKorisnik().getId() : null;
        Long voziloId = e.getVozilo() != null ? e.getVozilo().getId() : null;
        Long gradId = e.getGrad() != null ? e.getGrad().getId() : null;
        return new VoznjaDto(e.getId(), e.getCena(), e.getTrajanjeVoznje(), e.getVreme(), korisnikId, gradId, voziloId);
    }

    @Override
    public Voznja toEntity(VoznjaDto t) {

        Korisnik korisnik = t.getKorisnikId() != null ? new Korisnik(t.getKorisnikId()) : null;
        Vozilo vozilo = t.getVoziloId() != null ? new Vozilo(t.getVoziloId()) : null;
        Grad grad = t.getGradID() != null ? new Grad(t.getGradID()) : null;

        return new Voznja(t.getId(), t.getVreme(), t.getCena(), t.getTrajanjeVoznje(), korisnik, grad, vozilo);
    }

}
