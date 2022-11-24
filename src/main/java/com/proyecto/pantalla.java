package com.proyecto;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class pantalla {

    public void crearPantalla(String[][] informacionJSON){
        JFrame pantalla = new JFrame("Pantalla Dummie");
        String[][] informacion = informacionJSON;
        String[] columnas = { "ID", "firstName", "lastName", "photo" };

        JTable tablaInformacion = new JTable(informacion, columnas);

        JScrollPane scrollPanel = new JScrollPane(tablaInformacion);

        pantalla.add(scrollPanel);
        pantalla.setSize(500, 150);
        pantalla.setVisible(true);
    }

    public static void main(String[] args) {
        //TODO String[][] info = clase para obtener el JSON
        String[][] info = {
            { "ID1", "firstName1", "lastName1", "photo1" },
            { "ID2", "firstName2", "lastName2", "photo2" },
            { "ID3", "firstName3", "lastName3", "photo3" }
        };

        pantalla pantalla = new pantalla();
        pantalla.crearPantalla(info);
    }


}