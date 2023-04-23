package com.luiz.help_desk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiz.help_desk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

}
