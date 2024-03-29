package project.ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import project.core.User;

public class StartpageController {

    @FXML
    private Button logInBtn, guestBtn, gameBtn, sendLogIn, highscoreBtn;

    @FXML
    private Group scoreGroup;
    
    @FXML
    private Hyperlink registerBtn, logInBtn2, guestLink;

    @FXML
    protected void newWindow(ActionEvent event) throws IOException{  //sender deg til ulike sider basert på hvilken knapp man trykker på
        Parent root;
        if (event.getSource().equals(logInBtn)){//når man trykker på log-in knappen
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        }
        else if (event.getSource().equals(logInBtn2)){ //I tillfellet der man skal komme til login-siden fra registrer-siden så trykker man på en hyperlink og ikke en button - så det må være to 
            root = FXMLLoader.load(getClass().getResource("Login.fxml")); // muligheter
        }
        else if (event.getSource().equals(highscoreBtn)){ //I tillfellet der man skal komme til login-siden fra registrer-siden så trykker man på en hyperlink og ikke en button - så det må være to 
            root = FXMLLoader.load(getClass().getResource("Highscores.fxml")); // muligheter
        }
        else if (event.getSource().equals(registerBtn)){
            root = FXMLLoader.load(getClass().getResource("CreateAcc.fxml"));
        }
        else if (event.getSource().equals(gameBtn)){//går til spillet
            root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        }
        else if (event.getSource().equals(guestBtn)){//går til spillet
            TypingGame.setUser(new User());
            root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        }
        else if (event.getSource().equals(guestLink)){ //registreringssidene har en hyperlink og ikke en btn som tar deg til spillet og er derfor separat fram gameBtn
            TypingGame.setUser(new User());
            root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        }
        else{
            root = FXMLLoader.load(getClass().getResource("Highscores.fxml"));
        }
        Stage stage = ((Stage) ((Node)event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                try {
                    TypingGameController.keyPressed(event.getCode());
                } catch (IOException e) {
                    throw new IllegalAccessError("IOExeption");
                }
            }

        });
        stage.setScene(scene);
        stage.show();
    }
}
