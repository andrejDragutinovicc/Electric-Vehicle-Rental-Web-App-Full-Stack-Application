/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.mapper.impl;

import com.mycompany.mavenprojectpokusaj2.dto.impl.KorisnikDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Korisnik;
import com.mycompany.mavenprojectpokusaj2.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component
public class KorisnikMapper implements DtoEntityMapper<KorisnikDto,Korisnik>{

    @Override
    public KorisnikDto toDto(Korisnik e) {
        if(e == null) return null;
        else return new KorisnikDto(e.getId(), e.getUsername(), e.getEmail(), e.getRole());
    }

    @Override
    public Korisnik toEntity(KorisnikDto t) {
        if(t == null) return null;
        
        Korisnik k  = new Korisnik();
        k.setId(t.getId());
        k.setUsername(t.getUsername());
        k.setEmail(t.getEmail());
        k.setRole(t.getRole());
        
        return k;
        
    }    
}
