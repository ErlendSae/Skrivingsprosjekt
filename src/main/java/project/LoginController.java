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
   private Hyperlink registerBtn, gameLink;

   private Highscores highscores = new Highscores();

    @FXML
    public void validateLogin(ActionEvent event) throws IOException{
        highscores.restoreSavedUsers();
        boolean nameIncluded = false;
        for (User user : this.highscores.getUsers().keySet()) {
            if (this.inpText.getText().equals(user.getUsername())){
                nameIncluded = true;
                if (!this.inpPassword.getText().equals(user.getPassword())){
                    throw new IllegalArgumentException("password is incorrect");
                }
            }
        }
        if (!nameIncluded) throw new IllegalArgumentException("no user with this name");
        this.enterWindow(event);
    }
    @FXML
    public void enterWindow(ActionEvent event) throws IOException{
        this.newWindow(event);
    }

}
