package project;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class NewAccController extends StartpageController{
    @FXML
    private TextField inpText, inpPassword1, inpPassword2;
    @FXML
    private Hyperlink logInBtn2, gameLink;
    @FXML
     public void validateLogin(){
        inpText.setText("hey");
    }

     @FXML
     public void enterWindow(ActionEvent event) throws IOException{
         this.newWindow(event);
     }
}
