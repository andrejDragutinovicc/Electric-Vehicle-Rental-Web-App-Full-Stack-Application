package com.mycompany.mavenprojectpokusaj2.controller;

import com.mycompany.mavenprojectpokusaj2.dto.impl.AuthResponse;
import com.mycompany.mavenprojectpokusaj2.dto.impl.LogInRequest;
import com.mycompany.mavenprojectpokusaj2.dto.impl.RegistrationRequest;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Korisnik;
import com.mycompany.mavenprojectpokusaj2.repository.impl.KorisnikRepository;
import com.mycompany.mavenprojectpokusaj2.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final KorisnikRepository korisnikRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(KorisnikRepository korisnikRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.korisnikRepository = korisnikRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegistrationRequest req) {

        if (korisnikRepository.existsByUsername(req.getUsername())) {
            return new AuthResponse(null, "Username already taken");
        }

        if (korisnikRepository.existsByEmail(req.getEmail())) {
            return new AuthResponse(null, "Email already used");
        }

        Korisnik k = new Korisnik();
        k.setUsername(req.getUsername());
        k.setEmail(req.getEmail());
        k.setPasswordHash(passwordEncoder.encode(req.getPassword()));

        korisnikRepository.save(k);

        String token = jwtUtil.generateToken(k.getUsername());

        return new AuthResponse(token, "OK");
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LogInRequest req) {

        Korisnik k = korisnikRepository.findByUsername(req.getUsername())
                .orElse(null);

        if (k == null) {
            return new AuthResponse(null, "Invalid credentials");
        }

        if (!passwordEncoder.matches(req.getPassword(), k.getPasswordHash())) {
            return new AuthResponse(null, "Invalid credentials");
        }

        String token = jwtUtil.generateToken(k.getUsername());

        return new AuthResponse(token, "OK");
    }
}