package com.example.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ConnectionApi <T> {

    private String Url;
    private String ApiKey;
    private Gson gson;
    private final Class<T> ClassType;

    public ConnectionApi(String url, String apikey, Class<T> classType){

        if(null == url || url.strip().isEmpty())
            throw  new IllegalArgumentException("Url Invalida : " + (url == null ? "null" : url));


        if(null == apikey || apikey.strip().isEmpty())
            throw  new IllegalArgumentException("apikey Invalida : " + (apikey == null ? "null" : apikey));

        Url = url;
        ApiKey = apikey;
        ClassType = classType;

        gson = new Gson();

    }

    private String peticionHttp () throws Exception{

        try{

            // Es como abrir el navegador en tu pc
            HttpClient client = HttpClient.newHttpClient();

            // Escribir la url en la barra de buscar
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Url))
                    .GET()
                    .build();

            // Dar Enter en el navegador con la url especificada y traer la respuesta en formato String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Validar que el status code sea exitoso
            if (response.statusCode() != 200){
                throw new RuntimeException("Error Http: " + response.statusCode());
            }

            return response.body();

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    public List<T> obtenerData () throws Exception {

        JsonArray arrayData;
        JsonObject jsonObject;
        String jsonRespuesta = peticionHttp();

        // 1.Convertir el String a objeto Json
        JsonElement root = gson.fromJson(jsonRespuesta, JsonElement.class);

        // 2.Extraer elementos
        if (ApiKey == null || ApiKey.strip().isEmpty()){
            // Si la api no trae la llave para extraer la información
            /*
                {

                 [id: 1, ….]
                 [id: 2,...]

                }
             */
            // Se mapea los datos directamente a Array
            arrayData = root.getAsJsonArray();
        }
        else{
            // Si la api trae la(s) llave(s), extraemos primero todos los objetos (items, meta, results, etc)
            /*
            {
              "items": [
                {
                  "id": 1,
                  "name": "Goku",
                  "ki": "60.000.000",
             */
            jsonObject = root.getAsJsonObject();

            // Luego de extraer todos los objetos de la api, indicamos cual es la llave de interes
            arrayData = jsonObject.getAsJsonArray(ApiKey);
        }

        // Conexion parametrica a cualquier Record
        var listaElementosParametrizada = TypeToken.getParameterized(List.class, ClassType).getType();

        return gson.fromJson(arrayData, listaElementosParametrizada);
    }

}