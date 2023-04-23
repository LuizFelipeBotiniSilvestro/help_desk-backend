package com.luiz.help_desk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luiz.help_desk.domain.Chamado;
import com.luiz.help_desk.domain.Cliente;
import com.luiz.help_desk.domain.Tecnico;
import com.luiz.help_desk.domain.enums.Perfil;
import com.luiz.help_desk.domain.enums.Prioridade;
import com.luiz.help_desk.domain.enums.Status;
import com.luiz.help_desk.repositories.ChamadoRepository;
import com.luiz.help_desk.repositories.ClienteRepository;
import com.luiz.help_desk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpDeskApplication implements CommandLineRunner{

	// Injetando depências destas interfaces.
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Luiz Felipe", "11765549990", "email@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "69315400925", "torvalds@email.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}

}
