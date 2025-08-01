package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PlayerData {


    public static List getAllPLayers() throws SQLException {


        String url = "jdbc:mysql://localhost:3306/userdata";
        String username = "root";
        String password = "seif2004";

        List<PlayerwithAccount> allPlayers = new ArrayList<>() ;

        // Try-with-resources to automatically close resources
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//            System.out.println("Connected to the database!");

            // Example query
            String query = "SELECT * FROM user";

            // Execute query
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                // Process the result set
                while (resultSet.next()) {
                    String name = resultSet.getString("username");
                    String pass = resultSet.getString("password");
                    int t_score = Integer.parseInt(resultSet.getString("timeScore"));
                    int d_score = Integer.parseInt(resultSet.getString("deathScore"));



                    allPlayers.add( new PlayerwithAccount(name,pass,d_score,t_score) );


                }
                resultSet.close();
            }

            return allPlayers;

        }


    }

    public static void addPlayer(PlayerwithAccount player)
    {
        String url = "jdbc:mysql://localhost:3306/userdata";
        String username = "root";
        String password = "seif2004";

        try(Connection connection = DriverManager.getConnection(url,username,password))
        {

            String query = "INSERT INTO user (username, password) VALUES (?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query))
            {
                statement.setString(1, player.getUsername());
                statement.setString(2, player.getPassword());


                statement.executeUpdate();
            }
            catch (Exception e)
            {
                System.out.println("Error adding a player");
            }

       connection.close();

        } catch (SQLException e) {
            System.out.println("Error in connecting to database , addplayer method");
        }


    }



    public static void setScore (PlayerwithAccount player,int d_score , int t_score)
    {
        String url = "jdbc:mysql://localhost:3306/userdata";
        String username = "root";
        String password = "seif2004";

        try(Connection connection = DriverManager.getConnection(url,username,password))
        {

            String t_query = "UPDATE user SET timeScore = timeScore + ? WHERE username = ?";
            String d_query = "UPDATE user SET deathScore = deathScore + ? WHERE username = ?";

            try (PreparedStatement statement = connection.prepareStatement(t_query)) {
                statement.setInt(1, t_score);
                statement.setString(2, player.getUsername());
            }
            catch (Exception e)
            {
                System.out.println("Error updating t_score");
            }

            try (PreparedStatement statement = connection.prepareStatement(d_query)) {
                statement.setInt(1, d_score);
                statement.setString(2, player.getUsername());

                statement.executeUpdate();
            }
            catch (Exception e)
            {
                System.out.println("Error updating d_score");
            }


            connection.close();

        } catch (SQLException e) {
            System.out.println("Error in connecting to database , addplayer method");
        }
    }


}
