/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author sileR
 */
public class FXMLConverterController implements Initializable{

    @FXML
    private Slider slider;
    @FXML
    private Label text_conversion;
    @FXML
    private TextField text_input;
    @FXML
    private TextField text_output;
    @FXML
    private CheckBox checkbox_auto;
    @FXML
    private Button button_convert;
    
    //Inicializacion de los oyentes
    ChangeListener sliderOut, inOutput;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Bindeo del texto de 'Conversion rate:' con el slider 
        text_conversion.textProperty().bind(Bindings.format("%.2f",slider.valueProperty()));
        
        //Creacion del oyente del texto de Output, escuchando al slider
        sliderOut = new ChangeListener(){
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal){
                double output = slider.getValue() * Double.parseDouble(text_input.getText());
                String formato = String.format("%.2f",output);
                text_output.setText(formato);
            }
        };
        
        //Creacion del oyente del texto de Output, escuchando al Input
        inOutput = new ChangeListener(){
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal){
                double output = slider.getValue() * Double.parseDouble(text_input.getText());
                String formato = String.format("%.2f",output);
                text_output.setText(formato);
            }
        };
        
    }
    
    /*
     *   Boton 'Convert': multiplica el valor del slider y del campo Input
     *   y lo representa en Output en formato double con 2 decimales   
     */
    @FXML
    private void convert(ActionEvent event) {
        double output = slider.getValue() * Double.parseDouble(text_input.getText());
        String formato = String.format("%.2f",output);
        text_output.setText(formato);
    }
    
    /*
     *   Boton 'Clear': resetea el slider, y borra el contenido 
     *   Input y Output de los campos
     */
    @FXML
    private void clear(ActionEvent event) {
        slider.setValue(0.0);
        text_input.clear();
        text_output.clear();
    }
    
    //Checkbox 'Automatic convert'
    @FXML
    private void checkAuto(ActionEvent event){
        /*  
         *  Si el checkbox esta seleccionado, deshabilita el boton 'Convert'
         *  y añade a la lista de oyentes al los oyentes sliderOut y inOutput
         */ 
        if(checkbox_auto.isSelected()){
            button_convert.setDisable(true);
            slider.valueProperty().addListener(sliderOut);         
            text_input.textProperty().addListener(inOutput);   
        }
        /*  
         *  Si el checkbox no esta seleccionado, habilita el boton 'Convert'
         *  y elimina de la lista de oyentes al los oyentes sliderOut y inOutput
         */ 
        else{
            button_convert.setDisable(false);
            slider.valueProperty().removeListener(sliderOut);
            text_input.textProperty().removeListener(inOutput);
        }
    }
    
}
