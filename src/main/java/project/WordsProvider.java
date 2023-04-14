package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class WordsProvider {
    private List<String> all_words = new ArrayList<>();
    private List<String> fourWordsArr = new ArrayList<>();
    private Random random = new Random();

    public List<String> getWords() throws IOException{//henter alle ordene fra filen words.txt
        all_words = UserToFile.readLines("/project/words.txt", true);
        return all_words;
    }

    public List<String> getFourWords() throws IOException{//metode som produserer 4 semi-tilfeldige ord
        this.getWords();
        fourWordsArr.add(all_words.get(random.nextInt(5751)));
        if (fourWordsArr.size() == 1){
            return fourWordsArr;
        }
        else{
            boolean same_first_letter = false;
            for (int i = 0; i < fourWordsArr.size()-1; i++) {
                if (fourWordsArr.get(fourWordsArr.size()-1).substring(0,1).equals(fourWordsArr.get(i).substring(0,1))) {
                    same_first_letter = true;
                }
            }
            if (same_first_letter){
                fourWordsArr.remove(fourWordsArr.size()-1);
                this.getFourWords(); //bruker rekusjon hver gang to forbokstaver er like helt til alle ordene har forskjellig forbokstav 
            }
        }
        return fourWordsArr;
    }

    public String getOneWord(int index) throws IOException{
        this.getWords();
        this.fourWordsArr.remove(index);
        System.out.println(fourWordsArr);
        this.fourWordsArr.add(all_words.get(random.nextInt(5751)));
        boolean same_first_letter = false;
        for (int i = 0; i < fourWordsArr.size()-1; i++) {
            if (fourWordsArr.get(fourWordsArr.size()-1).substring(0,1).equals(fourWordsArr.get(i).substring(0,1))) {
                same_first_letter = true;
            }
        }
        if (same_first_letter){
            fourWordsArr.remove(fourWordsArr.size()-1);
            this.getFourWords(); //bruker rekusjon hver gang to forbokstaver er like helt til alle ordene har forskjellig forbokstav 
        }
        this.fourWordsArr.add(index, this.fourWordsArr.get(this.fourWordsArr.size()-1));
        this.fourWordsArr.add(this.fourWordsArr.get(index));
        this.fourWordsArr.set(index,this.fourWordsArr.get(this.fourWordsArr.size()-1));
        this.fourWordsArr.remove(this.fourWordsArr.size()-1);
        this.fourWordsArr.remove(this.fourWordsArr.size()-1);
        System.out.println(this.fourWordsArr);
        return fourWordsArr.get(fourWordsArr.size()-1); 
        // if (TypingGameController.getWords().stream().anyMatch(w -> newWord.substring(0,1).equals(w.getText().substring(0,1)))){
        //     newWord = all_words.get(random.nextInt(5758));
        //     this.getOneWord();
        // }

    }
    public List<String> getFourWordsArr(int index) throws IOException{
        this.getOneWord(index);
        return this.fourWordsArr;
    }
}