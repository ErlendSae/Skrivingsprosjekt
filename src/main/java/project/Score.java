package project;

public class Score {
    private float score;
    private float accuracy;

    public Score(){
        this.score = 0;
        this.accuracy = 0;
    }
    public Score(float score, float accuracy) {
        this.score = score;
        this.accuracy = accuracy;
    }
    public float getScore() {
        return score;
    }
    public float getAccuracy() {
        return accuracy;
    }    
}
