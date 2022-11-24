package com.proyecto.Exceptions;

public class ValidationException extends Exception{

    public ValidationException(String FuenteError){
        System.err.println("Error en la llave " + FuenteError);
        throw new RuntimeException();
    }
    
}
