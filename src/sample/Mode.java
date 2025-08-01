package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public interface Mode {
    public abstract void Game(Optional<Guest> guestOP , Optional<PlayerwithAccount> playerOP, Stage stage) throws IOException;

    public abstract void changeButtonStyle(Button selected,Button b2, Button b3, Button b4);

}



class DeathChallenge implements Mode {
    @Override
    public void changeButtonStyle(Button selected, Button b2, Button b3, Button b4)
    {
        selected.setStyle("-fx-background-color: darkgreen; -fx-text-fill: black;");
        b2.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        b3.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        b4.setStyle("-fx-background-color: white; -fx-text-fill: black;");

    }


    public void Game(Optional<Guest> guestOP, Optional<PlayerwithAccount> playerOP, Stage stage) {


        //background

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        StackPane fullpane = new StackPane();

        Image fotballImage = new Image("file:/C:/Users/HP/OneDrive/Pictures/Saved%20Pictures/pitch.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(fotballImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false));

        ImageView imageView = new ImageView(fotballImage);

        imageView.setFitHeight(screenHeight);
        imageView.setFitWidth(screenWidth);

        fullpane.setBackground(new Background(backgroundImage));




        // Begin Game

        AtomicInteger index = new AtomicInteger(0), score = new AtomicInteger(0);
        AtomicReference<Button> selected = new AtomicReference<>(null);


        List<Question> questions = Question.getDeathQuestions();
        final int length = questions.size();
        String[] choices = questions.get(index.get()).getChoices();


        Guest guest = guestOP.orElse(null);
        PlayerwithAccount player = playerOP.orElse(null);


        //Question
        Label questionLabel = new Label(questions.get(index.get()).getQuestion());
        questionLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 30));
        questionLabel.setAlignment(Pos.CENTER);
        questionLabel.setStyle("-fx-text-fill: darkred;");
        questionLabel.setPadding(new Insets(0, 0, 40, 0));




        //Choices

        Font buttonFont = Font.font("", FontWeight.BOLD, 25);

        Button optionA = new Button(choices[0]);
        Button optionB = new Button(choices[1]);
        Button optionC = new Button(choices[2]);
        Button optionD = new Button(choices[3]);

        optionA.setFont(buttonFont);
        optionA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                changeButtonStyle(optionA, optionB, optionC, optionD);
                selected.set(optionA);
            }
        });


        optionB.setFont(buttonFont);
        optionB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                changeButtonStyle(optionB, optionA, optionC, optionD);
                selected.set(optionB);
            }
        });


        optionC.setFont(buttonFont);
        optionC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                changeButtonStyle(optionC, optionB, optionA, optionD);
                selected.set(optionC);
            }
        });

        optionD.setFont(buttonFont);
        optionD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                changeButtonStyle(optionD, optionB, optionC, optionA);
                selected.set(optionD);
            }
        });



        //score Label
        Label scoreLabel = new Label();
        scoreLabel.setTextFill(Color.DARKGRAY);
        scoreLabel.setText("Your Score : " + score.get());
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(Font.font("", FontWeight.BOLD, 30));




        //Timer

        Label timerLabel = new Label("Time Left: 15");
        Timer.createTimer(timerLabel,15);
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(Font.font("", FontWeight.BOLD,30));

        Timer.setOnTimeout(() -> {
            int nextIndex = index.incrementAndGet();

            if (Timer.isTimeUp())
            {
                if (guest == null)
                {
                    player.setDeathScore(score.get());
                    PlayerData.setScore(player, score.get(), 0);
                    GameScenes.EndGame(stage, Optional.empty(), Optional.of(player), true, score.get());
                }
                else
                {
                    guest.setDeathScore(score.get());
                    GameScenes.EndGame(stage,Optional.of(guest), Optional.empty(), true,score.get());

                }

            }
        });



