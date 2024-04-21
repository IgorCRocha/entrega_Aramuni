package com.example.Clima_RestApi.repository;

import com.example.Clima_RestApi.model.ClimaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ClimaRepository extends MongoRepository<ClimaEntity, String> {
// Métodos de CRUD já estão disponíveis
    //findAll, findById, save, deleteById

    // Utilizando consultas personalizadas
    List<ClimaEntity> findByCountryIgnoreCase(String country);
    List<ClimaEntity> findByDateIgnoreCase(String date);
    List<ClimaEntity> findByTextIgnoreCase(String Text);




}
