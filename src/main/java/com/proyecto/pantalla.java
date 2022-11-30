package com.proyecto;

import java.awt.Dimension;
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
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import com.proyecto.Exceptions.ValidationException;

public class pantalla extends JFrame implements ActionListener{
    JButton buttonModificar;
    JButton buttonAgregar;
    JButton buttonEliminar;
    String[] columnas = { "ID", "firstName", "lastName", "photo" };
    Object[][] informacion;

    public pantalla(List<ArrayList<Object>> informacionArray){
        crearPantalla(convertidorAMatriz(informacionArray));
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

    public void crearPantalla(Object[][] matrizEmpleados){
        JFrame pantalla = new JFrame("Pantalla Dummie");
        informacion = matrizEmpleados;
        buttonModificar = new JButton("Modificar");
        buttonAgregar = new JButton("Agregar");
        buttonEliminar = new JButton("Eliminar");

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
        tablaInformacion.setPreferredScrollableViewportSize(new Dimension(750, 720));

        JScrollPane scrollPanel = new JScrollPane(tablaInformacion);
        scrollPanel.setBounds(500, 500, 700, 400);
        
        pantalla.setResizable(false);
        pantalla.setLayout(new FlowLayout());
        pantalla.add(buttonModificar);
        pantalla.add(buttonEliminar);
        pantalla.add(buttonAgregar);
        pantalla.add(scrollPanel);
        buttonModificar.addActionListener(this);
        buttonEliminar.addActionListener(this);
        buttonAgregar.addActionListener(this);
        pantalla.setSize(800, 800);
        pantalla.setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent e){
        JsonMod editor = new JsonMod();

        if (buttonModificar.equals(e.getSource())) {
            editor.editarEmpleado(informacion);
            dispose();
            refresh();
        } else if (buttonEliminar.equals(e.getSource())) {
            editor.eliminarEmpleado();
            dispose();
            refresh();
        } else if (buttonAgregar.equals(e.getSource())) {
            editor.agregarEmpleado();
            dispose();
            refresh();
        }

    }
    
    public void refresh(){

    }
    

    public static void main(String[] args) throws ValidationException, FileNotFoundException, IOException, ParseException {
        JsonManager jsonManager = new JsonManager();
        JSONArray jsonArray = jsonManager.readJson("src/employees.json");
        jsonManager.jsonValidation(jsonArray, "employee"); 
        pantalla pantalla = new pantalla(jsonManager.jsonConverterToObject(jsonArray));
    }


}