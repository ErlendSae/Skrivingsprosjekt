package project;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Group;

public class HighscoresController {

    @FXML
    private Group scoreGroup1, scoreGroup2, scoreGroup3, scoreGroup4, scoreGroup5, scoreGroup6, scoreGroup7, scoreGroup8;

    @FXML
    public void initialize(){
        List<Group> highscoresArr = new ArrayList<>();
        highscoresArr.add(scoreGroup1);
        highscoresArr.add(scoreGroup2);
        highscoresArr.add(scoreGroup3);
        highscoresArr.add(scoreGroup4);
        highscoresArr.add(scoreGroup5);
        highscoresArr.add(scoreGroup6);
        highscoresArr.add(scoreGroup7);
        highscoresArr.add(scoreGroup8);
        Highscores.setLeaderboardArr(highscoresArr);
    }
}
