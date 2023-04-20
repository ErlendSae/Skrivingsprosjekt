package project;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class NewAccController extends StartpageController{
    @FXML
    private TextField inpText;
    @FXML
    private PasswordField inpPassword1, inpPassword2;
    @FXML
    private Hyperlink logInBtn2, guestLink;

    private Highscores highscores = new Highscores();

    //metode som sender deg til spillet etter en validering
    @FXML
     public void validateRegister(ActionEvent event) throws IOException{
        this.validatePassword(inpPassword1);
        if (inpText.getLength() <= 1) throw new IllegalArgumentException("username must be at least two characters");     
        if (inpPassword1.getLength() != inpPassword2.getLength()) throw new IllegalArgumentException("passwords test");
        else if (!inpPassword1.getText().equals(inpPassword2.getText())) throw new IllegalArgumentException("passwords not equal");
        
        
        highscores.updateUsers(new User(inpText.getText(), inpPassword1.getText()), new Score());

        this.enterWindow(event);
    }

    //metode som validerer passordet
    public void validatePassword(PasswordField password){
        String password_string = password.getText().toString();
        char[] chars = password_string.toCharArray();

        //lager en løkke der jeg sjekker om en av karakterene i passordet er et tall
        boolean hasDigit = false;
        for (char c : chars) {
            if (Character.isDigit(c)) hasDigit = true;
        }
        if (!hasDigit) throw new IllegalArgumentException("password must contain at least a number"); //hvis det ikke finnes et tall kastes en feilmelding 
        if (password_string.equals(password_string.toLowerCase())) throw new IllegalArgumentException("password must contain at least one capital letter"); //passordet må inneholde minst en stor bokstav
    }

     @FXML
     public void enterWindow(ActionEvent event) throws IOException{
        TypingGame.setUser(new User(inpText.getText(), inpPassword1.getText()));
         this.newWindow(event);
     }
}
