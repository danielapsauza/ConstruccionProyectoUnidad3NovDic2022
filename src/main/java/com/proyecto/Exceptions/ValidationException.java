package com.proyecto.Exceptions;
import static javax.swing.JOptionPane.showMessageDialog;

public class ValidationException extends Exception{

    public ValidationException(String FuenteError){
        System.err.println("Error en la llave " + FuenteError);
        showMessageDialog(null, "Error en la llave " + FuenteError +" del archivo JSON");
        throw new RuntimeException();
    }
    
}
