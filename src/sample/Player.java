package sample;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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






class PlayerwithAccount extends Player
{

    //constructors
    PlayerwithAccount(String username, String password , int d_score, int t_score){ //for getting data from database

        super(d_score,t_score);
        this.username = username;
        this.password = password;

    }

    PlayerwithAccount(String username, String password){ //for adding new player
      super(0,0);
        this.username = username;
        this.password = password;
    }

    //fields
    private String username, password;

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword ()
    {
        return this.password;
    }


    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getUsername ()
    {
        return this.username;
    }





    private static List<PlayerwithAccount> users = new ArrayList<>();
    //methods

    public static List<PlayerwithAccount> getAllPlayers() throws SQLException {
        users = PlayerData.getAllPlayers();
        return users;
    }

    public static void addNewUser(PlayerwithAccount newPlayer)
    {
        users.add(newPlayer);
        PlayerData.addPlayer(newPlayer);
    }


    public static PlayerwithAccount checkUser(String username, String pass)
    {
        for (int i= 0; i< users.size(); i++)
        {
            if(users.get(i).getUsername().equalsIgnoreCase(username) && users.get(i).getPassword().equals(pass))
            {
                return users.get(i);
            }
        }

        return null;
    }

}






class Guest extends Player
{
    private String formatName ;


    public String GetName()
    {
        String name = this.formatName;
        return name;
    }


    private Character[] randomName = {
            '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            '@','$','!','&',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    Random random = new Random();


    Guest()
    {
        super(0,0);

        StringBuilder result = new StringBuilder();

        // Select random characters
        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(randomName.length);
            result.append(randomName[randomIndex]);
        }

        formatName = "Guestplayer-" + result;
    }




}