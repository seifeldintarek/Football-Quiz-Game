package sample;

public abstract class Player {

    private int death_score, time_score;


    Player(int ds, int ts)
    {
        this.death_score = ds;
        this.time_score = ts;
    }

    public int getDeathScore()
    {
        return this.death_score;
    }

    public void setDeathScore(int score)
    {
        this.death_score = this.death_score + score;
    }

    public int getTimeScore(){
        int score = this.time_score;
        return score;
    }

    public void setTimeScore(int score)
    {
        this.time_score = this.time_score + score;
    }




}


