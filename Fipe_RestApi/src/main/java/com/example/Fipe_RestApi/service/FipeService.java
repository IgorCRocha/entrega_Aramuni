package com.example.Fipe_RestApi.service;

import com.example.Fipe_RestApi.model.FipeEntity;
import com.example.Fipe_RestApi.repository.FipeRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FipeService {
    @Autowired
    private FipeRepository fipeRepository;
    private FipeEntity fipeEntity;

    private String consultarURL(String apiUrl) {
        String dados = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            dados = responseEntity.getBody();
            List<FipeEntity> fipeEntities = coletar(dados);
            salvar(fipeEntities);
        } else {
            dados = "Falha ao obter dados. Código de status: " + responseEntity.getStatusCode();
        }
        return dados;
    }

    public String consultarMarcas() {
        return consultarURL("https://parallelum.com.br/fipe/api/v1/carros/marcas");
    }

    public String consultarModelos(int id) {
        return consultarURL("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + id + "/modelos");
    }

    public String consultarAnos(int marca, int modelo) {
        return consultarURL("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marca + "/modelos/" + modelo + "/anos");
    }

    public String consultarValor(int marca, int modelo, String ano) {
        return consultarURL("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + marca + "/modelos/" + modelo + "/anos/" + ano);
    }
    private List<FipeEntity> coletar(String dados) {
        List<FipeEntity> fipeEntities = new ArrayList<>();

        ObjectMapper objeto = new ObjectMapper();
        try {
            JsonNode root = objeto.readTree(dados);
            if (root.isArray()) {
                for (JsonNode node : root) {
                    FipeEntity fipeEntity = new FipeEntity();
                    fipeEntity.setCodigo(node.get("codigo").asText());
                    fipeEntity.setNome(node.get("nome").asText());
                    fipeEntity.setModelo(node.get("modelo").asText());
                    fipeEntities.add(fipeEntity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fipeEntities;
    }

    private void salvar(List<FipeEntity> fipeEntities) {
        fipeRepository.saveAll(fipeEntities);
    }

    public List<FipeEntity> obterTodos() {
        return fipeRepository.findAll();
    }

    public FipeEntity inserir(FipeEntity fipe) {
        return fipeRepository.save(fipe);
    }

    public FipeEntity obterPorId(String id) {
        return fipeRepository.findById(id).orElse(null);
    }

    public FipeEntity atualizar(String id, FipeEntity Fipe) {

        FipeEntity fipe1 = fipeRepository.findById(id).orElse(null);

        if (fipe1 != null) {
            fipe1.setCodigo(Fipe.getCodigo());
            fipe1.setNome(Fipe.getNome());
            fipe1.setModelo(Fipe.getModelo());
            return fipeRepository.save(fipe1);
        } else {
            return null;
        }
    }
    public void excluir(String id) {
        fipeRepository.deleteById(id);
    }
}
