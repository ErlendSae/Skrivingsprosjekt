package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TypingGame{
    private WordsProvider wordsProvider = new WordsProvider();
    private  int writtenWords = 0; //words written during the game
    private int missclicks = 0; //antall bokstaver man har bommet

    private Text countDown;
    private static Text wpmText;
    private Text userText;
    private Button highscoreBtn;
    private static List<Text> wordsArr = new ArrayList<>();

    private int milliseconds = 60000;
    private static User playingUser;
    private Highscores highscores = new Highscores();

    
    public void setUserText(Text userText){
        this.userText = userText;
    }
    public void setWpmText(Text wpmText){
        TypingGame.wpmText = wpmText;
    }
    public Text getWpmText(){
        return TypingGame.wpmText;
    }
    public static void setUser(User playingUser) throws IOException{
        TypingGame.playingUser = playingUser;
    }
    public void init(List<Text> words, Button highscoreBtn) throws IOException{
        this.highscoreBtn = highscoreBtn;
        TypingGame.wordsArr = words;
        for (int i = 0; i < 4; i++) {
            words.get(i).setText(wordsProvider.getFourWords().get(i));
            this.userText.setText(playingUser.getUsername());
            this.highscores.updateUsers(playingUser, new Score());
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
                        
                        TypingGame.wordsArr.get(TypingGame.wordsArr.indexOf(text)).setText(wordsProvider.getFourWordsArr(TypingGame.wordsArr.indexOf(text)).get(TypingGame.wordsArr.indexOf(text)));
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
        return wordsArr;
    }

    
    public void countDown(Text countDown){
    this.countDown = countDown;
    final Timeline timeline = new Timeline();
    timeline.setCycleCount(60000);
    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), eachSecond()));
    timeline.play();
    timeline.setOnFinished(this.replaceWithHighscore());
    }

    private EventHandler<ActionEvent> eachSecond(){
        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String currentText = TypingGame.this.countDown.getText();
                TypingGame.this.milliseconds -= 1;
                if (TypingGame.this.milliseconds < 10000){ //dersom det er under 10 sekunder igjen

                    if (TypingGame.this.milliseconds < 100){
                        TypingGame.this.countDown.setText("00:" + ("" + TypingGame.this.milliseconds));
                    }
                    else{
                        if (Integer.parseInt((""+TypingGame.this.milliseconds).substring(1)) < 10){ //dersom det er under 10 millisekunder igjen og under 10 sekunder igjen
                            TypingGame.this.countDown.setText("0" + ("" + TypingGame.this.milliseconds).substring(0,1) + ":" + "0" + ("" + TypingGame.this.milliseconds).substring(1,2));
                        };
                        TypingGame.this.countDown.setText("0" + ("" + TypingGame.this.milliseconds).substring(0,1) + ":" + ("" + TypingGame.this.milliseconds).substring(1,3));
                    }

                }
                else{
                    if (Integer.parseInt((""+TypingGame.this.milliseconds).substring(2)) < 10){ //dersom det er under 10 millisekunder igjen
                        TypingGame.this.countDown.setText(("" + TypingGame.this.milliseconds).substring(0,2) + ":" + "0" + ("" + TypingGame.this.milliseconds).substring(2,4)); 
                    };
                    TypingGame.this.countDown.setText(("" + TypingGame.this.milliseconds).substring(0,2) + ":" + ("" + TypingGame.this.milliseconds).substring(2,4));    
                }

            }

        };

    }

    //When the timer runs out - send to highscore-screen
    private EventHandler<ActionEvent> replaceWithHighscore() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    TypingGame.this.updateUsers();
                    TypingGame.this.highscores.sortUsers(TypingGame.this.highscores.getUsers(), new ArrayList<>());
                    TypingGame.this.highscoreBtn.fire();
                    TypingGame.this.highscores.setLeaderboard();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        };
    }

    public void calculateWPMandAccuracy(){
        if (writtenWords <= 0){
            throw new IllegalStateException("words typed must be at least 1");
        }
        TypingGame.playingUser.updatePb(new Score((this.writtenWords*1000/(60000-((float)this.milliseconds)))*60, 100-(((writtenWords/5)/ ((float) missclicks+(writtenWords/5)))*100)));
        TypingGame.wpmText.setText((TypingGame.playingUser.getPb().getScore()+"").toString().substring(0, 4) + "WPM");
    }
    public void updateUsers() throws IOException{
        this.highscores.updateUsers(playingUser, new Score((this.writtenWords*1000/(60000-((float)this.milliseconds)))*60, 100-(((writtenWords/5)/ ((float) missclicks+(writtenWords/5)))*100)));
    }
}
