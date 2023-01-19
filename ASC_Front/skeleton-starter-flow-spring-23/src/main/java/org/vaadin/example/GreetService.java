package org.vaadin.example;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.google.gson.Gson;
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

}
