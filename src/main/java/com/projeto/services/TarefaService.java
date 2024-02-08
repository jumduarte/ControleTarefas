package com.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.entities.Tarefa;
import com.projeto.repository.TarefaRepository;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    
    @Autowired
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa getTarefaById(Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        return tarefa.orElse(null);
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa updateTarefa(Long id, Tarefa updatedTarefa) {
        Optional<Tarefa> existingTarefa = tarefaRepository.findById(id);
        if (existingTarefa.isPresent()) {
            updatedTarefa.setId(id);
            return tarefaRepository.save(updatedTarefa);
        }
        return null;
    }
    public boolean deleteTarefa(Long id) {
        Optional<Tarefa> existingTarefa = tarefaRepository.findById(id);
        if (existingTarefa.isPresent()) {
        	tarefaRepository.deleteById(id);
           return true;
        }
        return false;
    }
}