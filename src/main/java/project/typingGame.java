package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TypingGame {
    private WordsProvider wordsProvider = new WordsProvider();
    private List<Text> wordsArr = new ArrayList<>();
    private static int writtenWords = 0; //words written during the game
    private static Text countDown;
    private static Text wpmText;
    private Text userText;
    private static User playingUser;
    private static int secondsPassed = 0;
    private static int missclicks = 0;
    
    public void setUserText(Text userText){
        this.userText = userText;
    }
    public void setWpmText(Text wpmText){
        TypingGame.wpmText = wpmText;
    }
    public static void setUser(User playingUser) throws IOException{
        TypingGame.playingUser = playingUser;
    }
    public void initWords(List<Text> words) throws IOException{
        this.wordsArr = words;
        for (int i = 0; i < 4; i++) {
            words.get(i).setText(wordsProvider.getFourWords().get(i));
            this.userText.setText(playingUser.getUsername());
        }
    }

    //metode som registrerer tastetrykk, og sjekker om det er første gangen man begynner på et ord eller ikke
    public void keyPressedInit(KeyCode keycode) throws IOException{
            for (Text text : wordsArr) {
                //først må jeg finne ut av om det er noen ord som er blå
                if (text.getText().substring(0,1).toUpperCase().equals(keycode.toString()) && (text.getFill() == Color.BLUE)){
                    if (text.getText().substring(0,1).equals(text.getText())){ //i tillfellet det skal hentes et nytt ord
                        text.setFill(Color.BLACK);
                        writtenWords += 1;
                        this.calculateWPMandAccuracy(); //beregner hvor mye wpm skal ligge på
                        
                    this.wordsArr.get(this.wordsArr.indexOf(text)).setText(wordsProvider.getFourWordsArr(this.wordsArr.indexOf(text)).get(this.wordsArr.indexOf(text)));
                        return;
                    }
                        text.setText(text.getText().substring(1));
                        return;
                        }
        }
        missclicks += 1; // teller antall ganger man treffer feil (alle ganger man trykker feil knapp)
        if (wordsArr.stream()
        .anyMatch(text -> (text.getFill() == Color.BLUE))){
            return;
        }
        //dersom ingen ord er blå så vil alle ord være fem bokstaver, og programmet looper igjen for å finne eventuelle matches
        for (Text text : wordsArr) {
            if (text.getText().substring(0,1).toUpperCase().equals(keycode.toString())){
                missclicks -= 1; //at alle ord er 5 bokstaver og svart farge skal ikke være et missclick
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
        @Override
        public void run() {
            secondsPassed += 1;
            if (secondsPassed % 10 == 0){
                if (secondsPassed >= 60000){
                    try {
                        TypingGame.updateUsers();
                    } catch (IOException e) {
                    cancel();
                    e.printStackTrace();
                    }
                    cancel();
                    return;
                }
                else if(secondsPassed > 59000){
                    TypingGame.countDown.setText("00:" + (60000-secondsPassed+"").toString().substring(1,2) + "0");
                }
                else if(secondsPassed > 50000){
                    TypingGame.countDown.setText("0" + (60000-secondsPassed+"").toString().substring(0,1)+ ":" + (60000-secondsPassed+"").toString().substring(1,3));
                }
                else{
                    TypingGame.countDown.setText((60000-secondsPassed+"").toString().substring(0,2)+ ":" + (60000-secondsPassed+"").toString().substring(2,4));
                }
            }
              }
        }; 
      Timer timer = new Timer("Timer");
      
      timer.schedule(task, 0,1); //gjennomfører en nedtelling hvert sekund 
    }
    public void calculateWPMandAccuracy(){
        float wpm = (writtenWords/((float)secondsPassed/(float) 1000))*60;
        TypingGame.wpmText.setText( (wpm+"").toString().substring(0, 5) + "WPM");
    }
    public static void updateUsers() throws IOException{
        Highscores.updateUsers(playingUser, new Score((writtenWords/((float)secondsPassed/(float) 1000))*60, 100-(missclicks/((float) writtenWords*5))));
    }
}
