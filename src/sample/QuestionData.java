package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class QuestionData {

    int currentLine = 0;

    private BufferedReader reader = null;


    public <T> void writeInFile(T inputData, String fileName) {
        String write = inputData.toString() + "\n"; //هنا لو عايز تكتب حاجه في الملف اكتبها هنا
        byte[] data = write.getBytes();
        Path p = Paths.get(fileName + ".txt");//هنا هتحط المكان اللي هتعمل فيه الملف ولازم يكون موجود في نفس المكان بتاع المشروع
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException e) {
            System.err.println("error writing in file " + fileName);
        }
    }


    public String readNextLine(String fileName) {
        Path file = Paths.get(fileName + ".txt");

        try {
            // Initialize the reader if it's not already initialized
            if (reader == null) {
                InputStream in = Files.newInputStream(file);
                reader = new BufferedReader(new InputStreamReader(in));
            }

            // Directly read the next line
            String line = reader.readLine();
            if (line != null) {
//                String[] sp = line.split(",");
                currentLine++; // Increment the line counter after reading
//                System.out.println(currentLine);
                return line;
            }

        } catch (IOException e) {
            System.err.println("Error reading file " + fileName);
        }

        return null;
    }


    public List<Question> readAllQuestions(String fileName) {
        List<Question> questions = new ArrayList<>();
        Path file = Paths.get(fileName + ".txt");

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] values = line.split(",");

                if (values.length == 6) {
                    String question = values[0].trim();
                    String answer = values[5].trim();
                    String[] choices = {values[1].trim(), values[2].trim(), values[3].trim(), values[4].trim()};

                    Question q = new Question(question, answer, choices);
                    questions.add(q);


                } else {
                    System.err.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + fileName);
        }

        return questions;
    }


    public void stop() throws Exception {
        if (reader != null) {
            reader.close();
        }
    }


}
