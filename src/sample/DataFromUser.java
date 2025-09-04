package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Optional;


public class DataFromUser {

    static Scene signup(Stage stage)
    {


        StackPane fullPane = new StackPane();


//background image
        Image fotballImage = new Image("file:/C:/Users/HP/OneDrive/Pictures/Saved%20Pictures/wallpaper.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(fotballImage,BackgroundRepeat.ROUND,BackgroundRepeat.ROUND,BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false) );

        ImageView imageView = new ImageView(fotballImage);
        GaussianBlur blurEffect = new GaussianBlur(9);  // Adjust the blur radius as needed
        imageView.setEffect(blurEffect);


        fullPane.setBackground(new Background(backgroundImage));


        Font loginFont = Font.font("", FontWeight.BOLD, 40);

        Label loginLabel = new Label("Sign Up");
        loginLabel.setFont(loginFont);
        loginLabel.setTextFill(Color.AZURE);
        loginLabel.setAlignment(Pos.TOP_CENTER);

        //to put title and grid
        VBox vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(30,0,0,0));



        Font font = Font.font("", FontWeight.NORMAL, 20);

        Label userLabel = new Label("Username:");
        TextField userField = new TextField();
        userField.setPromptText("Enter your username");
        userLabel.setFont(font);
        userLabel.setTextFill(Color.WHITE);


        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordLabel.setFont(font);
        passwordLabel.setTextFill(Color.WHITE);


        Label hint = new Label("Username or Password is invalid");
        hint.setVisible(false);
        hint.setFont(Font.font("", FontWeight.BOLD, 30));
        hint.setTextFill(Color.BLACK);


        // Create "Join as Guest" and "Next" buttons
        Font f3 =  Font.font("", FontWeight.NORMAL, 15);

        Button GuestButton = new Button("Join as Guest");
        GuestButton.setFont(f3);
        GuestButton.setStyle(
                "-fx-background-color: #C0C0C0; " +     // Set background color (green)
                        "-fx-text-fill: white; " +                // Set text color (white)
                        "-fx-background-radius: 0; " +           // Rounded corners for background
                        "-fx-border-radius: 0; " +               // Rounded corners for border
                        "-fx-border-color: #C0C0C0; " +           // Set border color
                        "-fx-border-width: 2;"                    // Set border width
        );
        GuestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameScenes.ChooseMode(Optional.of(new Guest()), Optional.empty(), stage);
                stage.setFullScreen(true);
                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                stage.show();
            }
        });

        Button nextButton = new Button("Next");
        nextButton.setFont(f3);
        nextButton.setStyle(
                "-fx-background-color: #C0C0C0; " +     // Set background color (green)
                        "-fx-text-fill: white; " +                // Set text color (white)
                        "-fx-background-radius: 0; " +           // Rounded corners for background
                        "-fx-border-radius: 0; " +               // Rounded corners for border
                        "-fx-border-color: #C0C0C0; " +           // Set border color
                        "-fx-border-width: 2;"                    // Set border width
        );

        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (userField.getText().trim().isEmpty())
                {
                    userField.setStyle("-fx-border-color: red;");
                }
                else if (passwordField.getText().trim().isEmpty())
                {
                    passwordField.setStyle("-fx-border-color: red;");
                }
                else
                {
                    PlayerwithAccount player =  PlayerwithAccount.checkUser(userField.getText(), passwordField.getText());
                    if (player == null)
                    {
                        player = new PlayerwithAccount(userField.getText(), passwordField.getText());
                        PlayerwithAccount.addNewUser(player);
                        GameScenes.ChooseMode(Optional.empty(), Optional.of(player),stage);
                    }
                    else
                    {
                        hint.setVisible(true);
                    }

                }

            }
        });



        Button loginButton = new Button("Login");

        loginButton.setFont(f3);
        loginButton.setStyle(
                "-fx-background-color: #C0C0C0; " +     // Set background color (green)
                        "-fx-text-fill: white; " +                // Set text color (white)
                        "-fx-background-radius: 0; " +           // Rounded corners for background
                        "-fx-border-radius: 0; " +               // Rounded corners for border
                        "-fx-border-color: #C0C0C0; " +           // Set border color
                        "-fx-border-width: 2;"                    // Set border width
        );

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(DataFromUser.login(stage));
                stage.setFullScreen(true);
                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                stage.show();
            }
        });

        // Create a GridPane to arrange the UI components for the second scene
        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);  // Vertical gap between rows
        gridPane.setHgap(15);  // Horizontal gap between columns
        gridPane.setPadding(new Insets(20));  // Padding around the grid

        // Add components to the GridPane

        gridPane.add(userLabel, 0, 0);
        gridPane.add(userField, 1, 0);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(GuestButton, 0, 3);
        gridPane.add(passwordField,1,2);
        gridPane.add(nextButton, 2, 3);
        gridPane.add(loginButton, 1, 3);



        gridPane.setAlignment(Pos.CENTER);
        GridPane.setMargin(GuestButton, new Insets(20, 0, 0, 0));
        GridPane.setMargin(loginButton, new Insets(20, 0, 0, 0));
        GridPane.setMargin(nextButton, new Insets(20, 0, 0, 0));



        gridPane.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(loginLabel,gridPane,hint);
        fullPane.getChildren().addAll(imageView,vBox);


        // Create the scene for the second layout (scene2)
        Scene scene = new Scene(fullPane);

