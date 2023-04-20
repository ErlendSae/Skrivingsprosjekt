package project;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javafx.scene.control.TextField;

public class ProsjektTest {
    
    private User user;
    private Score pb;
    private TypingGame game;
    private Highscores highscores;
    private NewAccController register;
    private Login login;
    @BeforeEach
    public void setUp() {
        pb = new Score();
        user = new User("Erlend", "NyttPassord1", pb);
        game = new TypingGame();
        highscores = new Highscores();
        register = new NewAccController();
        login = new Login();
    }

    @Test
    @DisplayName("tester konstruktøren til user")
    public void testConstructorUser(){
        Assertions.assertEquals("Erlend", user.getUsername());
        Assertions.assertEquals("NyttPassord1", user.getPassword());
        Assertions.assertEquals(pb, user.getPb());
    }
    @Test
    @DisplayName("tester konstruktøren til score")
    public void testConstructorScore(){
        Assertions.assertEquals(0, pb.getScore());
        Assertions.assertEquals(0, pb.getAccuracy());
    }

    @Test
    @DisplayName("compareto")
    public void testPasswordValidator(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> login.validateLogin(new TextField("hei"), new TextField("Test123")), "No user with this name");

    }
    
    @Test
    @DisplayName("wordsperminute")
    public void testwordsperminute(){
        game.setSecondsPassed(6000);
        game.setWrittenWords(1);
        int writtenWords = game.getWrittenWords();
        int secondsPassed = game.getSecondsPassed();
        game.calculateWPMandAccuracy();
        Assertions.assertEquals("60WPM",game.getWpmText().getText());
    }

    @Test
    @DisplayName("compareto")
    public void testComparable(){
        Score testScore = new Score(10,0);
        Assertions.assertEquals(-1, pb.compareTo(testScore));
        Assertions.assertEquals(0, pb.compareTo(pb));
        Assertions.assertEquals(1, testScore.compareTo(pb));
    }
    
    @Test
    @DisplayName("tester filhåndtering")
    public void testFil() throws IOException{
        highscores.restoreSavedUsers();
        Assertions.assertEquals(true, highscores.getUsers().keySet().stream().anyMatch(u -> u.getUsername().equals("Erlend")));
        Assertions.assertEquals(true, highscores.getUsers().keySet().stream().anyMatch(u -> u.getPassword().equals("Test123")));
        Assertions.assertEquals(true, highscores.getUsers().values().stream().anyMatch(u -> u.getScore() == (Float.parseFloat("5.0"))));
    }

    @Test
    @DisplayName("tester filhåndtering2 og sortering")
    public void testFil2() throws IOException{
        pb = new Score(50, 90);
        highscores.setPath("src/test/java/project/datalagringTest.txt", false);
        highscores.restoreSavedUsers();
        highscores.updateUsers(user, pb);
        Assertions.assertEquals(true, highscores.getUsers().keySet().stream().anyMatch(u -> u.getUsername().equals("Erlend")));

        Assertions.assertEquals("guest748", highscores.sortUsers(highscores.getUsers(), new ArrayList<>()).get(2).getUsername(), "tester om den med nest mest poeng dukker opp på andreplass");
    }


}
