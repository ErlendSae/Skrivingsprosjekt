package project;

public class Score implements Comparable<Score>{
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
    @Override
    public int compareTo(Score arg0) {
        if (this.getScore() > arg0.getScore()){
            return 1;
        }
        else if (this.getScore() < arg0.getScore()){
            return -1;
        }
        return 0;
    }   
    
}
