package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PlayerData {

    static String SUPABASE_URL = "https://waqpnvxigvdxumvxqpvn.supabase.co";
    static String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndhcXBudnhpZ3ZkeHVtdnhxcHZuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTQwNzU0MDAsImV4cCI6MjA2OTY1MTQwMH0.GKLWTTT7DmiPUybOwRiJ04Gu2ID7wd3B4SKkPWD8Meo";
    static String TABLE_URL = SUPABASE_URL + "/rest/v1/players";

    public static List<PlayerwithAccount> getAllPlayers() {
        List<PlayerwithAccount> players = new ArrayList<>();

        try {
            URL url = new URL(TABLE_URL + "?select=*");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("apikey", API_KEY);
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder json = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    json.append(line);
                }
                in.close();

                Gson gson = new Gson();
                players = gson.fromJson(json.toString(), new TypeToken<List<PlayerwithAccount>>() {}.getType());
            } else {
                System.out.println("Failed to fetch players: " + conn.getResponseCode());
            }

        } catch (Exception e) {
            System.out.println("Error fetching players: " + e.getMessage());
        }

        return players;
    }

    public static void addPlayer(PlayerwithAccount player) {
        try {
            URL url = new URL(TABLE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("apikey", API_KEY);
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Prefer", "return=minimal");

            // Exclude transient fields (like created_at)
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.STATIC)
                    .create();
            String jsonInput = gson.toJson(player);
            System.out.println("Sending JSON:\n" + jsonInput);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int response = conn.getResponseCode();
            if (response != 201) {
                System.out.println("Failed to add player: HTTP code " + response);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            } else {
                System.out.println("Player added successfully!");
            }

        } catch (Exception e) {
            System.out.println("Error adding player: " + e.getMessage());
        }
    }


    public static void setScore(PlayerwithAccount player, int d_score, int t_score) {
        try {
            // Update by username (must be unique or indexed)
            String encodedUsername = java.net.URLEncoder.encode("eq." + player.getUsername(), "UTF-8");
            URL url = new URL(TABLE_URL + "?username=" + encodedUsername);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PATCH");
            conn.setDoOutput(true);
            conn.setRequestProperty("apikey", API_KEY);
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");

            JsonObject scores = new JsonObject();
            scores.addProperty("death_score", d_score);
            scores.addProperty("time_score", t_score);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = scores.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int response = conn.getResponseCode();
            if (response != 204) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"))) {
                    StringBuilder responseBuilder = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        responseBuilder.append(responseLine.trim());
                    }
                    System.out.println("Failed to update scores: HTTP code " + response);
                    System.out.println(responseBuilder.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating scores: " + e.getMessage());
        }
    }
}
