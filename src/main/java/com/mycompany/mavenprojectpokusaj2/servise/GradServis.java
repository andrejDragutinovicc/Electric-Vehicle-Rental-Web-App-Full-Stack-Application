/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.servise;

import com.mycompany.mavenprojectpokusaj2.dto.impl.GradDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Grad;
import com.mycompany.mavenprojectpokusaj2.mapper.impl.GradMapper;
import com.mycompany.mavenprojectpokusaj2.repository.impl.GradRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;


/**
 *
 * @author andre
 */
@Service
public class GradServis {

    private final GradRepository Repository;
    private final GradMapper mapper;

    @Autowired
    public GradServis(GradRepository Repository, GradMapper mapper) {
        this.Repository = Repository;
        this.mapper = mapper;
    }

     public List<GradDto> findAll(){
        return Repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
    
    
    
    public GradDto findById(Long id) throws Exception{
        return mapper.toDto(Repository.findById(id));
    }

    public GradDto create(GradDto g) {
        Grad tip = mapper.toEntity(g);
        Repository.save(tip);
        return mapper.toDto(tip);
        
    }

    public void delete(Long id) {
        Repository.deleteById(id);
    }

    public GradDto update(GradDto g) {
        Grad update = mapper.toEntity(g);
        Repository.save(update);
        
        return mapper.toDto(update);
    }
    
    
}
