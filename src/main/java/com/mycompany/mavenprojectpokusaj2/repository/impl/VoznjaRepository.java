/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.repository.impl;

import com.mycompany.mavenprojectpokusaj2.entity.impl.Vozilo;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Voznja;
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
public class VoznjaRepository implements MyAppRepository<Voznja, Long> {

    @PersistenceContext
    private EntityManager entityMenager;

    public List<Voznja> findAllByKorisnikUsername(String username) {
        return entityMenager.createQuery(
                "SELECT v FROM Voznja v WHERE v.korisnik.username = :username",
                Voznja.class
        )
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public List<Voznja> findAll() {
        return entityMenager.createQuery("SELECT vz from Voznja vz", Voznja.class).getResultList();
    }

    @Override
    public Voznja findById(Long id) throws Exception {
        Voznja v = entityMenager.find(Voznja.class, id);
        if (v == null) {
            throw new Exception("Voznja nije pronadjen");
        }

        return v;
    }

    @Override
    @Transactional
    public void save(Voznja entity) {
        if (entity.getId() == null) {
            entityMenager.persist(entity);
        } else {
            entityMenager.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Voznja v = entityMenager.find(Voznja.class, id);
        if (v != null) {
            entityMenager.remove(v);
        }
    }

}
