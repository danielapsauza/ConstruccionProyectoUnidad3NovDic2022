package com.proyecto;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.*;

public class JsonMod extends JFrame implements ActionListener{
    String identificador;
    private JTextField textFieldName;
    private JTextField textFieldLastName;
    private JTextField textFieldImg;
    private JLabel label1;
    private JButton buttonAceptar;
  
	
    public void editarEmpleado(Object[][] informacion){
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
    
        buttonAceptar=new JButton("Aceptar");
        buttonAceptar.setBounds(10,100,100,30);
        add(buttonAceptar);
    
        buttonAceptar.addActionListener(this);
        setVisible(true);
    }

    public void eliminarEmpleado(){
        identificador = JOptionPane.showInputDialog(null, "Ingrese el id del empleado que desea eliminar");
    }

    public void agregarEmpleado(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
