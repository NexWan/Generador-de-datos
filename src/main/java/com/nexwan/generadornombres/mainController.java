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

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    public TextField numGen;
    public Button generar;
    public Pane hidden;
    public CheckBox apellidoP;
    public CheckBox nombre;
    public CheckBox apellidoM;
    public CheckBox telefono;
    public CheckBox random;
    @FXML
    private ImageView imageLoad;

    private boolean isVisible(){
        return hidden.isVisible();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(Objects.requireNonNull(getClass().getResource("catto.jpg")).toExternalForm());
        imageLoad.setImage(image);
    }

    public void generar(){
        System.out.println(apellidoP.isSelected() + "" + nombre.isSelected() + " " + apellidoM.isSelected() + "" + telefono.isSelected() + " " + random.isSelected());
        if(nombre.isSelected()) {

        }else if(apellidoM.isSelected()){

        }else if(apellidoM.isSelected()){

        }else if(telefono.isSelected()){

        }else if(random.isSelected()){

        }
    }

    public void showOption(){
        hidden.setVisible(!isVisible());
    }

    public String getName(){

    }
}