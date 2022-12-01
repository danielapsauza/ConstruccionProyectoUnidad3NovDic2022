package com.proyecto;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.proyecto.Exceptions.ErrorUpdateException;
import com.proyecto.Exceptions.ValidationException;

import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class JsonMod extends JFrame implements ActionListener{
    
    String identificador;
    private JTextField textFieldName;
    private JTextField textFieldLastName;
    private JTextField textFieldImg;
    private JLabel label1;
    private JButton buttonAceptar = new JButton("Aceptar");
    public static JSONArray arrayAux = new JSONArray();
    private String accion;

    public void editarEmpleadoPantalla(Object[][] informacion){
        accion = "modificar";
        identificador = JOptionPane.showInputDialog(null, "Ingrese el id del empleado que desea modificar");
        setLayout(null);
        setBounds(0,0,450,180);
        setTitle("Modificar Empleado");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
        label1=new JLabel("Nombre:");
        label1.setBounds(10,10,100,30);
        add(label1);

        label1=new JLabel("Apellido:");
        label1.setBounds(10,40,100,30);
        add(label1);

        label1=new JLabel("Src Imagen:");
        label1.setBounds(10,70,100,30);
        add(label1);
    
        textFieldName=new JTextField();
        textFieldName.setBounds(120,10,200,20);
        add(textFieldName);

        textFieldLastName=new JTextField();
        textFieldLastName.setBounds(120,40,200,20);
        add(textFieldLastName);

        textFieldImg=new JTextField();
        textFieldImg.setBounds(120,70,300,20);
        add(textFieldImg);
    
        buttonAceptar.setBounds(10,100,100,30);
        add(buttonAceptar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    
        setVisible(true); 
    }

    public void eliminarEmpleadoPantalla(){
        accion = "eliminar";
        identificador = JOptionPane.showInputDialog(null, "Ingrese el id del empleado que desea eliminar");
        setLayout(null);
        setBounds(0,0,450,180);
        setTitle("Eliminar Empleado");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        label1=new JLabel("Se eliminará el empleado con id: " + identificador);
        label1.setBounds(120,40,250,30);
        add(label1);

        buttonAceptar.setBounds(170,100,100,30);
        add(buttonAceptar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void agregarEmpleadoPantalla(){
        
    }

    public JButton getButtonAceptar() {
        return buttonAceptar;
    }

    public void actualizarListaEmpleados() throws ErrorUpdateException{
        JSONArray jsonArrayEmpleados = JsonManager.obtenerJsonArray();
        JSONObject empleadoAux = new JSONObject();
        JSONObject objAux = new JSONObject();
        //si no inicializas identificador, tira la exception, entonces test correcto
        //identificador se inicializa en editarEmpleadoPantalla
        empleadoAux.put("id", identificador);
        if(accion == "modificar"){

            /*PRUEBAS
            identificador="1"; 
            empleadoAux.put("id", identificador);
            empleadoAux.put("firstName", "ejemplo");
            empleadoAux.put("lastName", "ejemplo");
            empleadoAux.put("photo", "https://jsonformatter.org/img/tom-cruise.jpg");
            */

            /*Correcto */
           
            empleadoAux.put("firstName", textFieldName.getText());
            empleadoAux.put("lastName", textFieldLastName.getText());
            empleadoAux.put("photo", textFieldImg.getText());
            
            
            for (Object object : jsonArrayEmpleados) {
                JSONObject jsonObjectAux = (JSONObject)object;

                if (jsonObjectAux.get("id").equals(empleadoAux.get("id"))) {
                    //insertamos todos los valores de empleadoAux al empleado del jsonArray
                    jsonObjectAux.put("firstName", empleadoAux.get("firstName"));
                    jsonObjectAux.put("lastName", empleadoAux.get("lastName"));
                    jsonObjectAux.put("photo", empleadoAux.get("photo"));
                }
                arrayAux.add(jsonObjectAux);
            }
            

        }else if(accion == "eliminar"){
            int idNuevo=1;
            for (Object object : jsonArrayEmpleados) {
                JSONObject jsonObjectAux = (JSONObject)object;
                if (!jsonObjectAux.get("id").equals(empleadoAux.get("id"))) {
                    jsonObjectAux.put("id", idNuevo);
                    arrayAux.add(jsonObjectAux);
                    idNuevo++;
                }
             }
        }

        objAux.put("employee", arrayAux);
        try {
            System.out.println(objAux);
            System.out.println("----------------------JSON editado");
			FileWriter file = new FileWriter("src/employees.json");
			file.write(objAux.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			System.out.println("error");
            throw new ErrorUpdateException(identificador);
		}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}