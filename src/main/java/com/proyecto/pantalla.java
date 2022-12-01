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

public class Pantalla extends JFrame implements ActionListener{
    JButton buttonModificar = new JButton("Modificar");
    JButton buttonAgregar = new JButton("Agregar");
    JButton buttonEliminar = new JButton("Eliminar");
    String[] columnas = { "ID", "firstName", "lastName", "photo" };
    public  Object[][] informacion;
    static JFrame pantallaJframe = new JFrame("Pantalla Dummie");
    public  DefaultTableModel model;
    JTable tablaInformacion;
    JScrollPane scrollPanel;

    public Pantalla(List<ArrayList<Object>> informacionArray){
        crearPantalla(convertidorAMatriz(informacionArray));
    }

    public Pantalla (JSONArray jsonArray){
        Object[] infoNueva = jsonArray.toArray();
        Object[][] informacionNueva = new Object[infoNueva.length][4];
        int i=0;

        for(Object obj : infoNueva){
            ArrayList aux = (ArrayList) obj;

            Object[] aux2 = aux.toArray();
            informacionNueva[i][0] = aux2[0].toString();
            informacionNueva[i][1] = aux2[1].toString();
            informacionNueva[i][2] = aux2[2].toString();

            informacionNueva[i][3] = getImgIcon(aux2[3].toString());
            
            i++;
        }
        crearPantalla(informacionNueva);
    }

    public static Object[][] convertidorAMatriz(List<ArrayList<Object>> informacionArray){
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
        informacion = matrizEmpleados;
        model = new DefaultTableModel(informacion, columnas) {
            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

        tablaInformacion = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0 || column == 1 || column == 2 || column == 3 ){
                    return false;
                }else{
                    return true;
                }
            }
        };

        tablaInformacion.setBounds(500, 500, 700, 400);
        tablaInformacion.setRowHeight(200);
        tablaInformacion.setPreferredScrollableViewportSize(new Dimension(750, 720));

        scrollPanel = new JScrollPane(tablaInformacion);
        scrollPanel.setBounds(500, 500, 700, 400);
        
        pantallaJframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        pantallaJframe.setResizable(false);
        pantallaJframe.setLayout(new FlowLayout());
        pantallaJframe.add(buttonModificar);
        pantallaJframe.add(buttonEliminar);
        pantallaJframe.add(buttonAgregar);
        pantallaJframe.add(scrollPanel);

        buttonModificar.addActionListener(this);
        buttonEliminar.addActionListener(this);
        buttonAgregar.addActionListener(this);

        pantallaJframe.setSize(800, 800);
        pantallaJframe.setVisible(true);
        
    }

    private static Icon getImgIcon(String urlStr) {

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
            editor.editarEmpleadoPantalla(informacion);
            controladorVista control1 = new controladorVista(this, editor);
        } else if (buttonEliminar.equals(e.getSource())) {
            editor.eliminarEmpleadoPantalla();
        } else if (buttonAgregar.equals(e.getSource())) {
            editor.agregarEmpleadoPantalla();
        }
    }

    public  void refresh(Pantalla pantalla1) throws FileNotFoundException, IOException, ParseException, ValidationException{
        pantallaJframe.remove(scrollPanel); 
        pantallaJframe.remove(buttonAgregar);
        pantallaJframe.remove(buttonEliminar);
        pantallaJframe.remove(buttonModificar);
        informacion = null;
        model=null;
        scrollPanel=null;
        JsonMod.arrayAux.clear();

        JsonManager jsonManager2 = new JsonManager();
        JSONArray jsonArray2 = jsonManager2.readJson("src/employees.json");
        jsonManager2.jsonValidation(jsonArray2, "employee");
        
        pantalla1 = new Pantalla(jsonManager2.jsonConverterToObject(jsonArray2));
        
        //System.out.println(jsonArray2);
        //System.out.println("Editado________________________________________________________________________________________");
        
    }
    
}