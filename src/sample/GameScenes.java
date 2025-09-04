package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;


public class GameScenes {

    static Scene GameIntro(Stage stage)
    {
        StackPane fullPane = new StackPane();


//background image
        Image fotballImage = new Image("file:/C:/Users/HP/OneDrive/Pictures/Saved%20Pictures/wallpaper.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(fotballImage,BackgroundRepeat.ROUND,BackgroundRepeat.ROUND,BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false) );

        ImageView imageView = new ImageView(fotballImage);
        GaussianBlur blurEffect = new GaussianBlur(3);  // Adjust the blur radius as needed
        imageView.setEffect(blurEffect);


        fullPane.setBackground(new Background(backgroundImage));






        VBox middlePanes = new VBox();
        middlePanes.setPadding(new Insets(60,0,0,0));

        //title
        VBox title = new VBox(5);

        title.setAlignment(Pos.BASELINE_CENTER);



        Label lb1 = new Label("Football Quiz");
//                lb1.setContextMenu(new ContextMenu());


        lb1.setEffect(new Glow(1.0));
        Font f1 = Font.font("", FontWeight.EXTRA_BOLD,FontPosture.ITALIC,  70);
        lb1.setFont(f1);
        lb1.setTextFill(Color.BLACK);




        title.setAlignment(Pos.BASELINE_CENTER);
        title.getChildren().addAll(lb1); //end title


        StackPane.setMargin(title, new javafx.geometry.Insets(20, 0, 0, 0));







        //button start,login,sign up

        Font buttonFont = Font.font("", FontWeight.BOLD, 50);
        Button start = new Button("Start");

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final Guest guest = new Guest();

                GameScenes.ChooseMode(Optional.of(guest),Optional.empty(), stage);
            }
        });
        start.setFont(buttonFont);
        start.setStyle(
                "-fx-background-color: #8B0000; " +     // Set background color (green)
                        "-fx-text-fill: white; " +                // Set text color (white)
                        "-fx-background-radius: 30; " +           // Rounded corners for background
                        "-fx-border-radius: 30; " +               // Rounded corners for border
                        "-fx-border-color: #8B0000; " +           // Set border color
                        "-fx-border-width: 2;"                    // Set border width
        );
        start.setAlignment(Pos.CENTER);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Guest guest = new Guest();
                GameScenes.ChooseMode(Optional.of(guest), Optional.empty(), stage);
                if (guest != null)
                {
                    System.out.println(guest.GetName());
                }
            }
        });



        HBox hbox = new HBox(20);
        hbox.setPadding(new Insets(20,0,0,0));
        hbox.setAlignment(Pos.CENTER);

        Font f3 =  Font.font("", FontWeight.NORMAL, 15);

        Button login = new Button("Login");
        login.setFont(f3);
        login.setStyle(
                "-fx-background-color: #C0C0C0; " +     // Set background color (green)
                        "-fx-text-fill: white; " +                // Set text color (white)
                        "-fx-background-radius: 0; " +           // Rounded corners for background
                        "-fx-border-radius: 0; " +               // Rounded corners for border
                        "-fx-border-color: #C0C0C0; " +           // Set border color
                        "-fx-border-width: 2;"                    // Set border width
        );

        Button newUser = new Button("Sign up");
        newUser.setFont(f3);
        newUser.setStyle(
                "-fx-background-color: #C0C0C0; " +     // Set background color (green)
                        "-fx-text-fill: white; " +                // Set text color (white)
                        "-fx-background-radius: 0; " +           // Rounded corners for background
                        "-fx-border-radius: 0; " +               // Rounded corners for border
                        "-fx-border-color: #C0C0C0; " +           // Set border color
                        "-fx-border-width: 2;"                    // Set border width
        );

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                stage.setScene(DataFromUser.login(stage));
                stage.setFullScreen(true);
                stage.show();
            }
        });

        newUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene( DataFromUser.signup(stage) );
                stage.setFullScreen(true);
                stage.show();
                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

            }
        });


        hbox.getChildren().addAll(login, newUser);












        middlePanes.setAlignment(Pos.CENTER);


        middlePanes.getChildren().addAll(start,hbox); //end middlePanes



        //Sign and LOGin




        fullPane.getChildren().addAll(imageView,title,middlePanes);

        Scene returnedScene = new Scene(fullPane);
        return returnedScene;
    }


    public static void ChooseMode(Optional<Guest> guestOP, Optional<PlayerwithAccount> playerOP, Stage stage)
    {
        final Guest guest = guestOP.orElse(null);
        final  PlayerwithAccount player = playerOP.orElse(null);





        StackPane fullPane = new StackPane();


//background image
        Image fotballImage = new Image("file:/C:/Users/HP/OneDrive/Pictures/Saved%20Pictures/wallpaper.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(fotballImage,BackgroundRepeat.ROUND,BackgroundRepeat.ROUND,BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false) );

        ImageView imageView = new ImageView(fotballImage);
        GaussianBlur blurEffect = new GaussianBlur(8);  // Adjust the blur radius as needed
        imageView.setEffect(blurEffect);


        fullPane.setBackground(new Background(backgroundImage));


        Font titleFont = Font.font("", FontWeight.BOLD, 55);

        Label title = new Label("Choose Mode");
        title.setFont(titleFont);
        title.setTextFill(Color.AZURE);
        title.setAlignment(Pos.TOP_CENTER);



        HBox titlePane = new HBox();
        titlePane.setAlignment(Pos.TOP_CENTER);
        titlePane.setPadding(new Insets(30,0,0,0));

        titlePane.getChildren().add(title);



        VBox choicesPane = new VBox(30);


        Font buttonFont = Font.font("", FontWeight.NORMAL, 35);
        Button deathChallenge = new Button("Death Challenge");

        deathChallenge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (guest == null)
                {

                        new DeathChallenge().Game(Optional.empty(), Optional.of(player), stage);

                }
                else
                {
                    new DeathChallenge().Game(Optional.of(guest), Optional.empty(), stage);

                }


            }
        });
        deathChallenge.setFont(buttonFont);
        deathChallenge.setStyle(
                "-fx-background-color: #8B0000; " +     // Set background color (green)
                        "-fx-text-fill: white; " +                // Set text color (white)
                        "-fx-background-radius: 30; " +           // Rounded corners for background
                        "-fx-border-radius: 30; " +               // Rounded corners for border
                        "-fx-border-color: #8B0000; " +           // Set border color
                        "-fx-border-width: 2;"                    // Set border width
        );



        Button timeChallenge = new Button("Time Challenge");

        timeChallenge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (guest == null)
                {
                    new TimeChallenge().Game(Optional.empty(), Optional.of(player), stage);
                }
                else
                {
                    new TimeChallenge().Game(Optional.of(guest), Optional.empty(), stage);
                }

            }
        });
        timeChallenge.setFont(buttonFont);
        timeChallenge.setStyle(
                "-fx-background-color: #8B0000; " +     // Set background color (green)
                        "-fx-text-fill: white; " +                // Set text color (white)
                        "-fx-background-radius: 30; " +           // Rounded corners for background
                        "-fx-border-radius: 30; " +               // Rounded corners for border
                        "-fx-border-color: #8B0000; " +           // Set border color
                        "-fx-border-width: 2;"                    // Set border width
        );

        Button leaderboard = new Button("Leaderboard");
        leaderboard.setFont(buttonFont);
        leaderboard.setStyle(
                "-fx-background-color: #8B0000; " +     // Set background color (green)
                "-fx-text-fill: white; " +                // Set text color (white)
                "-fx-background-radius: 30; " +           // Rounded corners for background
                "-fx-border-radius: 30; " +               // Rounded corners for border
                "-fx-border-color: #8B0000; " +           // Set border color
                "-fx-border-width: 2;"                    // Set border width
        );

