package com.example.apiescolas.APIGoogle;

import com.example.apiescolas.model.Escola;
import com.example.apiescolas.repository.EscolaRepository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GooglePlacesService {

    private final String API_KEY = "AIzaSyBM1YbDk5qEBdkwyl05JB5uZIgZ2j1txsc";
    private final String GOOGLE_PLACES_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

    @Autowired
    private EscolaRepository escolaRepository;

    public void updateEscolasFromGooglePlaces() {
        String url = String.format("%s?location=-23.9574,-46.3883&radius=5000&type=school&key=%s", GOOGLE_PLACES_URL, API_KEY);
        List<Escola> escolas = fetchAllResults(url);
        escolaRepository.saveAll(escolas);
    }

    private List<Escola> fetchAllResults(String url) {
        List<Escola> escolas = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String nextPageToken = null;

        do {
            if (nextPageToken != null) {
                url = url + "&pagetoken=" + nextPageToken;
            }
            String response = restTemplate.getForObject(url, String.class);
            JSONObject jsonResponse = new JSONObject(response);

            JSONArray results = jsonResponse.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject escolaJson = results.getJSONObject(i);
                Escola escola = new Escola();
                escola.setNome(escolaJson.getString("name"));
                escola.setEndereco(escolaJson.optString("vicinity", "Endereço não disponível"));
                escola.setCidade("São Vicente");
                escola.setEstado("SP");
                escolas.add(escola);
            }

            nextPageToken = jsonResponse.optString("next_page_token", null);

            try {
                if (nextPageToken != null) {
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (nextPageToken != null);

        return escolas;
    }
}
