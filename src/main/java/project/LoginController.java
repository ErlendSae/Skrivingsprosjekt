package project;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class LoginController extends StartpageController{
   @FXML
   private TextField inpText, inpPassword;
   @FXML
   private Hyperlink registerBtn, guestLink;

   private Login loginPage = new Login();

    @FXML
    public void validateLogin(ActionEvent event) throws IOException{
        this.loginPage.validateLogin(inpText, inpPassword);
        this.enterWindow(event);
    }
    @FXML
    public void enterWindow(ActionEvent event) throws IOException{
        this.newWindow(event);
    }

}
