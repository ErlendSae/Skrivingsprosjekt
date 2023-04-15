package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TypingGame {
    private WordsProvider wordsProvider = new WordsProvider();
    private List<Text> wordsArr = new ArrayList<>();
    private static Text countDown;
    
    public void initWords(List<Text> words) throws IOException{
        this.wordsArr = words;
        for (int i = 0; i < 4; i++) {
            words.get(i).setText(wordsProvider.getFourWords().get(i));
        }
    }

    //metode som registrerer tastetrykk, og sjekker om det er første gangen man begynner på et ord eller ikke
    public void keyPressedInit(KeyCode keycode) throws IOException{

            for (Text text : wordsArr) {
                //først må jeg finne ut av om det er noen ord som er blå
                if (text.getText().substring(0,1).toUpperCase().equals(keycode.toString()) && (text.getFill() == Color.BLUE)){
                    if (text.getText().substring(0,1).equals(text.getText())){ //i tillfellet det skal hentes et nytt ord
                        text.setFill(Color.BLACK);
                    this.wordsArr.get(this.wordsArr.indexOf(text)).setText(wordsProvider.getFourWordsArr(this.wordsArr.indexOf(text)).get(this.wordsArr.indexOf(text)));
                        return;
                    }
                        text.setText(text.getText().substring(1));
                        return;
                        }
        }
        if (wordsArr.stream()
        .anyMatch(text -> (text.getFill() == Color.BLUE))){
            return;
        }
        //dersom ingen ord er blå så vil alle ord være fem bokstaver, og programmet looper igjen for å finne eventuelle matches
        for (Text text : wordsArr) {
            if (text.getText().substring(0,1).toUpperCase().equals(keycode.toString())){
                text.setText(text.getText().substring(1));
                text.setFill(Color.BLUE);
                return;
            }
        }
    }
    public List<Text> getWords(){
        return this.wordsArr;
    }
    public void countDown(Text countDown){
    TypingGame.countDown = countDown;
    TimerTask task = new TimerTask() {
        int i = 0;
        @Override
        public void run() {
            i += 1;
            if(i < 10){
                TypingGame.countDown.setText("00:" + "0" + (60-i));
            }
            else if (i == 0){
                
                return;
            }
            TypingGame.countDown.setText("00:" + (60-i));
                System.out.println(i); 
          }
        }; 
      Timer timer = new Timer("Timer");
      
      timer.schedule(task, 0,1000); //gjennomfører en nedtelling hvert sekund 
    }
}
