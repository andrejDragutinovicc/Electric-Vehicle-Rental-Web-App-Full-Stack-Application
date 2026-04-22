/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.servise;

import com.mycompany.mavenprojectpokusaj2.dto.impl.VoziloDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Vozilo;
import com.mycompany.mavenprojectpokusaj2.mapper.impl.VoziloMapper;
import com.mycompany.mavenprojectpokusaj2.repository.impl.VoziloRepository;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
public class VoziloServis {

    private final VoziloRepository voziloRepository;
    private final VoziloMapper voziloMapper;

    @Autowired
    public VoziloServis(VoziloRepository voziloRepository, VoziloMapper voziloMapper) {
        this.voziloRepository = voziloRepository;
        this.voziloMapper = voziloMapper;
    }

    public Object findAll() {
        return voziloRepository.findAll().stream().map(voziloMapper::toDto).collect(Collectors.toList());
    }

    public VoziloDto findById(Long id) {
        try {
            Vozilo vozilo = voziloRepository.findById(id);
            return voziloMapper.toDto(vozilo);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Vozilo sa ID " + id + " ne postoji"
            );
        }
    }

    public VoziloDto create(VoziloDto voziloDto) {
        Vozilo v = voziloMapper.toEntity(voziloDto);
        voziloRepository.save(v);
        return voziloMapper.toDto(v);

    }

    public void delete(Long id) {
        voziloRepository.deleteById(id);
    }

    public VoziloDto update(VoziloDto tipVozilaDto) {
        Vozilo update = voziloMapper.toEntity(tipVozilaDto);
        voziloRepository.save(update);

        return voziloMapper.toDto(update);
    }

}
