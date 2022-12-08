package com.proyecto.Exceptions;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

public class MissingIdException extends Exception{

    public MissingIdException(String id){
        System.err.println("Error, no existe el id: " + id);
        showMessageDialog(null, "Id no valido, intentelo de nuevo :)");
        throw new RuntimeException();
    }
}
