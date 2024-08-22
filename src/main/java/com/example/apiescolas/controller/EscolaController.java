package com.example.apiescolas.controller;

import com.example.apiescolas.model.Escola;
import com.example.apiescolas.service.EscolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/escolas")
public class EscolaController {

    @Autowired
    private EscolaService escolaService;

    @GetMapping
    public List<Escola> getEscolasDeSaoVicente() {
        return escolaService.getEscolasDeSaoVicente();
    }

    @PostMapping
    public Escola addEscola(@RequestBody Escola escola) {
        return escolaService.saveEscola(escola);
    }
}
