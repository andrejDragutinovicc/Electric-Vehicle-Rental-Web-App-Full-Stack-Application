/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.repository.impl;

import com.mycompany.mavenprojectpokusaj2.entity.impl.TipVozila;
import com.mycompany.mavenprojectpokusaj2.repository.MyAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public class TipVozilaRepository implements MyAppRepository<TipVozila, Long> {

    @PersistenceContext
    private EntityManager entityMenager;

    @Override
    public List<TipVozila> findAll() {
        return entityMenager.createQuery("SELECT v from TipVozila v", TipVozila.class).getResultList();
    }

    @Override
    public TipVozila findById(Long id) throws Exception {
        TipVozila tip = entityMenager.find(TipVozila.class, id);
        if (tip == null) {
            throw new Exception("Tip vozila nije pronadjen");
        }

        return tip;
    }

    @Override
    @Transactional
    public void save(TipVozila entity) {
        if (entity.getId() == null) {
            entityMenager.persist(entity);
        } else {
            entityMenager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        TipVozila tip = entityMenager.find(TipVozila.class, id);
        if (tip  != null) {
            entityMenager.remove(tip);
        }
    }


    
}
