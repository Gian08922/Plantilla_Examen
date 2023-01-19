package ufv.dis.final2022test.bien;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Controller {


    @GetMapping("/NE")
    public ArrayList<Character> mayores() {
        //metodo para el Get Mayores
        Datahandling datahandling = new Datahandling();
        ArrayList<Character> list = datahandling.cogerlista();

        return list;
    }


    @PostMapping(path = "/NE",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> create(@RequestBody Inforecibida recibido) {
        //Metodo post para Zona Básica
        try {
                Datahandling datahandling = new Datahandling();
              Character chara = datahandling.getCharacter(recibido);

            return new ResponseEntity<>(chara, HttpStatus.ACCEPTED);
        } catch (java.lang.Exception ex) {

            ex.printStackTrace();
            return null;
        }
    }
}
