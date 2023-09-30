package project.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class TypingGameController extends StartpageController{
    @FXML
    private Text word1, word2, word3, word4, wpmText, user;
    @FXML
    private Text timer;
    @FXML
    public Button highscoreBtn;

    private static List<Text> words = new ArrayList<>();
    private static TypingGame game = new TypingGame();
    

    @FXML
    public void switchScenes(ActionEvent event) throws IOException{
        this.newWindow(event);
    }
    @FXML
    public static void keyPressed(KeyCode keyCode) throws IOException {
        game.keyPressedInit(keyCode);  
    }
    @FXML
    public void initialize() throws IOException{
        words.add(this.word1);
        words.add(this.word2);
        words.add(this.word3);
        words.add(this.word4);
        game.setWpmText(wpmText);
        game.setUserText(user);
        game.init(words, highscoreBtn);
        game.countDown(timer);
    }
    @FXML
    public Button gettHighscoreBtn(){
        return this.highscoreBtn;
    }
    public static List<Text> getWords(){
        return words;
    }
    public Text getUser(){
        return this.user;
    }
}
