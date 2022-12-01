package com.proyecto;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;

import com.proyecto.Exceptions.ErrorUpdateException;
import com.proyecto.Exceptions.MissingIdException;
import com.proyecto.Exceptions.ValidationException;

public class controladorVista implements ActionListener{
    Pantalla pantalla;
    JsonMod jsonMod;

    public controladorVista(Pantalla pantalla, JsonMod jsonMod){
        this.pantalla = pantalla;
        this.jsonMod = jsonMod;

        this.jsonMod.getButtonAceptar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jsonMod.getButtonAceptar().equals(e.getSource())) {
            
                try {
                    jsonMod.actualizarListaEmpleados();
                } catch (ErrorUpdateException | MissingIdException e1) {
                    e1.printStackTrace();
                }
            
            }
            jsonMod.dispose();
            Pantalla.pantallaJframe.pack();
            Pantalla.pantallaJframe.repaint();
            Pantalla.pantallaJframe.dispose();
                try {
                    pantalla.refresh(this.pantalla);
                } catch (IOException | org.json.simple.parser.ParseException | ValidationException e1) {
                    e1.printStackTrace();
                }
        }  
    }

