package com.proyecto.Exceptions;

public class ErrorUpdateException extends Exception {

    public ErrorUpdateException(String id){
        System.err.println("ERROR, NO SE PUDO ACTUALIZAR EL DOCUMENTO JSON EN EL EMPLEADO " + id);
        throw new RuntimeException();
    }
}
