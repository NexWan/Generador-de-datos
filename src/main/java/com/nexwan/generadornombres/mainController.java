package com.nexwan.generadornombres;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.*;
import java.net.URL;
import java.util.*;
import javax.swing.*;

public class mainController implements Initializable {
    public TextField numGen;
    public Button generar;
    public Pane hidden;
    public CheckBox apellidoP;
    public CheckBox nombre;
    public CheckBox apellidoM;
    public CheckBox telefono;
    public CheckBox random;
    public CheckBox correo;
    @FXML
    private ImageView imageLoad;

    private boolean isVisible(){
        return hidden.isVisible();
    }

    public String correos[] = {"@mail.com","@hotmail.com","@gmail.com","@yahoo.com"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(Objects.requireNonNull(getClass().getResource("catto.jpg")).toExternalForm());
        imageLoad.setImage(image);
    }

    public void generar() throws IOException {
        int cantidad;
        JTextField nombreJT = new JTextField(), apellidoPJT = new JTextField(), apellidoMJT = new JTextField(), telefonoJT = new JTextField(), correoJT = new JTextField(), randomJT = new JTextField(), tablaJT = new JTextField();
        if(!(apellidoM.isSelected() || apellidoP.isSelected() || nombre.isSelected() || telefono.isSelected() || random.isSelected() || correo.isSelected())) return;
        try{
            cantidad = Integer.parseInt(JOptionPane.showInputDialog(null,"Cuantos datos desea generar?"));
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Seleccione un numero correcto!","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println(apellidoP.isSelected() + "" + nombre.isSelected() + " " + apellidoM.isSelected() + "" + telefono.isSelected() + " " + random.isSelected());
        Object[] fields= {
                "INTRODUCE EL NOMBRE DE TUS VARIABLES EN ORACLE\n(Los campos que no seleccionaste puedes dejarlos vacios, el campo tabla NO puede estar vacio!)",
                "nombre", nombreJT,
                "apellido Paterno",apellidoPJT,
                "Apellido materno",apellidoMJT,
                "Telefono", telefonoJT,
                "Numero random",randomJT,
                "Correo", correoJT,
                "Tabla", tablaJT
        };
        JOptionPane.showConfirmDialog(null,fields,"Selecciona",JOptionPane.OK_CANCEL_OPTION);
        System.out.println(nombreJT.getText());
        String values = "";
        String[] names = new String[0];
        String[] lastPName;
        String[] lastMName = new String[0];
        if (nombre.isSelected()) {
            names = getStrings(cantidad,"nombres");
            values+=String.format("'%s'",nombreJT.getText());
            System.out.println(Arrays.toString(names));
        }
        if (apellidoP.isSelected()) {
            lastPName = getStrings(cantidad,"apellidos");
            values+=String.format("'%s'",apellidoPJT.getText());
            System.out.println(Arrays.toString(lastPName));
        }
        if (apellidoM.isSelected()) {
            lastMName = getStrings(cantidad,"apellidos");
            values+=String.format("'%s'",apellidoMJT.getText());
            System.out.println(Arrays.toString(lastMName));
        }
        if (telefono.isSelected()) {
            String[] nums = genTel(cantidad);
            System.out.println(Arrays.toString(nums));
            values+=String.format("'%s'",telefonoJT.getText());
        }
        if (random.isSelected()) {
            int cant = Integer.parseInt(numGen.getText());
            String[] randoms = genRand(cantidad,cant);
            System.out.println(Arrays.toString(randoms));
            values+=String.format("'%s'",randomJT);
        }
        if (correo.isSelected()) {
            String[] mails = genMail(cantidad,names,genRand(cantidad,5),getStrings(cantidad,"apellidos"));
            System.out.println(Arrays.toString(mails));
        }
    }

    public void showOption(){
        hidden.setVisible(!isVisible());
    }

    public String[] getStrings(int cantidad, String fileName) throws IOException {
        Random r = new Random();
        File names = new File(Objects.requireNonNull(getClass().getResource(String.format("%s.txt",fileName))).getFile());
        LinkedList<String> list = createList(names);
        String[] listNames = new String[cantidad];
        for (int i = 0; i < cantidad; i++) {
            listNames[i] = list.get(r.nextInt(0,list.size()-1));
        }
        return listNames;
    }

    public LinkedList<String> createList(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        LinkedList<String> list = new LinkedList<>();
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        int i = 0;
        while(line != null){
            line = bufferedReader.readLine();
            if(line != null) list.add(line);
        }
        return list;
    }

    public String[] genTel(int cantidad){
        Random r = new Random();
        String lada = "844";
        String[] nums = new String[cantidad];
        for (int i = 0; i < cantidad; i++) {
            nums[i] = lada;
            for (int j = 0; j < 7; j++) {
                nums[i]+= String.valueOf(r.nextInt(0,9));
            }
        }
        return nums;
    }

    public String[] genRand(int cantidad, int max){
        String[] rand = new String[cantidad];
        Random r = new Random();
        for (int i = 0; i < cantidad; i++) {
            rand[i] = "";
            for (int j = 0; j < max; j++) {
                rand[i]+= String.valueOf(r.nextInt(0,9));
            }
        }
        return rand;
    }
    
    public String[] genMail(int cantidad, String[] nombre, String[] rand, String[] apellido){
        String[] mails = new String[cantidad];
        Random r = new Random();
        for (int i = 0; i < cantidad; i++) {
            mails[i] = nombre[i].substring(0,4) + "" + apellido[i].substring(0,2).toUpperCase() + "" + rand[i] + correos[r.nextInt(0, correos.length)];
        }
        return mails;
    }
}