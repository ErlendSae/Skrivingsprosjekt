package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordsProvider {
    private List<String> all_words = new ArrayList<>();
    private List<String> fourWordsArr = new ArrayList<>();
    private Random random = new Random();

    public List<String> getWords() throws IOException{
        all_words = UserToFile.readLines("/project/words.txt", true);
        return all_words;
    }

    public List<String> getFourWords() throws IOException{//metode som produserer 4 tilfeldige ord
        this.getWords();
        for (int index = 0; index < 4; index++) {
            fourWordsArr.add(all_words.get(random.nextInt(5758)));
        }
        return fourWordsArr;
    }

    public String getOneWord() throws IOException{
        this.getWords();
        String newWord;
        newWord = all_words.get(random.nextInt(5758));
        return newWord; 
    }
}