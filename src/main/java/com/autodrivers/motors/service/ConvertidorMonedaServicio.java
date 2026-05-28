package com.autodrivers.motors.service;


import com.autodrivers.motors.dto.conversor.Conversion;
import com.autodrivers.motors.dto.conversor.TasaCambio;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConvertidorMonedaServicio {

    public TasaCambio consultarMonedaCambio(String paisOrigen, String paisDestino, double valor){

        try {
            URI direccion = URI.create("https://v6.exchangerate-api.com/v6/1dc20381986410d20e8cefe5/pair/"+
                    paisOrigen + "/" + paisDestino + "/" + valor);


            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();


            Conversion conversion =  new Gson().fromJson(json, Conversion.class);
            System.out.println("conversion: " + conversion);
            return new TasaCambio(conversion);
        } catch (NumberFormatException | IOException | InterruptedException e){
            System.out.println("Ocurrio un Error!");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println("Error en la URI, Verifique la dirección.");
        }

        return new TasaCambio(paisOrigen, paisDestino, 0.0, 0.0);
    }
}
