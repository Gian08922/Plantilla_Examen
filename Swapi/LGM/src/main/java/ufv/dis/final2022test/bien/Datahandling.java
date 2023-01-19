package ufv.dis.final2022test.bien;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import com.google.gson.Gson;
public class Datahandling {
    private static final String urlPrefix = "https://swapi.dev/api/%s/%s";
    static ArrayList<Character> lista=new ArrayList<>();
    Character getCharacter(Inforecibida x) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, x.getEntity(), x.getId());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        Gson gson = new Gson();

        Character chara = gson.fromJson(response.body(), Character.class);
        gestionarlista(chara);

        return chara;
    }
  static void gestionarlista(Character ch){
     lista.add(ch);
     Json json = new Json();
     json.WriteJson(lista);
 }
    static ArrayList<Character> cogerlista(){
        return lista;

    }
    static boolean comprobarelemento(Character ch){
        for(int i=0; i< lista.size();i++){
            if(ch == lista.get(i)){
                return true;
            }
        }
        return false;
    }

}
