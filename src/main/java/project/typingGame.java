package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class TypingGame {
    WordsProvider wordsProvider = new WordsProvider();
    public void initWords(List<Text> words) throws IOException{
        for (int i = 0; i < 4; i++) {
            words.get(i).setText(wordsProvider.getFourWords().get(i));
        }
    }
    public void keyPressed(KeyCode keycode){

    }
}
