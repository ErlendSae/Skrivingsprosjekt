package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class TypingGame {
    private WordsProvider wordsProvider = new WordsProvider();
    private List<Text> wordsArr = new ArrayList<>();
    public void initWords(List<Text> words) throws IOException{
        this.wordsArr = words;
        for (int i = 0; i < 4; i++) {
            words.get(i).setText(wordsProvider.getFourWords().get(i));
        }
    }
    public void keyPressed(KeyCode keycode) throws IOException{
        for (Text text : wordsArr) {
            if (text.getText().substring(0,1).toUpperCase().equals(keycode.toString())){
                if (text.getText().substring(0,1).equals(text.getText())){
                    text.setText(this.wordsProvider.getOneWord());
                    return;
                }
                text.setText(text.getText().substring(1));
                return;
            }
        }
    }
}
