package com.luiz.help_desk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luiz.help_desk.domain.Tecnico;
import com.luiz.help_desk.domain.dtos.TecnicoDTO;
import com.luiz.help_desk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	// Significa que quero representar toda resposta HTTP
	
	@Autowired
	private TecnicoService service;
	
	// localhost:8080/tecnicos/1
	
	// Entendendo a linha: return ResponseEntity.ok().body(new TecnicoDTO(obj));
	
	// Classe tecnico DTO recebe e monta o obj para ser retornado (separando entidade pessoa do tecnico (mais segurança))
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico obj = this.service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
		
	}
	

}
