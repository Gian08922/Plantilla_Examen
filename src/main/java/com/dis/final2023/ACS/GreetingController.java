package com.dis.final2023.ACS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {


    @GetMapping("/Fichero1")                                            //aqui hago la llamada al enlace de la pagina web
        public ArrayList<ClassDato> Dato(){
        ArrayList<ClassDato> listaDato = new LectorJSON().leeFicheroJson();


        return listaDato;
    }



    @GetMapping("/Fichero2")
    public List<ClassDato1> Dato2() {
        List<ClassDato1> lector = new LectorJSON().leeFicheroJsonCon();
        return  lector;
    }

    /*Aqui voy a realizar el post para modificar un elemento de la lista*/
    @PostMapping(path = "Datos",
            consumes  = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClassDato>> create (@RequestBody ClassDato  dato){

        DataHandling data = new DataHandling();
        List<ClassDato> Clase = data.Añadir(dato);
        return new ResponseEntity<>(Clase, HttpStatus.CREATED);
    }



    @PutMapping(path = "{id}",
            consumes  = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity <List<ClassDato>> modificar (@PathVariable("id") Long ip,@RequestBody ClassDato classDato) {

        DataHandling dato = new DataHandling();           //Me creo un objeto de tipo datahandling para llamar a la funcion modificarElementoFichero() mas tarde
       List<ClassDato> datoClase ;
        return new ResponseEntity<>(dato.modificarElementoFichero(classDato ,ip), HttpStatus.CREATED);        //Devuelvo el response entity
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            DataHandling dato = new DataHandling();
            dato.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}