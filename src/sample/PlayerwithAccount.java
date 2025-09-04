package sample;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerwithAccount extends Player {

    //constructors
    PlayerwithAccount(String username, String password, int d_score, int t_score) { //for getting data from database

        super(d_score, t_score);
        this.username = username;
        this.password = password;

    }

    PlayerwithAccount(String username, String password) { //for adding new player
        super(0, 0);
        this.username = username;
        this.password = password;
    }

    //fields
    private String username, password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }


    private static List<PlayerwithAccount> users = new ArrayList<>();
    //methods

    public static List<PlayerwithAccount> getAllPlayers() throws SQLException {
        users = PlayerData.getAllPlayers();
        return users;
    }

    public static void addNewUser(PlayerwithAccount newPlayer) {
        users.add(newPlayer);
        PlayerData.addPlayer(newPlayer);
    }


    public static PlayerwithAccount checkUser(String username, String pass) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equalsIgnoreCase(username) && users.get(i).getPassword().equals(pass)) {
                return users.get(i);
            }
        }

        return null;
    }

}