leaderboard.setOnAction(
        new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Leaderboard.showRanking();
                } catch (SQLException e) {
                    System.out.println("Error showing ranking in choosing mode");
                }
            }
        }
);

        choicesPane.setAlignment(Pos.CENTER);
        choicesPane.getChildren().addAll(deathChallenge, timeChallenge, leaderboard);


        fullPane.getChildren().addAll(imageView,titlePane, choicesPane);
        Scene scene = new Scene(fullPane);

        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }


    static void EndGame (Stage stage, Optional<Guest> guestOP, Optional<PlayerwithAccount> playerOP , Boolean mode, int score)
    {
        //mode is true if death & False if Time
        PlayerwithAccount player = playerOP.orElse(null);
        Guest guest = guestOP.orElse(null);


        StackPane fullPane = new StackPane();


        // Background image
        Image footballImage = new Image("file:/C:/Users/HP/OneDrive/Pictures/Saved%20Pictures/wallpaper.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(footballImage, BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false));

        ImageView imageView = new ImageView(footballImage);
        GaussianBlur blurEffect = new GaussianBlur(5); // Adjust the blur radius as needed
        imageView.setEffect(blurEffect);

        fullPane.setBackground(new Background(backgroundImage));

        // Create the first scene content
        VBox labelsBox = new VBox(5); // Space between the labels
        labelsBox.setAlignment(Pos.TOP_CENTER);
        labelsBox.setPadding(new Insets(50, 0, 0, 0)); // Add some padding at the top

        Label mainLabel = new Label("Good Game");
        mainLabel.setEffect(new Glow(0.6));
        Font titleFont = Font.font("", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 50);
        mainLabel.setFont(titleFont);
        mainLabel.setTextFill(Color.WHITE);

        Label subLabel = new Label("Score: " + score);
        Font subTitleFont = Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 25);
        subLabel.setFont(subTitleFont);
        subLabel.setTextFill(Color.CYAN);



        labelsBox.getChildren().addAll(mainLabel, subLabel);

        HBox buttonsBox = new HBox(20); // Space between buttons
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setPadding(new Insets(50, 0, 50, 0)); // Add some padding at the bottom

        Button startButton = new Button("Back to Main");
        Button leaderboardButton = new Button("View Leaderboard");
        Button endButton = new Button("End");

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (guest == null)
                {
                    GameScenes.ChooseMode(Optional.empty(),Optional.of(player),stage);

                }
                else
                {
                    GameScenes.ChooseMode(Optional.of(guest),Optional.empty(),stage);
                }

            }
        });

