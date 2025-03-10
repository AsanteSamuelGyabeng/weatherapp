package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.logging.Logger;

@RestController
public class WeatherController {

    private final String RAPIDAPI_KEY = "8a5ef04921msh5c081b622d77445p1c2efbjsn759cb78aaa56";
    private final String RAPIDAPI_HOST = "open-weather13.p.rapidapi.com";
    private static final Logger logger = Logger.getLogger(WeatherController.class.getName());

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city) {
        try {
            if (city == null || city.isBlank()) {
                return "{\"error\": \"City name cannot be empty\"}";
            }
            logger.info("Fetching weather for city: " + city);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("https://%s/city/%s/EN", RAPIDAPI_HOST, city)))
                    .header("x-rapidapi-key", RAPIDAPI_KEY)
                    .header("x-rapidapi-host", RAPIDAPI_HOST)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("API response: " + response.body());
            return response.body();
        } catch (Exception e) {
            logger.severe("Failed to fetch weather data: " + e.getMessage());
            return String.format("{\"error\": \"Failed to fetch weather data for %s\"}", city);
        }
    }
}