package project;

public class Score {
    private float wpm;
    private float accuracy;

    public Score(){
        this.wpm = 0;
        this.accuracy = 0;
    }
    public Score(float wpm, float accuracy) {
        this.wpm = wpm;
        this.accuracy = accuracy;
    }
    public float getScore() {
        return wpm;
    }
    public float getAccuracy() {
        return accuracy;
    }
    @Override
    public String toString() {
        return wpm + " " + accuracy;
    }   
     
}
