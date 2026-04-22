/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.repository.impl;

import com.mycompany.mavenprojectpokusaj2.entity.impl.Grad;
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
public class GradRepository implements MyAppRepository<Grad, Long> {

    @PersistenceContext
    private EntityManager entityMenager;

    @Override
    public List<Grad> findAll() {
        return entityMenager.createQuery("SELECT g from Grad g", Grad.class).getResultList();
    }

    @Override
    public Grad findById(Long id) throws Exception {
        Grad g = entityMenager.find(Grad.class, id);
        if (g == null) {
            throw new Exception("Grad nije pronadjen");
        }

        return g;
    }

    @Override
    @Transactional
    public void save(Grad entity) {
        if (entity.getId() == null) {
            entityMenager.persist(entity);
        } else {
            entityMenager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Grad g = entityMenager.find(Grad.class, id);
        if (g != null) {
            entityMenager.remove(g);
        }
    }

}
