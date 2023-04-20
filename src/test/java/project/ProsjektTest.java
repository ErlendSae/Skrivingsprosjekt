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

import javafx.scene.control.PasswordField;

public class ProsjektTest {
    
    private User user;
    private Score pb;
    private TypingGame game;
    private Highscores highscores;
    private NewAccController register;

    @BeforeEach
    public void setUp() {
        pb = new Score();
        user = new User("Erlend", "Kul1", pb);
        game = new TypingGame();
        highscores = new Highscores();
        register = new NewAccController();
    }

    @Test
    @DisplayName("tester konstruktøren til user")
    public void testConstructorUser(){
        Assertions.assertEquals("Erlend", user.getUsername());
        Assertions.assertEquals("Kul1", user.getPassword());
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
        PasswordField passwordField = new PasswordField();
        passwordField.setText("hei123");
        Assertions.assertThrows(IllegalArgumentException.class, () -> register.validatePassword(passwordField), "password must contain at least one capital letter");

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
    @DisplayName("sorted users")
    public void testSortedUsers(){
        Map<User,Score> unsortedMap = new HashMap<>();
        User user1 = new User("user1", null);
        User user2 = new User("user2", null);
        User user3 = new User("user3", null);
        unsortedMap.put(user, new Score(20, 39));
        unsortedMap.put(user1, new Score(30, 39));
        unsortedMap.put(user2, new Score(40, 39));
        unsortedMap.put(user3, new Score(50, 39));
        List<User> solList = new ArrayList();
        solList.add(user3);
        solList.add(user2);
        solList.add(user1);
        solList.add(user);
        
        highscores.sortUsers(unsortedMap,new ArrayList<>());
        Assertions.assertEquals(solList,highscores.sortUsers(unsortedMap,new ArrayList<>()));
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


}
