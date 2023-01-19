package ufv.dis.final2022test.bien;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Json {
    public void WriteJson(ArrayList<Character> lista) {
        String path = "./src/main/resources/datos/Characters.json";
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(path), lista);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}

