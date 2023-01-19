package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class IpApi {
    private static final String urlPrefix = "http://localhost:8080/%s/%s";

    public String getDatos() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "Fichero1","");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        return response.body();
    }



public ArrayList<ClassDato> PostCaso(ClassDato Datos) throws URISyntaxException, IOException, InterruptedException {
    String fullUrl = String.format(urlPrefix, "Datos","");
    HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(fullUrl))
            .setHeader("Content-type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(Datos)))
            .build();

    HttpResponse<String> response = HttpClient
            .newBuilder()
            .build()
            .send(request, HttpResponse.BodyHandlers.ofString());

    try{


            Type listType = new TypeToken<ArrayList<ClassDato>>(){}.getType();

            ArrayList<ClassDato> lista = new Gson().fromJson(response.body(), listType);



        return lista;
    }catch(JsonSyntaxException e){
        e.printStackTrace();
        return null;
    }
}



    public ArrayList<ClassDato> PutCaso(ClassDato Datos, Long ip) throws URISyntaxException, IOException, InterruptedException {

        String fullUrl = String.format(urlPrefix,ip, "");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .setHeader("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(new Gson().toJson(Datos)))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        try{
            Type listType = new TypeToken<ArrayList<ClassDato>>(){}.getType();

            ArrayList<ClassDato> lista = new Gson().fromJson(response.body(), listType);

            return lista;
        }catch(JsonSyntaxException e){
            e.printStackTrace();
            return null;
        }

    }

    public String DeleteCaso(Long ip) throws URISyntaxException, IOException, InterruptedException {

        String fullUrl = String.format(urlPrefix,ip, "");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .DELETE()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        return response.body();

    }

}



/*
    public ClassDato PostCaso(ClassDato Datos) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "Datos","");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .setHeader("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(Datos)))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        try{

            ClassDato datos = new Gson().fromJson(response.body(), ClassDato.class);


            return datos;
        }catch(JsonSyntaxException e){
            e.printStackTrace();
            return null;
        }
    }
*/