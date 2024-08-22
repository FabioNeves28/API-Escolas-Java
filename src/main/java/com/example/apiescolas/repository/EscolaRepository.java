package com.example.apiescolas.repository;

import com.example.apiescolas.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EscolaRepository extends JpaRepository<Escola, Long> {
    List<Escola> findByCidadeAndEstado(String cidade, String estado);
}
