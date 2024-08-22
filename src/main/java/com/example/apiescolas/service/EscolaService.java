package com.example.apiescolas.service;

import com.example.apiescolas.model.Escola;
import com.example.apiescolas.repository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscolaService {

    @Autowired
    private EscolaRepository escolaRepository;

    public List<Escola> getEscolasDeSaoVicente() {
        return escolaRepository.findByCidadeAndEstado("São Vicente", "SP");
    }

    public Escola saveEscola(Escola escola) {
        return escolaRepository.save(escola);
    }
}
