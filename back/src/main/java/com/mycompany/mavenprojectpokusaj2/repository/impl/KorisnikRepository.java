package com.mycompany.mavenprojectpokusaj2.repository.impl;

import com.mycompany.mavenprojectpokusaj2.entity.impl.Korisnik;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    // 🔍 Pronađi korisnika po username-u (za login)
    Optional<Korisnik> findByUsername(String username);

    // 🔍 Pronađi korisnika po email-u
    Optional<Korisnik> findByEmail(String email);

    // ✔ Provera da li username već postoji
    boolean existsByUsername(String username);

    // ✔ Provera da li email već postoji
    boolean existsByEmail(String email);
}