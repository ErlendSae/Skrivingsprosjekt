package project;

public class Score {
    private int score;
    private float accuracy;

    public Score(){
        this.score = 0;
        this.accuracy = 0;
    }
    public Score(int score, float accuracy) {
        this.score = score;
        this.accuracy = accuracy;
    }
    public float getScore() {
        return score;
    }
    public float getAccuracy() {
        return accuracy;
    }
    @Override
    public String toString() {
        return score + " " + accuracy;
    }   
     
}
