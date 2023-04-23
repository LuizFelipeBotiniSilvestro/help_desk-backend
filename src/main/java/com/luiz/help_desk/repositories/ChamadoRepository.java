package com.luiz.help_desk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiz.help_desk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
