package com.luiz.help_desk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiz.help_desk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
