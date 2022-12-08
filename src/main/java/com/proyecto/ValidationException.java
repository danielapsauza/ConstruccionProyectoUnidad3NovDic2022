package com.proyecto;

public class ValidationException extends Exception{
    ValidationException(String FuenteError){
        System.err.println("Error en la llave " + FuenteError);
        throw new RuntimeException();
    }
}
