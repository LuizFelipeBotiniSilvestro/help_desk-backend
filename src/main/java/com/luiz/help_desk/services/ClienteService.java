package com.luiz.help_desk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luiz.help_desk.domain.Cliente;
import com.luiz.help_desk.domain.Pessoa;
import com.luiz.help_desk.domain.dtos.ClienteDTO;
import com.luiz.help_desk.repositories.ClienteRepository;
import com.luiz.help_desk.repositories.PessoaRepository;
import com.luiz.help_desk.services.exception.DataIntegrityViolationException;
import com.luiz.help_desk.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository  repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	// Converte de Cliente DTO para Cliente
	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null); // Por segurança, por quê se ir valor do id para requisição, o banco de dados entende que é update.
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		
		// Chama a função que validade 
		validaPorCpfEmail(objDTO);
		
		Cliente newObj = new Cliente(objDTO);	
		// Chamada Asyn
		return repository.save(newObj);
	}

	// Update
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id); 
		Cliente oldObj = findById(id);
		validaPorCpfEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}	
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");	
		} 
		repository.deleteById(id);
	}
	
	private void validaPorCpfEmail(ClienteDTO objDTO) {
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
