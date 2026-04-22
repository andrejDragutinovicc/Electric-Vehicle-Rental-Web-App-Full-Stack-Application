/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.servise;

import com.mycompany.mavenprojectpokusaj2.dto.impl.VoziloDto;
import com.mycompany.mavenprojectpokusaj2.dto.impl.VoznjaDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Korisnik;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Vozilo;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Voznja;
import com.mycompany.mavenprojectpokusaj2.mapper.impl.VoznjaMapper;
import com.mycompany.mavenprojectpokusaj2.repository.impl.KorisnikRepository;
import com.mycompany.mavenprojectpokusaj2.repository.impl.VoznjaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
public class VoznjaServis {

    @Autowired
    private KorisnikRepository korisnikRepository;

    private final VoznjaRepository repository;
    private final VoznjaMapper mapper;

    @Autowired
    public VoznjaServis(VoznjaRepository repository, VoznjaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Object findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public VoznjaDto create(VoznjaDto voznjaDto) {

        Voznja v = mapper.toEntity(voznjaDto);

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        Korisnik korisnik = korisnikRepository
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Korisnik ne postoji"));
        v.setKorisnik(korisnik);
        repository.save(v);
        return mapper.toDto(v);

    }

    public List<VoznjaDto> getHistory() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        List<Voznja> voznje;
        voznje = repository.findAllByKorisnikUsername(username);

        return voznje.stream()
                .map(mapper::toDto)
                .toList();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public VoznjaDto update(VoznjaDto voznjaDto) {
        Voznja update = mapper.toEntity(voznjaDto);
        repository.save(update);

        return mapper.toDto(update);
    }

}
