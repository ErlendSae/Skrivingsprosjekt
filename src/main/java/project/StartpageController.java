package project;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartpageController {
    @FXML
    private Text firstNumber, secondNumber, operator;
    
    @FXML
    private void loginClick(ActionEvent event) throws IOException{ //sender deg til login siden når man klikker på login-knappen på startsiden
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = ((Stage) ((Node)event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private void createAccountClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = ((Stage) ((Node)event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