//submit button
        Button submit = new Button("Submit");
        submit.setAlignment(Pos.CENTER);
        submit.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-text-fill: white;");
        submit.setFont(Font.font("", FontWeight.MEDIUM, 20));
        submit.setPadding(new Insets(20, 70, 20, 70));
        submit.setAlignment(Pos.CENTER);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //check answer

                Button clickedButton = selected.get();
                int currentIndex = index.get();
                System.out.println(currentIndex);

                System.out.println(questions.get(currentIndex).getQuestion());
                System.out.println(questions.get(currentIndex).getAnswer());
                System.out.println(clickedButton.getText());


                if (clickedButton.getText().trim().equalsIgnoreCase(questions.get(currentIndex).getAnswer().trim())) {
                    score.getAndIncrement();
                    System.out.println("Score: " + score);
                    scoreLabel.setText("Your Score : " + score.get());

                }
                else {

                    if (player == null)
                    {
                        GameScenes.EndGame(stage,Optional.of(guest), Optional.empty(), true, score.get());
                    }
                    else
                    {
                        GameScenes.EndGame(stage, Optional.empty(), Optional.of(player), true,score.get());
                    }

                }


                optionA.setStyle("-fx-background-color: white; -fx-text-fill: white; -fx-text-fill: black;");
                optionB.setStyle("-fx-background-color: white; -fx-text-fill: white; -fx-text-fill: black;");
                optionC.setStyle("-fx-background-color: white; -fx-text-fill: white; -fx-text-fill: black;");
                optionD.setStyle("-fx-background-color: white; -fx-text-fill: white; -fx-text-fill: black;");



                //change question

                int nextIndex = index.incrementAndGet();

                if (nextIndex == length)
                {
                    GameScenes.ChooseMode(Optional.ofNullable(guest), Optional.ofNullable(player), stage);
                }
                else  if(nextIndex < length )
                {

                    questionLabel.setText(questions.get(nextIndex).getQuestion());

                    // Update the choices for the new question
                    String[] choices = questions.get(nextIndex).getChoices();

                    optionA.setText(choices[0]);
                    optionB.setText(choices[1]);
                    optionC.setText(choices[2]);
                    optionD.setText(choices[3]);
                }


                //reset timer
                Timer.resetTimer(timerLabel,15);


            }
        });





        //Layout

        HBox submitLayout = new HBox(submit);
        submitLayout.setAlignment(Pos.CENTER);
        submitLayout.setPadding(new Insets(0,0,125,0));



        VBox buttons = new VBox(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(questionLabel,optionA, optionB, optionC, optionD);
        buttons.setPadding(new Insets(0));


        HBox scoreANDtimer = new HBox();
        scoreANDtimer.getChildren().addAll(scoreLabel, timerLabel);
        scoreANDtimer.setPadding(new Insets(20,20,0,20));
        scoreANDtimer.setSpacing(screenWidth/5);
        scoreANDtimer.setAlignment(Pos.CENTER);

        BorderPane gamePanes = new BorderPane();
        gamePanes.setCenter(buttons);
        gamePanes.setTop(scoreANDtimer);
        gamePanes.setBottom(submitLayout);





        fullpane.getChildren().addAll(imageView, gamePanes);
        Scene scene = new Scene(fullpane);


        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }
}


class TimeChallenge implements Mode {



    @Override
    public void changeButtonStyle(Button selected, Button b2, Button b3, Button b4)
    {
        selected.setStyle("-fx-background-color: darkgreen; -fx-text-fill: black;");
        b2.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        b3.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        b4.setStyle("-fx-background-color: white; -fx-text-fill: black;");

    }



    public void Game(Optional<Guest> guestOP, Optional<PlayerwithAccount> playerOP, Stage stage) {


        //background

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        StackPane fullpane = new StackPane();

        Image fotballImage = new Image("file:/C:/Users/HP/OneDrive/Pictures/Saved%20Pictures/pitch.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(fotballImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false));

        ImageView imageView = new ImageView(fotballImage);

        imageView.setFitHeight(screenHeight);
        imageView.setFitWidth(screenWidth);

        fullpane.setBackground(new Background(backgroundImage));




        // Begin Game

        AtomicInteger index = new AtomicInteger(0), score = new AtomicInteger(0);
        AtomicReference<Button> selected = new AtomicReference<>(null);

        List<Question> questions = Question.getTimeQuestions();
        final int length = questions.size();
        String[] choices = questions.get(index.get()).getChoices();


        Guest guest = guestOP.orElse(null);
        PlayerwithAccount player = playerOP.orElse(null);


        //Question
        Label questionLabel = new Label(questions.get(index.get()).getQuestion());
        questionLabel.setFont(Font.font("", FontWeight.EXTRA_BOLD, 30));
        questionLabel.setAlignment(Pos.CENTER);
        questionLabel.setStyle("-fx-text-fill: darkred;");
        questionLabel.setPadding(new Insets(0, 0, 40, 0));




        //Choices

        Font buttonFont = Font.font("", FontWeight.BOLD, 25);

        Button optionA = new Button(choices[0]);
        Button optionB = new Button(choices[1]);
        Button optionC = new Button(choices[2]);
        Button optionD = new Button(choices[3]);

        optionA.setFont(buttonFont);
        optionA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                changeButtonStyle(optionA, optionB, optionC, optionD);
                selected.set(optionA);
            }
        });


        optionB.setFont(buttonFont);
        optionB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                changeButtonStyle(optionB, optionA, optionC, optionD);
                selected.set(optionB);
            }
        });


        optionC.setFont(buttonFont);
        optionC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                changeButtonStyle(optionC, optionB, optionA, optionD);
                selected.set(optionC);
            }
        });

        optionD.setFont(buttonFont);
        optionD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                changeButtonStyle(optionD, optionB, optionC, optionA);
                selected.set(optionD);
            }
        });



        //score Label
        Label scoreLabel = new Label();
        scoreLabel.setTextFill(Color.DARKGRAY);
        scoreLabel.setText("Your Score : " + score.get());
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(Font.font("", FontWeight.BOLD, 30));




        //Timer

        Label timerLabel = new Label("Time Left: 89");
        Timer.createTimer(timerLabel,89);
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(Font.font("", FontWeight.BOLD,30));

        Timer.setOnTimeout(() -> {
            int nextIndex = index.incrementAndGet();

            if (Timer.isTimeUp())
            {
                if (guest == null)
                {
                    player.setTimeScore(score.get());
                    PlayerData.setScore(player, 0,score.get());
                    GameScenes.EndGame(stage, Optional.empty(), Optional.of(player), true, score.get());
                }
                else
                {
                    guest.setTimeScore(score.get());
                    GameScenes.EndGame(stage,Optional.of(guest), Optional.empty(), true, score.get());
                }

            }
        });



