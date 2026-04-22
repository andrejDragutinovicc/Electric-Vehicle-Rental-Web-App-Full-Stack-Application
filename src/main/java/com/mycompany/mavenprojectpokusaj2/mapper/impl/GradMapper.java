/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.mapper.impl;

import com.mycompany.mavenprojectpokusaj2.dto.impl.GradDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Grad;
import com.mycompany.mavenprojectpokusaj2.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component
public class GradMapper implements DtoEntityMapper<GradDto, Grad> {

    @Override
    public GradDto toDto(Grad e) {
        return new GradDto(e.getId(), e.getNaziv(), e.getPostanskiBroj());

    }

    @Override
    public Grad toEntity(GradDto t) {
        return new Grad(t.getId(), t.getNaziv(), t.getPostanskiBRoj(),null);
        
    }

}
