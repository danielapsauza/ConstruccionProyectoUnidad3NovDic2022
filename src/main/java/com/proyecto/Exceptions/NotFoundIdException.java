package com.proyecto.Exceptions;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

public class NotFoundIdException extends Exception{

    public NotFoundIdException(){
        System.err.println("Error, no ingreso el id: " );
        showMessageDialog(null, "No se ingresó un id, intentelo de nuevo :)");
        throw new RuntimeException();
    }
}