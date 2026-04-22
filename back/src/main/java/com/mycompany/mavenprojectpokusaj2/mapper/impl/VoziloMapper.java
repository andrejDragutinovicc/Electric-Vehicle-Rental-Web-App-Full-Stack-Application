/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.mapper.impl;

import com.mycompany.mavenprojectpokusaj2.dto.impl.VoziloDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.TipVozila;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Vozilo;
import com.mycompany.mavenprojectpokusaj2.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component
public class VoziloMapper implements DtoEntityMapper<VoziloDto, Vozilo>{

    @Override
    public VoziloDto toDto(Vozilo e) {
        Long tipVozilaid = e.getTipVozila() != null ? e.getTipVozila().getId():null;
        
        return new VoziloDto(e.getId(), e.getBaterija(), e.getKilometraza(), e.getCenaPoMinutu(),tipVozilaid);
    }

    @Override
    public Vozilo toEntity(VoziloDto t) {
        TipVozila tipVozila = t.getTipVozilaID()!= null ? new TipVozila(t.getTipVozilaID()):null;
        
        return new Vozilo(t.getId(), t.getBaterija(), t.getKilometraza(), t.getCenaPoMinutu(), tipVozila);
    }
    
}
