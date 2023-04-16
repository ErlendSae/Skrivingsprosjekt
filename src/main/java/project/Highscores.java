package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Highscores{
    private static Map<User,Score> users = new HashMap<>();
    
    private static List<String> saved_users = new ArrayList<>();

    public static Map<User,Score> updateUsers(User newUser, Score score) throws IOException{
        Highscores.restoreSavedUsers();
        boolean match = false;
        boolean forste = true;
        List<String> inputArray = new ArrayList<>();
        for (String string : saved_users) {
            inputArray.add(string);
        }
        for (User user : users.keySet()) {
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
            users.put(newUser, score);
            String newLine = new String(newUser.toString() +  " " + score.toString());
            inputArray.add(newLine);
        }
        UserToFile.writeLines("src/main/resources/project/datalagring.txt", inputArray);
        return users;
    }
    public Map<User,Score> newPb(){
        return users;
    }
    public static Map<User, Score> getUsers() {
        return users;
    }
    public static Map<User,Score> restoreSavedUsers() throws IOException{
        saved_users = UserToFile.readLines("/project/datalagring.txt", true);
        users = saved_users.stream() //streamer igjennom de ulike linjene fra tekstfilen
        .map(element -> element.split(" ")) //splitter hvert linje basert på mellomrom
        .collect(Collectors.toMap(key -> new User(key[0], key[1]),value -> new Score(Float.parseFloat(value[2]),Float.parseFloat(value[3]))));
        //henter til slutt et map der det første elementet består av en user
        return users;
    }
    public void sortUsers(Map<User,Score> unsortedList){

    }
}