//submit button
        Button submit = new Button("Submit");
        submit.setAlignment(Pos.CENTER);
        submit.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-text-fill: white;");
        submit.setFont(Font.font("", FontWeight.MEDIUM, 20));
        submit.setPadding(new Insets(20, 70, 20, 70));
        submit.setAlignment(Pos.CENTER);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //check answer

                Button clickedButton = selected.get();
                int currentIndex = index.get();
                System.out.println(currentIndex);

                System.out.println(questions.get(currentIndex).getQuestion());
                System.out.println(questions.get(currentIndex).getAnswer());
                System.out.println(clickedButton.getText());


                if (clickedButton.getText().trim().equalsIgnoreCase(questions.get(currentIndex).getAnswer().trim())) {
                    score.getAndIncrement();
                    System.out.println("Score: " + score);
                    scoreLabel.setText("Your Score : " + score.get());

                }



                optionA.setStyle("-fx-background-color: white; -fx-text-fill: white; -fx-text-fill: black;");
                optionB.setStyle("-fx-background-color: white; -fx-text-fill: white; -fx-text-fill: black;");
                optionC.setStyle("-fx-background-color: white; -fx-text-fill: white; -fx-text-fill: black;");
                optionD.setStyle("-fx-background-color: white; -fx-text-fill: white; -fx-text-fill: black;");



                //change question

                int nextIndex = index.incrementAndGet();

                if (nextIndex == length)
                {
                    if (player == null)
                    {
                        GameScenes.EndGame(stage,Optional.of(guest), Optional.empty(), true,score.get());
                    }
                    else
                    {
                        GameScenes.EndGame(stage, Optional.empty(), Optional.of(player), true, score.get());
                    }

                }
                else  if(nextIndex < length )
                {

                    questionLabel.setText(questions.get(nextIndex).getQuestion());

                    // Update the choices for the new question
                    String[] choices = questions.get(nextIndex).getChoices();

                    optionA.setText(choices[0]);
                    optionB.setText(choices[1]);
                    optionC.setText(choices[2]);
                    optionD.setText(choices[3]);
                }


            }
        });





        //Layout

        HBox submitLayout = new HBox(submit);
        submitLayout.setAlignment(Pos.CENTER);
        submitLayout.setPadding(new Insets(0,0,125,0));



        VBox buttons = new VBox(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(questionLabel,optionA, optionB, optionC, optionD);
        buttons.setPadding(new Insets(0));


        HBox scoreANDtimer = new HBox();
        scoreANDtimer.getChildren().addAll(scoreLabel, timerLabel);
        scoreANDtimer.setPadding(new Insets(20,20,0,20));
        scoreANDtimer.setSpacing(screenWidth/5);
        scoreANDtimer.setAlignment(Pos.CENTER);

        BorderPane gamePanes = new BorderPane();
        gamePanes.setCenter(buttons);
        gamePanes.setTop(scoreANDtimer);
        gamePanes.setBottom(submitLayout);





        fullpane.getChildren().addAll(imageView, gamePanes);
        Scene scene = new Scene(fullpane);

        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }
}