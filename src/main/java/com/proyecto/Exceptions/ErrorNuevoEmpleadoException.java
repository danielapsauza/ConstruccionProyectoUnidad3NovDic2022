package com.proyecto.Exceptions;

public class ErrorNuevoEmpleadoException extends Exception{
    public ErrorNuevoEmpleadoException(){
        System.err.println("ERROR, NO SE PUDO INGRESAR EL NUEVO EMPLEADO, LLENE TODAS LAS CASILLAS DE INFORMACION");
        throw new RuntimeException();
    }
}