return scene;


    }


    static Scene login(Stage stage)
    {
//        Stage stage = new Stage();
        StackPane fullPane = new StackPane();


//background image
        Image fotballImage = new Image("file:/C:/Users/HP/OneDrive/Pictures/Saved%20Pictures/wallpaper.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(fotballImage,BackgroundRepeat.ROUND,BackgroundRepeat.ROUND,BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false) );

        ImageView imageView = new ImageView(fotballImage);
        GaussianBlur blurEffect = new GaussianBlur(9);  // Adjust the blur radius as needed
        imageView.setEffect(blurEffect);


        fullPane.setBackground(new Background(backgroundImage));


        Font loginFont = Font.font("", FontWeight.BOLD, 40);

        Label loginLabel = new Label("Login");
        loginLabel.setFont(loginFont);
        loginLabel.setTextFill(Color.AZURE);
loginLabel.setAlignment(Pos.TOP_CENTER);

        //to put title and grid
        VBox vBox = new VBox(60);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(30,0,0,0));



        Font font = Font.font("", FontWeight.NORMAL, 20);

        Label userLabel = new Label("Username:");
        TextField userField = new TextField();
        userField.setPromptText("Enter your username");
        userLabel.setFont(font);
        userLabel.setTextFill(Color.WHITE);


        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
       String pass =  passwordField.getText();
        passwordField.setPromptText("Enter your password");
        passwordLabel.setFont(font);
        passwordLabel.setTextFill(Color.WHITE);

        Label hint = new Label("Username or Password is invalid");
        hint.setVisible(false);
        hint.setFont(Font.font("", FontWeight.BOLD, 30));
        hint.setTextFill(Color.BLACK);



        // Create "Join as Guest" and "Next" buttons
        Font f3 =  Font.font("", FontWeight.NORMAL, 15);

        Button guestButton = new Button("Join as Guest");
        guestButton.setFont(f3);
        guestButton.setStyle(
                "-fx-background-color: #C0C0C0; " +     // Set background color (green)
                        "-fx-text-fill: white; " +                // Set text color (white)
                        "-fx-background-radius: 0; " +           // Rounded corners for background
                        "-fx-border-radius: 0; " +               // Rounded corners for border
                        "-fx-border-color: #C0C0C0; " +           // Set border color
                        "-fx-border-width: 2;"                    // Set border width
        );

        guestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameScenes.ChooseMode(Optional.of( new Guest() ) , Optional.empty() , stage );

            }
        });


        Button nextButton = new Button("Next");
        nextButton.setFont(f3);
        nextButton.setStyle(
                "-fx-background-color: #C0C0C0; " +     // Set background color (green)
                        "-fx-text-fill: white; " +                // Set text color (white)
                        "-fx-background-radius: 0; " +           // Rounded corners for background
                        "-fx-border-radius: 0; " +               // Rounded corners for border
                        "-fx-border-color: #C0C0C0; " +           // Set border color
                        "-fx-border-width: 2;"                    // Set border width
        );
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (userField.getText().trim().isEmpty())
                {
                    userField.setStyle("-fx-border-color: red;");
                }
                else if (passwordField.getText().trim().isEmpty())
                {
                    passwordField.setStyle("-fx-border-color: red;");
                }
                else
                {
                  PlayerwithAccount player =  PlayerwithAccount.checkUser(userField.getText(), passwordField.getText());
                    if (player == null)
                        hint.setVisible(true);
                    else
                    {
                        GameScenes.ChooseMode(Optional.empty(), Optional.of(player), stage);
                    }

                }
            }
        });

        Button signUp = new Button("Sign up");
        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                stage.setScene(DataFromUser.signup(stage));
                stage.setFullScreen(true);
                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                stage.show();
            }
        });
        signUp.setFont(f3);
        signUp.setStyle(
                "-fx-background-color: #C0C0C0; " +     // Set background color (green)
                        "-fx-text-fill: white; " +                // Set text color (white)
                        "-fx-background-radius: 0; " +           // Rounded corners for background
                        "-fx-border-radius: 0; " +               // Rounded corners for border
                        "-fx-border-color: #C0C0C0; " +           // Set border color
                        "-fx-border-width: 2;"                    // Set border width
        );

        // Create a GridPane to arrange the UI components for the second scene
        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);  // Vertical gap between rows
        gridPane.setHgap(15);  // Horizontal gap between columns
        gridPane.setPadding(new Insets(20));  // Padding around the grid

        // Add components to the GridPane

        gridPane.add(userLabel, 0, 0);
        gridPane.add(userField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(guestButton, 0, 2);
        gridPane.add(signUp,1,2);
        gridPane.add(nextButton, 2, 2);


        gridPane.setAlignment(Pos.CENTER);
        GridPane.setMargin(guestButton, new Insets(20, 0, 0, 0));
        GridPane.setMargin(signUp, new Insets(20, 0, 0, 0));
        GridPane.setMargin(nextButton, new Insets(20, 0, 0, 0));

        gridPane.setAlignment(Pos.CENTER);
vBox.getChildren().addAll(loginLabel,gridPane, hint);
        fullPane.getChildren().addAll(imageView,vBox);


        // Create the scene for the second layout (scene2)
        Scene scene = new Scene(fullPane);
return scene;
    }
}
