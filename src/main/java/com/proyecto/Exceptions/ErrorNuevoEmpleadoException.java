package com.proyecto.Exceptions;
import static javax.swing.JOptionPane.showMessageDialog;
public class ErrorNuevoEmpleadoException extends Exception{
    public ErrorNuevoEmpleadoException(){
        System.err.println("ERROR, NO SE PUDO INGRESAR EL NUEVO EMPLEADO, LLENE TODAS LAS CASILLAS DE INFORMACION");
        showMessageDialog(null, "LLENE TODAS LAS CASILLAS DE INFORMACION :)");
        throw new RuntimeException();
    }
}
