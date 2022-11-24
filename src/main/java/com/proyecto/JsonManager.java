package com.proyecto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonManager {
    Object jsonCrudo;
    JSONObject jsonObj;
    JSONArray jsonArray;
    
    public JSONArray readJson(String ruta) throws FileNotFoundException, IOException, ParseException{
         jsonCrudo = new JSONParser().parse(new FileReader(ruta));

          jsonObj = (JSONObject) jsonCrudo;
          jsonArray  = (JSONArray) jsonObj.get("employee");

         return jsonArray;
    }

    public void jsonValidation(JSONArray jsonArray, String key) throws ValidationException{
        String error = "";

        if(!jsonObj.containsKey(key)){
           throw new RuntimeException("No coincide la llave employee");
        }

        for (Object object : jsonArray) {
            JSONObject jsonObjectAux = (JSONObject)object;
            if (jsonObjectAux.get("id") == null) {
                error = "id";
            } else if (jsonObjectAux.get("firstName") == null) {
                error = "firstName";
            } else if (jsonObjectAux.get("lastName") == null) {
                error = "lastName";
            } else if (jsonObjectAux.get("photo") == null) {
                error = "photo";
            }

            if (!error.equals("")) {
                throw new ValidationException(error);
            }
        }
    }    
}
