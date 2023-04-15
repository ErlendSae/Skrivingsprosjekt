package project;

import javafx.fxml.FXML;

public class User {
    @FXML
    private String username;
    @FXML
    private String password;

    private Score pb;   //personlig beste score til denne useren

    //constructor for guest-brukere
    public User(){
        this.username = "guest";
        this.password = null;
        this.pb = new Score(0, 0);
    }

    //constructor for new account
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.pb = new Score();
    }

    //constructor for old account
    public User(String username, String password, Score pb){
        this.username = username;
        this.password = password;
        this.pb = pb;
    }
    
    public Score updatePb(Score newPb){
        this.pb = newPb;
        return this.pb;
    }
    
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Score getPb() {
        return pb;
    }
    
    @Override
    public String toString() {
        return username + " " + password;
    }
}
