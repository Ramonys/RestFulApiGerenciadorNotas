package com.gerenciadornotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciadornotas.entity.Nota;

public interface NotaRepository extends JpaRepository<Nota, Integer> {

}
