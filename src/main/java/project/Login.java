package project;

import java.io.IOException;

import javafx.scene.control.TextField;

public class Login {
    private TextField inpText;
    private TextField inpPassword;
    private Highscores highscores = new Highscores();

   private User inpUser = new User();


    public void validateLogin(TextField inpText, TextField inpPassword) throws IOException{
        this.inpPassword = inpPassword;
        this.inpText = inpText;
        highscores.restoreSavedUsers();
        boolean nameIncluded = false;
        for (User user : highscores.getUsers().keySet()) {
            if (this.inpText.getText().equals(user.getUsername())){
                this.inpUser = user;
                nameIncluded = true;
                if (!this.inpPassword.getText().equals(user.getPassword())){
                    throw new IllegalArgumentException("password is incorrect");
                }
            }
        }
        if (!nameIncluded) throw new IllegalArgumentException("no user with this name");
        TypingGame.setUser(new User(inpText.getText(), inpPassword.getText(), this.inpUser.getPb()));
    }
}
