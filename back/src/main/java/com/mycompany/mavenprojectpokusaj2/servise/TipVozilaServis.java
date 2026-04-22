/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.servise;

import com.mycompany.mavenprojectpokusaj2.dto.impl.TipVozilaDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.TipVozila;
import com.mycompany.mavenprojectpokusaj2.mapper.impl.TipVozilaMapper;
import com.mycompany.mavenprojectpokusaj2.repository.impl.TipVozilaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
public class TipVozilaServis {
    private final TipVozilaRepository tipVozilaRepository;
    private final TipVozilaMapper mapper;

    
    
    @Autowired
    public TipVozilaServis(TipVozilaRepository tipVozilaRepository, TipVozilaMapper mapper) {
        this.tipVozilaRepository = tipVozilaRepository;
        this.mapper = mapper;
    }
    
    public List<TipVozilaDto> findAll(){
        return tipVozilaRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
    
    
    
    public TipVozilaDto findById(Long id) throws Exception{
        return mapper.toDto(tipVozilaRepository.findById(id));
    }

    public TipVozilaDto create(TipVozilaDto tipVozilaDto) {
        TipVozila tip = mapper.toEntity(tipVozilaDto);
        tipVozilaRepository.save(tip);
        return mapper.toDto(tip);
        
    }

    public void delete(Long id) {
        tipVozilaRepository.deleteById(id);
    }

    public TipVozilaDto update(TipVozilaDto tipVozilaDto) {
        TipVozila update = mapper.toEntity(tipVozilaDto);
        tipVozilaRepository.save(update);
        
        return mapper.toDto(update);
    }
    
    
}
