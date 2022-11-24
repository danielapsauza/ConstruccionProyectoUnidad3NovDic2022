package com.proyecto;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.beans.ExceptionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;


public class JsonTest {

    @Test
    @DisplayName("Existe Archivo")
    public void existeArchivo(){
        File archivoJson = new File("src/employees.json");
        assert(archivoJson.exists());
    }

    @Test
    @DisplayName("NO existe Archivo")
    public void noExisteArchivo(){
        JsonManager archivoJson = new JsonManager();
        Assertions.assertThrows(FileNotFoundException.class, ()->{ archivoJson.readJson("src/employes.json");});
    }

    @Test
    @DisplayName("Validar Formato")
    public void validarFormato() throws FileNotFoundException, IOException, ParseException{
        
        JsonManager archivoJson = new JsonManager();
        JSONArray jsonaArray = archivoJson.readJson("src/employees.json");
        Assertions.assertThrows(RuntimeException.class, ()->{ archivoJson.jsonValidation(jsonaArray, "employee");});
    }

    @Test
    @DisplayName("Convertir a Array de Objetos")
    public void convertir() throws FileNotFoundException, IOException, ParseException{
        JsonManager archivoJson = new JsonManager();
        JSONArray jsonaArray = archivoJson.readJson("src/employees.json");
        archivoJson.jsonConverterToObject(jsonaArray);
    }
}
