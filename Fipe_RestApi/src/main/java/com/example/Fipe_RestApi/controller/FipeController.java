package com.example.Fipe_RestApi.controller;

import com.example.Fipe_RestApi.model.FipeEntity;
import com.example.Fipe_RestApi.service.FipeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class FipeController {
    FipeService service = new FipeService();

    @GetMapping("/marcas")
    public String consultarMarcas() {
        return service.consultarMarcas();
    }

    @GetMapping("/modelos/{marca}")
    public String consultarModelos(@PathVariable int marca) {
        return service.consultarModelos(marca);
    }

    @GetMapping("/anos/{marca}/{modelo}")
    public String consultarAnos(@PathVariable int marca, @PathVariable int modelo) {
        return service.consultarAnos(marca, modelo);
    }

    @GetMapping("/valor/{marca}/{modelo}/{ano}")
    public String consultarValor(@PathVariable int marca, @PathVariable int modelo, @PathVariable String ano) {
        return service.consultarValor(marca, modelo, ano);
    }
    @GetMapping("/todos")
    public List<FipeEntity> obterTodos() {
        return service.obterTodos();
    }
    @PostMapping
    public FipeEntity inserir(@RequestBody FipeEntity modelo){
        return service.inserir(modelo);
        }

        @PutMapping("{id}")
        public FipeEntity atualizar(@PathVariable String id, @RequestBody FipeEntity fipeEntity){
            return service.atualizar(id, fipeEntity);
        }

        @DeleteMapping("{id}")
        public void excluir(@PathVariable String id) {
            service.excluir(id);
        }
}