leaderboardButton.setOnAction(
        new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Leaderboard.showRanking();
                } catch (SQLException e) {
                   System.out.println("Error showing ranking");
                }
            }
        }
);

        Font buttonFont = Font.font("", FontWeight.BOLD, 20);
        startButton.setFont(buttonFont);
        endButton.setFont(buttonFont);

        startButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10 20;");
        endButton.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-padding: 10 20;");
        leaderboardButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-padding: 10 20;");

        buttonsBox.getChildren().addAll(startButton,leaderboardButton, endButton);

        VBox contentBox = new VBox(20); // Space between labels and buttons
        contentBox.setAlignment(Pos.CENTER);
        contentBox.getChildren().addAll(labelsBox, buttonsBox);

        fullPane.getChildren().add(contentBox);

        // Create the scene
        Scene scene = new Scene(fullPane);



        // Event handling for the "End" button
        endButton.setOnAction(event -> EndScene(fullPane, stage, buttonFont));


        // Set up the stage
        stage.setTitle("Football Quiz");
        stage.setScene(scene);
        stage.setFullScreen(true); // Enable fullscreen mode
        stage.show();
    }



     static void EndScene(StackPane fullPane, Stage stage, Font buttonFont) {
        // Clear the current children from the fullPane
        fullPane.getChildren().clear();

        // Create new content for the next scene
        Label goodbyeLabel = new Label("Did You Enjoy Our Game?");
        goodbyeLabel.setFont(Font.font("", FontWeight.BOLD, 40));
        goodbyeLabel.setTextFill(Color.WHITE);

        Button NoButton = new Button("No");
        Button yesButton = new Button("Yes");
        yesButton.setFont(buttonFont);
        yesButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10 20;");
        yesButton.setOnAction(e -> stage.close()); // Close the application

        NoButton.setFont(buttonFont);
        NoButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-padding: 10 20;");

        HBox buttonBox = new HBox(10, yesButton, NoButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox newContent = new VBox(20, goodbyeLabel, buttonBox);
        newContent.setAlignment(Pos.CENTER);

        // Add the new content to the fullPane
        fullPane.getChildren().add(newContent);

        // Add functionality to move the No button to a random position
        Random random = new Random();
        NoButton.setOnAction(e -> {
            // Generate random positions for the No button
            double randomX = random.nextDouble() * 200;
            double randomY = random.nextDouble() * 200;

            // Move the No button to a new position
            NoButton.setTranslateX(randomX);
            NoButton.setTranslateY(randomY);
        });
    }


}
