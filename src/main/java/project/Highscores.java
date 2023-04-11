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

    public Map<User,Score> updateUsers(User newUser){
        users.put(newUser, new Score());
        return this.users;
    }

    public Map<User, Score> getUsers() {
        return users;
    }
    public Map<User,Score> restoreSavedUsers() throws IOException{
        saved_users = userToFile.readLines("/project/datalagring.txt", true);
        users = saved_users.stream() //streamer igjennom de ulike linjene fra tekstfilen
        .map(e -> e.split(" ")) //splitter hvert linje basert på mellomrom
        .collect(Collectors.toMap(e -> new User(e[0], e[1]),e -> new Score(Integer.parseInt(e[2]),Float.parseFloat(e[3]))));
        //henter til slutt et map der det første elementet består av en user
        return users;
    }

}
