package com.proyecto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.json.simple.parser.ParseException;

public class pantalla {

    public pantalla(List<ArrayList<Object>> informacionArray){
        crearPantalla(convertidorAMatriz(informacionArray));
    }

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

    public String[][] convertidorAMatriz(List<ArrayList<Object>> informacionArray){
        int i = 0;

        Object[] info = informacionArray.toArray();
        String[][] informacion = new String[info.length][4];

        for(Object obj : info){
            ArrayList aux = (ArrayList) obj;

            Object[] aux2 = aux.toArray();
            informacion[i][0] = aux2[0].toString();
            informacion[i][1] = aux2[1].toString();
            informacion[i][2] = aux2[2].toString();
            informacion[i][3] = aux2[3].toString();

            i++;
        }

        return informacion;
    }

    public static void main(String[] args) {

    }


}