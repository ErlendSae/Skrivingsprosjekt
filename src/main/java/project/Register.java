package project;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Register extends Application{
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Register");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CreateAcc.fxml"))));
        primaryStage.show();
    }
}
