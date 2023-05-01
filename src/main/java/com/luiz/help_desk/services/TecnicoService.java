package com.luiz.help_desk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luiz.help_desk.domain.Pessoa;
import com.luiz.help_desk.domain.Tecnico;
import com.luiz.help_desk.domain.dtos.TecnicoDTO;
import com.luiz.help_desk.repositories.PessoaRepository;
import com.luiz.help_desk.repositories.TecnicoRepository;
import com.luiz.help_desk.services.exception.DataIntegrityViolationException;
import com.luiz.help_desk.services.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository  repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
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
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		
		// Chama a função que validade 
		validaPorCpfEmail(objDTO);
		
		Tecnico newObj = new Tecnico(objDTO);	
		// Chamada Asyn
		return repository.save(newObj);
	}

	// Update
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id); 
		Tecnico oldObj = findById(id);
		validaPorCpfEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return repository.save(oldObj);
	}	
	
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");	
		} 
		repository.deleteById(id);
	}
	
	private void validaPorCpfEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
		
	}
}
