package com.proyecto;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import com.proyecto.Exceptions.ValidationException;

public class pantalla {

    public pantalla(List<ArrayList<Object>> informacionArray){
        crearPantalla(convertidorAMatriz(informacionArray));
        System.out.println("aaaa");
    }

    public void crearPantalla(Object[][] matrizEmpleados){
        JFrame pantalla = new JFrame("Pantalla Dummie");
        Object[][] informacion = matrizEmpleados;
        String[] columnas = { "ID", "firstName", "lastName", "photo" };

        JButton button = new JButton("Modificar");
        button.setBounds(450,700,95,30);

        DefaultTableModel model = new DefaultTableModel(informacion, columnas) {
            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

        JTable tablaInformacion = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0 || column == 3){
                    return false;
                }else{
                    return true;
                }
            }
        };
        tablaInformacion.setBounds(500, 500, 700, 400);
        tablaInformacion.setRowHeight(200);

        JScrollPane scrollPanel = new JScrollPane(tablaInformacion);
        scrollPanel.setBounds(500, 500, 700, 400);

        pantalla.add(button);
        pantalla.add(scrollPanel);
        
        pantalla.setSize(1000, 1000);
        pantalla.setVisible(true);
    }

    public Object[][] convertidorAMatriz(List<ArrayList<Object>> informacionArray){
        int i = 0;
       
        Object[] info = informacionArray.toArray();
        Object[][] informacion = new Object[info.length][4];

        for(Object obj : info){
            ArrayList aux = (ArrayList) obj;

            Object[] aux2 = aux.toArray();
            informacion[i][0] = aux2[0].toString();
            informacion[i][1] = aux2[1].toString();
            informacion[i][2] = aux2[2].toString();

            informacion[i][3] = getImgIcon(aux2[3].toString());
            
            i++;
        }

        return informacion;
    }

    private Icon getImgIcon(String urlStr) {

        try {
            URL url = new URL(urlStr);
            BufferedImage myPicture = ImageIO.read(url);
            return new ImageIcon(myPicture);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void main(String[] args) throws ValidationException, FileNotFoundException, IOException, ParseException {
        JsonManager jsonManager = new JsonManager();
        JSONArray jsonArray = jsonManager.readJson("src/employees.json");
        jsonManager.jsonValidation(jsonArray, "employee"); 
        pantalla pantalla = new pantalla(jsonManager.jsonConverterToObject(jsonArray));
    }


}