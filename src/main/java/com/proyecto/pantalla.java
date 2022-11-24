package com.proyecto;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class pantalla {

    public static void main(String[] args) {

        JFrame pantalla = new JFrame("Pantalla Dummie");
        //una matriz de string [][] informacion;

        String[][] informacion = {
                { "ID1", "firstName1", "lastName1", "photo1" },
                { "ID2", "firstName2", "lastName2", "photo2" },
                { "ID3", "firstName3", "lastName3", "photo3" }
        };

        String[] columnas = { "ID", "firstName", "lastName", "photo" };

        JTable tablaInformacion = new JTable(informacion, columnas);

        JScrollPane scrollPanel = new JScrollPane(tablaInformacion);

        pantalla.add(scrollPanel);
        pantalla.setSize(500, 150);
        pantalla.setVisible(true);
    }


}