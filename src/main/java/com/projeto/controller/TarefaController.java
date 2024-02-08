package com.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.entities.Tarefa;
import com.projeto.services.TarefaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Tarefas", description = "API REST DE GERENCIAMENTO DE Tarefas")
@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    
    private final TarefaService tarefaService;
    
    @Autowired
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Localiza Tarefa por ID")
    public ResponseEntity<Tarefa> getProductById(@PathVariable Long id) {
    	Tarefa tarefa = tarefaService.getTarefaById(id);
        if (tarefa != null) {
            return ResponseEntity.ok(tarefa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    @Operation(summary = "Apresenta todos os Tarefas")
    public ResponseEntity<List<Tarefa>> getAllTarefas() {
        List<Tarefa> tarefas = tarefaService.getAllTarefas();
        return ResponseEntity.ok(tarefas);
    }
    @PostMapping("/")
    @Operation(summary = "Cadastra um Tarefa")
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody @Valid Tarefa tarefa) {
    	Tarefa criarTarefa = tarefaService.salvarTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarTarefa);
    }
   

    @PutMapping("/{id}")
    @Operation(summary = "Altera a Tarefa")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody @Valid Tarefa tarefa) {
    	Tarefa updatedTarefa = tarefaService.updateTarefa(id, tarefa);
        if (updatedTarefa != null) {
            return ResponseEntity.ok(updatedTarefa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta a Tarefa")
    public ResponseEntity<String> deleteTarefa(@PathVariable Long id) {
        boolean deleted = tarefaService.deleteTarefa(id);
        if (deleted) {
        	 return ResponseEntity.ok().body("A tarefa foi excluído com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}