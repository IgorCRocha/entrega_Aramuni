package com.example.Clima_RestApi.service;

import com.example.Clima_RestApi.model.ClimaEntity;
import com.example.Clima_RestApi.repository.ClimaRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClimaService {

    @Autowired

    private ClimaRepository climaRepository;

    public ClimaEntity climaEntity;


    public String preverTempo() {
        String dadosMeteorologicos = "";
        //Nível BR
        String apiUrl = "https://apiadvisor.climatempo.com.br/api/v1/anl/synoptic/locale/BR?token=9fe25332679ebce952fdd9f7f9a83c3e";
        //Nível BH
        //String apiUrl = "http://apiadvisor.climatempo.com.br/api/v1/weather/locale/6879/current?token=9fe25332679ebce952fdd9f7f9a83c3e";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            dadosMeteorologicos = responseEntity.getBody();
            List<ClimaEntity> climaEntities = coletar(dadosMeteorologicos);
            salvar(climaEntities);
        } else {
            dadosMeteorologicos = "Falha ao obter dados meteorológicos. Código de status: " + responseEntity.getStatusCode();
        }
        return dadosMeteorologicos;
    }


    private List<ClimaEntity> coletar(String dadosMeteorologicos) {
        List<ClimaEntity> climaEntities = new ArrayList<>();

        ObjectMapper objeto = new ObjectMapper();
        try {
            JsonNode root = objeto.readTree(dadosMeteorologicos);
            if (root.isArray()) {
                for (JsonNode node : root) {
                    ClimaEntity climaEntity = new ClimaEntity();
                    climaEntity.setCountry(node.get("country").asText());
                    climaEntity.setDate(node.get("date").asText());
                    climaEntity.setText(node.get("text").asText());
                    climaEntities.add(climaEntity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return climaEntities;
    }

    private void salvar(List<ClimaEntity> climaEntities) {
        climaRepository.saveAll(climaEntities);
    }

    public List<ClimaEntity> obterTodos() {
        return climaRepository.findAll();
    }

    public ClimaEntity inserir(ClimaEntity clima) {
        return climaRepository.save(clima);
    }

    public ClimaEntity obterPorId(String id) {
        return climaRepository.findById(id).orElse(null);
    }

    public ClimaEntity atualizar(String id, ClimaEntity Clima) {

        ClimaEntity clima1 = climaRepository.findById(id).orElse(null);

        if (clima1 != null) {
            clima1.setId(Clima.getId());
            clima1.setCountry(Clima.getCountry());
            clima1.setDate(Clima.getDate());
            clima1.setText(Clima.getText());
            return climaRepository.save(clima1);
        } else {
            return null;
        }
    }
    public void excluir(String id) {
        climaRepository.deleteById(id);
    }
}