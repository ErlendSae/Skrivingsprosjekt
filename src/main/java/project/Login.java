package project;

import java.io.IOException;

import javafx.scene.control.TextField;

public class Login {
    private Highscores highscores = new Highscores();

   private User inpUser = new User();


    public void validateLogin(String inpTextStr, String inpPasswordStr) throws IOException{
        highscores.restoreSavedUsers();
        boolean nameIncluded = false;
        for (User user : highscores.getUsers().keySet()) {
            if (inpTextStr.equals(user.getUsername())){
                this.inpUser = user;
                nameIncluded = true;
                if (!inpPasswordStr.equals(user.getPassword())){
                    throw new IllegalArgumentException("password is incorrect");
                }
            }
        }
        if (!nameIncluded) throw new IllegalArgumentException("no user with this name");
        TypingGame.setUser(new User(inpTextStr, inpPasswordStr, this.inpUser.getPb()));
    }
    public void enterGame(TextField inpText, TextField inpPassword) throws IOException{
        this.validateLogin(inpText.getText(), inpPassword.getText());
    }
}
