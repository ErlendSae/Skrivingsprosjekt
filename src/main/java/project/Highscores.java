package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Highscores {
    private Map<User,Score> users = new HashMap<>();
    
    private List<String> saved_users = new ArrayList<>();

    public Map<User,Score> updateUsers(User newUser, Score score) throws IOException{
        users.put(newUser, score);
        this.restoreSavedUsers();
        List<String> inputArray = new ArrayList<>();
        for (String string : saved_users) {
            inputArray.add(string);
        }
        String test = new String(newUser.toString() +  " " + score.toString());
        inputArray.add(test);
        System.out.println(saved_users);
        UserToFile.writeLines("src/main/resources/project/datalagring.txt", inputArray);
        return this.users;
    }

    public Map<User, Score> getUsers() {
        return users;
    }
    public Map<User,Score> restoreSavedUsers() throws IOException{
        saved_users = UserToFile.readLines("/project/datalagring.txt", true);
        users = saved_users.stream() //streamer igjennom de ulike linjene fra tekstfilen
        .map(element -> element.split(" ")) //splitter hvert linje basert på mellomrom
        .collect(Collectors.toMap(key -> new User(key[0], key[1]),value -> new Score(Float.parseFloat(value[2]),Float.parseFloat(value[3]))));
        //henter til slutt et map der det første elementet består av en user
        return users;
    }

}
