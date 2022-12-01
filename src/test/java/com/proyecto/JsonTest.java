package com.proyecto;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
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

import com.proyecto.Exceptions.ValidationException;


public class JsonTest {

    private Pantalla pantalla;

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
        Assertions.assertThrows(RuntimeException.class, ()->{ archivoJson.jsonValidation(jsonaArray, "employe");});
    }

    @Test
    @DisplayName("Convertir a Array de Objetos")
    public void convertir() throws FileNotFoundException, IOException, ParseException{
        JsonManager archivoJson = new JsonManager();
        JSONArray jsonaArray = archivoJson.readJson("src/employees.json");
        archivoJson.jsonConverterToObject(jsonaArray);
    }

    @Test
    @DisplayName("enviar informacion a tabla")
    public void enviar() throws FileNotFoundException, IOException, ParseException, ValidationException{
        JsonManager jsonManager = new JsonManager();
        JSONArray jsonArray = jsonManager.readJson("src/employees.json");
        jsonManager.jsonValidation(jsonArray, "employee"); 
        Pantalla pantalla = new Pantalla(jsonManager.jsonConverterToObject(jsonArray));
        System.out.println("pantalla creada");
    }

    @Test
    @DisplayName("actualizarJSON")
    public void actualizar() throws FileNotFoundException, IOException, ParseException, ValidationException{
        JsonManager jsonManager = new JsonManager();
        JSONArray jsonArray = jsonManager.readJson("src/employees.json");
        jsonManager.jsonValidation(jsonArray, "employee"); 
        System.out.println(jsonArray);
        System.out.println("Original________________________________________________________________________________________");
        Pantalla pantalla1 = new Pantalla(jsonManager.jsonConverterToObject(jsonArray));
        JsonMod editor = new JsonMod();
        controladorVista control1 = new controladorVista(pantalla1, editor);
        Assertions.assertThrows(RuntimeException.class, ()->{editor.actualizarListaEmpleados();});
        
    }
}