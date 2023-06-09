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
import java.text.Normalizer;
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
        JTextField nombreJT = new JTextField("nombre"), apellidoPJT = new JTextField("apellidoP"), apellidoMJT = new JTextField("apellidoM"), telefonoJT = new JTextField("telefono"), correoJT = new JTextField("correo"), randomJT = new JTextField("id"), tablaJT = new JTextField("cambiame");
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
        if(tablaJT.getText().equalsIgnoreCase("cambiame") || tablaJT.getText().isBlank() ){
            JOptionPane.showMessageDialog(null,"Porfavor, dale un nombre a la tabla!!","ERROR!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println(nombreJT.getText());
        String values = "";
        LinkedList<String[]> toAdd = new LinkedList<>();
        String[] names = new String[0];

        if (nombre.isSelected()) {
            names = getStrings(cantidad, "nombres");
            values += String.format("%s,", nombreJT.getText());
            System.out.println(Arrays.toString(names));
            toAdd.add(names);
        }

        if (apellidoP.isSelected()) {
            String[] lastPName = getStrings(cantidad, "apellidos");
            values += String.format("%s,", apellidoPJT.getText());
            System.out.println(Arrays.toString(lastPName));
            toAdd.add(lastPName);
        }

        if (apellidoM.isSelected()) {
            String[] lastMName = getStrings(cantidad, "apellidos");
            values += String.format("%s,", apellidoMJT.getText());
            System.out.println(Arrays.toString(lastMName));
            toAdd.add(lastMName);
        }

        if (telefono.isSelected()) {
            String[] nums = genTel(cantidad);
            System.out.println(Arrays.toString(nums));
            values += String.format("%s,", telefonoJT.getText());
            toAdd.add(nums);
        }

        if (random.isSelected()) {
            int cant = Integer.parseInt(numGen.getText());
            String[] randoms = genRand(cantidad, cant);
            System.out.println(Arrays.toString(randoms));
            values += String.format("%s,", randomJT.getText());
            toAdd.add(randoms);
        }

        if (correo.isSelected()) {
            if (!nombre.isSelected())
                names = getStrings(cantidad, "nombres");
            String[] mails = genMail(cantidad, names, genRand(cantidad, 5), getStrings(cantidad, "apellidos"));
            System.out.println(Arrays.toString(mails));
            values += String.format("%s", correoJT.getText());
            toAdd.add(mails);
        }

        generateScript(toAdd,values,tablaJT.getText(),cantidad);
    }

    public void showOption(){
        hidden.setVisible(!isVisible());
    }

    public String[] getStrings(int cantidad, String fileName) throws IOException {
        Random r = new Random();
        File namesFile = new File(Objects.requireNonNull(getClass().getResource(String.format("%s.txt", fileName))).getFile());
        LinkedList<String> list = createList(namesFile);
        String[] listNames = new String[cantidad];
        for (int i = 0; i < cantidad; i++) {
            listNames[i] = list.get(r.nextInt(0, list.size() - 1));
        }
        return listNames;
    }

    public LinkedList<String> createList(File file) throws IOException {
        LinkedList<String> list = new LinkedList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file.getName())));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            list.add(line);
        }
        bufferedReader.close();
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

    public void generateScript(LinkedList<String[]> list, String values, String table, int cantidad){
        String[] s = new String[cantidad];
        if(!correo.isSelected())
            values = values.substring(0,values.length()-1);
        for (int i = 0; i < cantidad; i++) {
            s[i]=String.format("INSERT INTO %s (%s) VALUES(",table,values);
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                if(i == list.size()-1)
                    s[j]+=String.format("'%s');",list.get(i)[j].toUpperCase());
                else
                    s[j]+=String.format("'%s',",list.get(i)[j]).toUpperCase();
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            res.append(s[i]).append("\n");
        }
        res = new StringBuilder(Normalizer.normalize(res, Normalizer.Form.NFD));
        String finalS = String.valueOf(res);
        finalS = finalS.replaceAll("[\\p{InCombiningDiacriticalMarks}]", ""); //Normalizacion del texto para remover tildes
        JScrollPane scrollPane = new JScrollPane();
        JTextArea textArea = new JTextArea();
        textArea.append(finalS);
        scrollPane.add(textArea);
        JOptionPane.showMessageDialog(null,textArea,"Script resultante!",JOptionPane.INFORMATION_MESSAGE);
    }
}