package org.vaadin.example;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class GreetService implements Serializable {

    private static final String urlPrefix = "http://localhost:8080/";

    public ArrayList<ClassDato> leeCasos() throws URISyntaxException, IOException, InterruptedException {
        IpApi api = new IpApi();
        String resultsAPI = api.getDatos();


        ArrayList<ClassDato> listaDatos = new Gson().fromJson(resultsAPI, new TypeToken<ArrayList<ClassDato>>() {
        }.getType());;

        return listaDatos;
    }

    public ArrayList<ClassDato> Post(ClassDato Datos) throws URISyntaxException, IOException, InterruptedException {
        IpApi api = new IpApi();
        ArrayList<ClassDato> updatedSalud = api.PostCaso(Datos);
        return updatedSalud;
    }

    public ArrayList<ClassDato> Put(ClassDato Datos, Long ip) throws URISyntaxException, IOException, InterruptedException {
        IpApi api = new IpApi();
        ArrayList<ClassDato> updatedSalud = api.PutCaso(Datos, ip);
        return updatedSalud;
    }



}




/*
    public ClassDato Post(ClassDato Datos) throws URISyntaxException, IOException, InterruptedException {
        IpApi api = new IpApi();
        ClassDato updatedSalud = api.PostCaso(Datos);
        return updatedSalud;
    }


 */