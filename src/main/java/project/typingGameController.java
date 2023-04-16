package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TypingGameController {
    @FXML
    private Text word1, word2, word3, word4, user, timer, wpmText;
    private static List<Text> words = new ArrayList<>();
    private static TypingGame game = new TypingGame();
    
    @FXML
    public static void keyPressed(KeyCode keyCode) throws IOException {
        TypingGameController.game.keyPressedInit(keyCode);  
    }
    @FXML
    public void initialize() throws IOException{
        System.out.println(this.word1.getScene());
        words.add(this.word1);
        words.add(this.word2);
        words.add(this.word3);
        words.add(this.word4);
        game.setWpmText(wpmText);
        game.setUserText(user);
        game.initWords(words);
        game.countDown(this.timer);
    }
    public static List<Text> getWords(){
        return words;
    }
    public Text getUser(){
        return this.user;
    }
    public void highscoreScene() throws IOException{
        System.out.println("hei");
        Parent root = FXMLLoader.load(getClass().getResource("Highscores.fxml"));
        System.out.println(root);
        Stage stage = ((Stage) (word1).getScene().getWindow());
        System.out.println(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
