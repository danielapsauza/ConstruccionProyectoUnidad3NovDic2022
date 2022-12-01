package com.proyecto;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import com.proyecto.Exceptions.ValidationException;

public class PruebaCompleta {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, ValidationException {
        JsonManager jsonManager = new JsonManager();
        JSONArray jsonArray = jsonManager.readJson("src/employees.json");
        jsonManager.jsonValidation(jsonArray, "employee"); 
        System.out.println(jsonArray);
        System.out.println("Original________________________________________________________________________________________");
        Pantalla pantalla1 = new Pantalla(jsonManager.jsonConverterToObject(jsonArray));
    }
    
}
