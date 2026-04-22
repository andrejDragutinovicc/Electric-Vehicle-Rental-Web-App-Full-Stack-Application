package com.mycompany.mavenprojectpokusaj2.servise;

import com.mycompany.mavenprojectpokusaj2.entity.impl.Korisnik;
import com.mycompany.mavenprojectpokusaj2.repository.impl.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // POVEŽI sa tvojom implementacijom repo-a
    @Autowired
    private KorisnikRepository korisnikRepository; // prilagodi ako imaš drugi repo

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik k = korisnikRepository.findByUsername(username).orElse(null);
        if (k == null) {
            throw new UsernameNotFoundException("Korisnik nije pronađen: " + username);
        }
        // pretpostavljam da password field/ime: passwordHash
        return User.builder()
                .username(k.getUsername())
                .password(k.getPasswordHash()) // ili getPassword()
                .roles(k.getRole() != null ? k.getRole().name() : "USER")
                .build();
    }
}
