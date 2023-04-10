package project;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class User {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private Score pb;   //personlig beste score til denne useren

    public User(TextField username, PasswordField password){
        this.username = username;
        this.password = password;
    }
    public Score updatePb(Score nyPb){
        this.pb = nyPb;
        return this.pb;
    }

}
