/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.mapper.impl;

import com.mycompany.mavenprojectpokusaj2.dto.impl.TipVozilaDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.TipVozila;
import com.mycompany.mavenprojectpokusaj2.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component
public class TipVozilaMapper implements DtoEntityMapper<TipVozilaDto, TipVozila> {

    @Override
    public TipVozilaDto toDto(TipVozila e) {
        return new TipVozilaDto(e.getId(), e.getIme(), e.getOpis());
    }

    @Override
    public TipVozila toEntity(TipVozilaDto t) {
        return new TipVozila(t.getId(), t.getIme(), t.getOpis(), null);
        
        
    }

}
