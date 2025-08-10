package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SupabaseUploader {
    private static final String SUPABASE_URL = "https://waqpnvxigvdxumvxqpvn.supabase.co/rest/v1/Time_Question";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndhcXBudnhpZ3ZkeHVtdnhxcHZuIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc1NDA3NTQwMCwiZXhwIjoyMDY5NjUxNDAwfQ.V1J8VwZoEPzQbGWpLpoNgXSZ8CtDAflmzBYDtPyXcmY";

    public static void main(String[] args) {
        String filePath = "Time.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNum = 1;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 6) {
                    System.out.println("❌ Line " + lineNum + " skipped: Invalid format");
                    lineNum++;
                    continue;
                }

                String question = parts[0].trim();
                String choice1 = parts[1].trim();
                String choice2 = parts[2].trim();
                String choice3 = parts[3].trim();
                String choice4 = parts[4].trim();
                String answer = parts[5].trim();

                String json = String.format("""
                    {
                        "question": "%s",
                        "choice1": "%s",
                        "choice2": "%s",
                        "choice3": "%s",
                        "choice4": "%s",
                        "answer": "%s"
                    }
                    """, escape(question), escape(choice1), escape(choice2), escape(choice3), escape(choice4), escape(answer));

                boolean success = uploadToSupabase(json);
                if (success) {
                    System.out.println("✅ Line " + lineNum + " uploaded.");
                } else {
                    System.out.println("❌ Line " + lineNum + " failed: " + json);
                }

                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean uploadToSupabase(String json) {
        try {
            URL url = new URL(SUPABASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("apikey", API_KEY);
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Prefer", "return=minimal");
            connection.setDoOutput(true);

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            connection.getOutputStream().write(out);

            int responseCode = connection.getResponseCode();
            return responseCode == 201 || responseCode == 200;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String escape(String input) {
        return input.replace("\"", "\\\"").replace("&", "and");
    }
}
