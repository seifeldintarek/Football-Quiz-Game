package sample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QuestionData {

    private static final String SUPABASE_URL = "https://waqpnvxigvdxumvxqpvn.supabase.co";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndhcXBudnhpZ3ZkeHVtdnhxcHZuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTQwNzU0MDAsImV4cCI6MjA2OTY1MTQwMH0.GKLWTTT7DmiPUybOwRiJ04Gu2ID7wd3B4SKkPWD8Meo";
    private static final String DEATH_URL = SUPABASE_URL + "/rest/v1/Death_Question?select=*";
    private static final String TIME_URL = SUPABASE_URL + "/rest/v1/Time_Question?select=*";

    public static List<Question> fetchDeathQuestions() {
        return fetchQuestionsFromUrl(DEATH_URL);
    }

    public static List<Question> fetchTimeQuestions() {
        return fetchQuestionsFromUrl(TIME_URL);
    }

    private static List<Question> fetchQuestionsFromUrl(String endpoint) {
        List<Question> questions = new ArrayList<>();

        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("apikey", API_KEY);
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder json = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    json.append(line);
                }
                in.close();

                Gson gson = new Gson();
                questions = gson.fromJson(json.toString(), new TypeToken<List<Question>>() {}.getType());
            } else {
                System.out.println("Failed to fetch questions: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }
}
