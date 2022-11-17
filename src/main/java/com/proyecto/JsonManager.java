package com.proyecto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonManager {
    Object jsonCrudo;
    
    public JSONObject readJson(String ruta) throws FileNotFoundException, IOException, ParseException{
         jsonCrudo = new JSONParser().parse(new FileReader(ruta));

         JSONObject jsonObj = (JSONObject) jsonCrudo;

         return jsonObj;
    }

    public void jsonValidation(JSONObject JSON, String key){

        if(!JSON.containsKey(key)){
           throw new RuntimeException("no coincide la llave");
        }
    }
}
