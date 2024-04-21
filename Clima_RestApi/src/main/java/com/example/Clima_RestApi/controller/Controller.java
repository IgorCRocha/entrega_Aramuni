package com.example.Clima_RestApi.controller;

import com.example.Clima_RestApi.service.ClimaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Clima_RestApi.model.ClimaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clima")
public class Controller {

    @Autowired
    private ClimaService service;

   @GetMapping("/clima")
    public String preverTempo(){
        return service.preverTempo()+ "Dados salvos no BD";
    }

    @GetMapping("/{id}")
    public ClimaEntity obterPorId(@PathVariable String id) {
        return service.obterPorId(id);
    }

    @PostMapping
    public ClimaEntity inserir (@RequestBody ClimaEntity clima) {return service.inserir(clima);}

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable String id) {
        service.excluir(id);
    }

    @PutMapping("/{id}")
    public ClimaEntity atualizar(@PathVariable String id, @RequestBody ClimaEntity clima) {
        return service.atualizar(id,clima);
    }
    @GetMapping("/todos")
    public List<ClimaEntity> obterTodos(){
       return service.obterTodos();
    }

}
