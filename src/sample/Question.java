package sample;

import java.util.ArrayList;
import java.util.List;

public class Question {


    //fields
   private String question;

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion()
    {
        return this.question;
    }

    private String answer ;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private String[] choices = new String[4];

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public String[] getChoices() {
        return choices;
    }



    //handling questions from files
    private  static List<Question> deathQuestions = new ArrayList<>();
    private  static List<Question> timeQuestions = new ArrayList<>();

    public static void setAllQuestions()
    {
        QuestionData data = new QuestionData();
        deathQuestions = data.readAllQuestions("Death");
        timeQuestions = data.readAllQuestions("Time");
    }

    public static List<Question> getTimeQuestions()
    {
        return timeQuestions;
    }

    public static List<Question> getDeathQuestions()
    {
        return deathQuestions;
    }



   //constructors
Question()
{
    setAllQuestions();
}

    Question(String question, String answer, String[] choices)
    {
        this.question = question;
        this.answer = answer;
        this.choices = choices;
    }

}
