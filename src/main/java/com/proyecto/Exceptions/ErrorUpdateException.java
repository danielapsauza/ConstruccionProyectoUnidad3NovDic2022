package com.proyecto.Exceptions;

import static javax.swing.JOptionPane.showMessageDialog;

public class ErrorUpdateException extends Exception {

    public ErrorUpdateException(String id){
        System.err.println("ERROR, NO SE PUDO ACTUALIZAR EL DOCUMENTO JSON EN EL EMPLEADO " + id);
        showMessageDialog(null, "NO SE PUDO ACTUALIZAR EL DOCUMENTO JSON");
        throw new RuntimeException();
    }
}
