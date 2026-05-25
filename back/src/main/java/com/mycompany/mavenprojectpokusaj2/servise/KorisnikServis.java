package com.mycompany.mavenprojectpokusaj2.servise;

import java.util.Collection;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.mavenprojectpokusaj2.dto.impl.KorisnikDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Korisnik;
import com.mycompany.mavenprojectpokusaj2.mapper.impl.KorisnikMapper;
import com.mycompany.mavenprojectpokusaj2.repository.impl.KorisnikRepository;

@Service
public class KorisnikServis {

	private final KorisnikRepository repository;
	private final KorisnikMapper mapper;
	
	@Autowired
	public KorisnikServis(KorisnikRepository r,KorisnikMapper m) {
		repository = r;
		mapper = m;
	}
	
	public List<KorisnikDto> findByUsername(String name){
		
		List<Korisnik> korisnici = repository.findByUsernameContainingIgnoreCase(name);
		return korisnici.stream().map(mapper::toDto).collect(Collectors.toList());
		
		
	}
	
	
	
}
