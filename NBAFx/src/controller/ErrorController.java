package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.text.Text;



public class ErrorController extends Controller<Validator> {
    
    @FXML public Text errorMessages;
    
    @FXML public void close(){
        stage.close();    // may need go back to prev instance
    }
    
    @FXML public void initialize(){
        String allErrorMessages = "";
        for (String error : getValidator().errors()){
            allErrorMessages += error;
            errorMessages.setText(allErrorMessages); // may need to change it for multiple
        }
    }
    
    
    private Validator getValidator(){
        return this.model;
    }
}
