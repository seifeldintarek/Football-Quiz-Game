package sample;

import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.sql.SQLException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws SQLException {

//        String videoPath = "E:/repos/FootballQuizGame/songmp3.mp3",
//              videoPath2 = "E:/repos/FootballQuizGame/balada.mp3";
//File path =new File (videoPath);

//        Media media = new Media(path.toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);
//        mediaPlayer.setVolume(0.02);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//
//        mediaPlayer.play();


        try {
            PlayerwithAccount.getAllPlayers();
        } catch (SQLException e) {
            System.out.println("error getting players");
        }


        Question.setDeathQuestions(QuestionData.fetchDeathQuestions());
        Question.setTimeQuestions(QuestionData.fetchTimeQuestions());




        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(GameScenes.GameIntro(stage));
        stage.show();

    }

    public static void main(String[] args) {
launch(args);
}
}