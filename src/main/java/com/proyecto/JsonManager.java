package com.proyecto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.proyecto.Exceptions.ValidationException;

public class JsonManager {
    Object jsonCrudo;
    JSONObject jsonObj;
    JSONArray jsonArray;
    List<ArrayList<Object>> listaEmpleadosString = new ArrayList<>();
    
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
    
    public List<ArrayList<Object>> jsonConverterToObject(JSONArray array){
  
        if(array==null){
        return null;
        }

        for (Object object : jsonArray) {
            ArrayList<Object> innerArraylist = new ArrayList<Object>();
            int i=0;
            JSONObject jsonObjectAux = (JSONObject)object;
            innerArraylist.add(i, (Object) jsonObjectAux.get("id"));
            i++;
            innerArraylist.add(i, (Object) jsonObjectAux.get("firstName"));
            i++;
            innerArraylist.add(i, (Object) jsonObjectAux.get("lastName"));
            i++;
            innerArraylist.add(i, (Object) jsonObjectAux.get("photo"));
            i++;
            listaEmpleadosString.add(innerArraylist);
        }
        

        return listaEmpleadosString;
    }
}
