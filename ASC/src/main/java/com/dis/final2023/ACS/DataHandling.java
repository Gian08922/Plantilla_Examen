package com.dis.final2023.ACS;

import java.util.ArrayList;
import java.util.List;

public class DataHandling {
    ArrayList<ClassDato> Añadir (ClassDato nuevoCodigo){

        LectorJSON lector = new LectorJSON();                   //me declaro un objeto del tipo json
        ArrayList<ClassDato> usuario =  lector.leeFicheroJson();    //declar una lista de tipo classdato1 que busque al usuario
        usuario.add(nuevoCodigo);                               //Añado al nuevo usuario a la lista
        lector.EscribirEnJSON((List<ClassDato>) nuevoCodigo, "./src/main/resources/FicheroResultado.json");

        return usuario;                                         //devuelvo la lista
    }


    List<ClassDato> modificarElementoFichero (ClassDato nuevoCodigo , Long ip){

        LectorJSON lector = new LectorJSON();                   //me declaro un objeto del tipo json
         List<ClassDato> usuario =  lector.leeFicheroJson();    //declar una lista de tipo classdato1 que busque al usuario


        for(ClassDato clase : usuario){                        //me declaro un foreach para recorrer la lista con todos los elementos en busca del codigo de geometria que quiero
            if(clase.getIp_from().equals(ip)){         //si lo encuentro , modifico los sets
                clase.setIp_to(nuevoCodigo.getIp_to());
                clase.setCountry_code(nuevoCodigo.getCountry_code());
                clase.setCountry_name(nuevoCodigo.getCountry_name());
                clase.setRegion_name(nuevoCodigo.getRegion_name());
                clase.setCity_name(nuevoCodigo.getCity_name());
                clase.setLatitude(nuevoCodigo.getLatitude());
                clase.setLongitude(nuevoCodigo.getLongitude());
                clase.setZip_code(nuevoCodigo.getZip_code());
                clase.setTime_zone(nuevoCodigo.getTime_zone());

            }
        }

        lector.EscribirEnJSON(usuario,"./src/main/resources/LocalizaIP.json");            //Escribo en el fichero JSON


        return usuario;                //devuelvo la lista
    }

    public void eliminar(Long id) {
        LectorJSON lector = new LectorJSON();
        List<ClassDato> usuario = lector.leeFicheroJson();


        for (int i = 0; i < usuario.size(); i++) {
            if (usuario.get(i).getIp_from().equals(id)) {
                usuario.remove(i);
                break;
            }
        }
        lector.EscribirEnJSON(usuario, "./src/main/resources/LocalizaIP.json");
    }




}