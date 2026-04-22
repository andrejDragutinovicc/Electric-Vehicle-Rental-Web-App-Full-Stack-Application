/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.repository.impl;

import com.mycompany.mavenprojectpokusaj2.entity.impl.Vozilo;
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
public class VoziloRepository implements MyAppRepository<Vozilo, Long> {

    @PersistenceContext
    private EntityManager entityMenager;

    @Override
    public List<Vozilo> findAll() {
        return entityMenager.createQuery("SELECT v from Vozilo v", Vozilo.class).getResultList();
    }

    @Override
    public Vozilo findById(Long id) throws Exception {
        Vozilo vozilo = entityMenager.find(Vozilo.class, id);
        if (vozilo == null) {
            throw new Exception("Proizvod nije pronadjen");
        }

        return vozilo;
    }

    @Override
    @Transactional
    public void save(Vozilo entity) {
        if (entity.getId() == null) {
            entityMenager.persist(entity);
        } else {
            entityMenager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Vozilo vozilo = entityMenager.find(Vozilo.class, id);
        if (vozilo != null) {
            entityMenager.remove(vozilo);
        }

    }

}
