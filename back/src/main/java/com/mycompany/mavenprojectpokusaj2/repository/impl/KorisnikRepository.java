package com.mycompany.mavenprojectpokusaj2.repository.impl;

import com.mycompany.mavenprojectpokusaj2.entity.impl.Korisnik;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {


    Optional<Korisnik> findByUsername(String username);

    List<Korisnik> findByUsernameContainingIgnoreCase(String username);

    Optional<Korisnik> findByEmail(String email);


    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}