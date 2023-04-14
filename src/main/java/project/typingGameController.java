package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class TypingGameController {
    @FXML
    private Text word1, word2, word3, word4, user;
    private static List<Text> words = new ArrayList<>();
    private static TypingGame game = new TypingGame();
    
    @FXML
    public static void keyPressed(KeyCode keyCode) throws IOException {
        TypingGameController.game.keyPressedInit(keyCode);  
    }
    @FXML
    public void initialize() throws IOException{
        words.add(this.word1);
        words.add(this.word2);
        words.add(this.word3);
        words.add(this.word4);
        game.initWords(words);
    }
    public static List<Text> getWords(){
        return words;
    }
}
