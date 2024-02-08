package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.entities.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}