package com.dis.final2023.ACS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LectorJSON {


    //lector json sin objeto, a pelo
    public ArrayList<ClassDato> leeFicheroJson() {
        try {
            //lee el fichero que le pasemos y lo carga en un reader
            Reader reader = Files.newBufferedReader(Paths.get("./src/main/resources/LocalizaIP.json"));
            // convierte el array JSON a un arraylist de users
            ArrayList<ClassDato> lista=new Gson().fromJson(reader, new TypeToken<ArrayList<ClassDato>>() {}.getType());
            reader.close();// close reader
            return lista;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }



    //lector json con objeto
    public List<ClassDato1> leeFicheroJsonCon() {
        try {
            //lee el fichero que le pasamos y lo carga en un reader
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("FicheroConObjeto.json")));

            // convierte el array JSON a un arraylist de users
            ObjetoJson listaZonasBasicasSalud = new Gson().fromJson(reader, new TypeToken<ObjetoJson>() {}.getType());

            reader.close();// close reader
            return listaZonasBasicasSalud.getData();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>(); //si no ha leido nada, devuelve un array vacio
        }
    }

    public void EscribirEnJSON(List<ClassDato> usuario,String fichero) {

        try {
            ObjectMapper mapper = new ObjectMapper();               //me creo un objeto de tipo mapeo
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(fichero), usuario);         //paso el objeto y el enlace donde va a ir el archivo

            System.out.println("Se escribio con exito");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
