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

   private Highscores highscores = new Highscores();

   private User inpUser = new User();

    @FXML
    public void validateLogin(ActionEvent event) throws IOException{
        highscores.restoreSavedUsers();
        boolean nameIncluded = false;
        for (User user : this.highscores.getUsers().keySet()) {
            if (this.inpText.getText().equals(user.getUsername())){
                this.inpUser = user;
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
        TypingGame.setUser(new User(inpText.getText(), inpPassword.getText(), this.inpUser.getPb()));
        this.newWindow(event);
    }

}
