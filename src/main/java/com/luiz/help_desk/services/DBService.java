package com.luiz.help_desk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiz.help_desk.domain.Chamado;
import com.luiz.help_desk.domain.Cliente;
import com.luiz.help_desk.domain.Tecnico;
import com.luiz.help_desk.domain.enums.Perfil;
import com.luiz.help_desk.domain.enums.Prioridade;
import com.luiz.help_desk.domain.enums.Status;
import com.luiz.help_desk.repositories.ChamadoRepository;
import com.luiz.help_desk.repositories.ClienteRepository;
import com.luiz.help_desk.repositories.TecnicoRepository;

@Service
public class DBService {

	// Injetando depêndencias destas interfaces.
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	

	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Luiz Felipe", "11765549990", "email@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Gabriel", "11111111111", "email1@gmail.com", "123");
		tec1.addPerfil(Perfil.TECNICO);
		Tecnico tec3 = new Tecnico(null, "Vitor", "22222222222", "email2@gmail.com", "123");
		tec1.addPerfil(Perfil.TECNICO);
		Tecnico tec4 = new Tecnico(null, "Valdir", "33333333333", "email3@gmail.com", "123");
		tec1.addPerfil(Perfil.TECNICO);
		
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "69315400925", "torvalds@email.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}
}
