package project.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import project.util.UserToFile;

public class Highscores{
    private static Map<User,Score> users = new HashMap<>();
    private static List<String> saved_users = new ArrayList<>();
    private static List<Group> highscoresArr = new ArrayList<>();
    private static List<User> sorted_users = new ArrayList<>();
    private String path = "src/main/resources/project/datalagring.txt";
    
    public Map<User,Score> updateUsers(User newUser, Score score) throws IOException{
        this.restoreSavedUsers();

        boolean match = false;
        boolean forste = true;
        List<String> inputArray = new ArrayList<>();
        for (String string : saved_users) {
            inputArray.add(string);
        }
        List<User> usersArr = new ArrayList<>();
        for (User user : users.keySet()) {
            usersArr.add(user);
        }

        //sjekke om navnet allerede finnes i filen
        for (User user : usersArr) {
            if (user.getUsername().equals(newUser.getUsername())){
                match = true;
                if (((int) user.getPb().getScore()) < (int) score.getScore() && forste){
                    forste = false;
                    users.remove(user);
                    users.put(newUser, score);
                    newUser.updatePb(score);
                    for (String string : inputArray) {
                        if (string.split(" ")[0].equals(newUser.getUsername())){
                            inputArray.set(inputArray.indexOf(string), newUser.toString() +  " " + score.toString());
                        }
                    }
                }
            }
        }
        if (!match){
            System.out.println(users);
            users.put(newUser, score);
            System.out.println(users);
            String newLine = new String(newUser.toString() +  " " + score.toString());
            inputArray.add(newLine);
        }
        UserToFile.writeLines(path, inputArray);
        return users;
    }


    public Map<User,Score> newPb(){
        return users;
    }
    public Map<User, Score> getUsers() {
        return users;
    }
    public String setPath(String newPath, boolean resource){
        this.path = newPath;
        return this.path;
    }
    public Map<User,Score> restoreSavedUsers() throws IOException{
        saved_users = UserToFile.readLines("/project/datalagring.txt", true);
        System.out.println(saved_users);
        users = saved_users.stream() //streamer igjennom de ulike linjene fra tekstfilen
        .map(element -> element.split(" ")) //splitter hvert linje basert på mellomrom
        .collect(Collectors.toMap(key -> new User(key[0], key[1]),value -> new Score(Float.parseFloat(value[2]),Float.parseFloat(value[3]))));
        //henter til slutt et map der det første elementet består av en user
        return users;
    }

    public static List<User> sortUsers(Map<User,Score> unsortedMap, List<User> sortedUsers){
        Map<User,Score> unsortedMapClone = new HashMap<>();
        unsortedMapClone = unsortedMap;
        for (User user : unsortedMapClone.keySet()) {
            boolean biggest = true;
            for (User user2 : unsortedMapClone.keySet()) {
                if (unsortedMapClone.get(user).compareTo(unsortedMapClone.get(user2)) == -1){
                    biggest = false;
                }
            }
            if (biggest){
                sortedUsers.add(user);
                unsortedMapClone.remove(user);
                if (unsortedMapClone.size() == 0) {
                    sorted_users = sortedUsers;
                    for (User user2 : sorted_users) {
                        user2.updatePb(users.get(user));
                    }
                    return sortedUsers;
                }
                else{
                    sortUsers(unsortedMapClone, sortedUsers);
                    return sortedUsers;
                }
            }
        }
        return sortedUsers;
    }
    public void setLeaderboard() throws IOException{
        this.restoreSavedUsers();
        System.out.println(users);
            for (Group group : highscoresArr) {
            Text name = new Text();
            name.setFont(Font.font("Castellar", null, null, 14));
            name.setTranslateX(group.getChildren().get(1).getLayoutX());
            name.setTranslateY(group.getChildren().get(1).getLayoutY());
            name.setText(sorted_users.get(highscoresArr.indexOf(group)).getUsername());
            group.getChildren().remove(group.getChildren().get(1));
            group.getChildren().add(name);

            Text wpm = new Text();
            wpm.setFont(Font.font("Castellar", null, null, 14));
            wpm.setTranslateX(group.getChildren().get(1).getLayoutX());
            wpm.setTranslateY(group.getChildren().get(1).getLayoutY());

            for (User user : users.keySet()) {
                if (user.getUsername().equals((sorted_users.get(highscoresArr.indexOf(group)).toString()).split(" ")[0])){
                wpm.setText(users.get(user).getScore()+"WPM");
                }

            }
            group.getChildren().remove(group.getChildren().get(1));
            group.getChildren().add(wpm);

            Text accuracy = new Text();
            accuracy.setFont(Font.font("Castellar", null, null, 14));
            accuracy.setTranslateX(group.getChildren().get(1).getLayoutX());
            accuracy.setTranslateY(group.getChildren().get(1).getLayoutY());
            for (User user : users.keySet()) {
                if (user.getUsername().equals((sorted_users.get(highscoresArr.indexOf(group)).toString()).split(" ")[0])){
                accuracy.setText(users.get(user).getAccuracy()+"ACC");
                }
                
            }
            group.getChildren().remove(group.getChildren().get(1));
            group.getChildren().add(accuracy);
        }
    }
    public static void setLeaderboardArr(List<Group> highscoreArr){
        Highscores.highscoresArr = highscoreArr;
    }
}
