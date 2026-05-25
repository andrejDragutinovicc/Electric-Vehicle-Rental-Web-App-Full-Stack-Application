package com.mycompany.mavenprojectpokusaj2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.mavenprojectpokusaj2.dto.impl.KorisnikDto;
import com.mycompany.mavenprojectpokusaj2.servise.KorisnikServis;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/korisnici")
public class KorisnikController {
	
	private final KorisnikServis service;
	
	 public KorisnikController(KorisnikServis s) {
		service = s;
	}

	 @GetMapping("/search")
	 public List<KorisnikDto> findByUsername(@RequestParam String username){
		 
		 return service.findByUsername(username);
		 
	 }
	 
	 
}
