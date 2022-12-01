package com.proyecto.Exceptions;

public class MissingIdException extends Exception{

    public MissingIdException(String id){
        System.err.println("Error, no existe el id: " + id);
        throw new RuntimeException();
    }
}
