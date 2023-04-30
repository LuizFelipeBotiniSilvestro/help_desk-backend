package com.luiz.help_desk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiz.help_desk.domain.Tecnico;
import com.luiz.help_desk.domain.dtos.TecnicoDTO;
import com.luiz.help_desk.repositories.TecnicoRepository;
import com.luiz.help_desk.services.exception.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository  repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	// Converte de Tecnico DTO para Tecnico
	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null); // Por segurança, por quê se ir valor do id para requisição, o banco de dados entende que é update.
		Tecnico newObj = new Tecnico(objDTO);	
		// Chamada Asyn
		return repository.save(newObj);
	}	
}
