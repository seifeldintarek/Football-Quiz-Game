package sample;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private String answer;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private transient String[] choices;

    public static List<Question> allQuestions = new ArrayList<>();
    public static List<Question> deathQuestions = new ArrayList<>();
    public static List<Question> timeQuestions = new ArrayList<>();

    public Question() {
        this.choices = new String[4];
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String[] getChoices() {
        if (choices == null || choices[0] == null) {
            choices = new String[]{choice1, choice2, choice3, choice4};
        }
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public static void setAllQuestions(List<Question> questions) {
        allQuestions = questions;
        for (Question q : questions) {
            System.out.println(q); // Safe now since choices will be initialized
        }
    }

    public static void setDeathQuestions(List<Question> questions) {
        deathQuestions = questions;
    }

    public static void setTimeQuestions(List<Question> questions) {
        timeQuestions = questions;
    }

    @Override
    public String toString() {
        String[] c = getChoices();
        return "Q: " + question + " | A: " + answer + " | Choices: " +
                ((c != null) ? String.join(", ", c) : "null");
    }
}
