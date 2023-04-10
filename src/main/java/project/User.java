package project;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class User {
    private Map<User,Score> users = new HashMap<>();
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private Score pb;   //personlig beste score til denne useren

    public User(TextField username, PasswordField password){
        this.username = username;
        this.password = password;
    }
    public Score updatePb(Score newPb){
        this.pb = newPb;
        return this.pb;
    }
    public Map<User,Score> updateUsers(User newUser){
        
        return this.users;
    }

}
