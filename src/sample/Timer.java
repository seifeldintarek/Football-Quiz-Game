package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class Timer {

    private static Timeline timeline;
    private static boolean timerRunning = false;
    private static int remainingTime ;
    private static Runnable timeoutAction;

    public static void createTimer(Label timerLabel,int timeDuration) {

        if (timeline != null) {
            timeline.stop(); // Stop any existing timeline
        }

        remainingTime = timeDuration; // Reset to initial duration
        timerLabel.setText("Time Left: " + remainingTime);

        // Create a new Timeline to handle the countdown
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (remainingTime > 0) {
                remainingTime--;
                timerLabel.setText("Time Left: " + remainingTime);
            } else {
                timerLabel.setText("Time's up!");
                timeline.stop();  // Stop the timer when it runs out
                timerRunning = false; // Reset the running flag
                if (timeoutAction != null) { // Trigger the timeout action
                    timeoutAction.run();
                }
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        timerRunning = true; // Mark the timer as running
    }

    public static void resetTimer(Label timerLabel, int timerDuration) {
        if (timeline != null) {
            timeline.stop();
        }
        remainingTime = timerDuration;
        timerLabel.setText("Time Left: " + remainingTime);
        createTimer(timerLabel,15); // Start the timer again
    }


    public static void setOnTimeout(Runnable action) {
        timeoutAction = action;
    }

    public static boolean isTimeUp() {
        return remainingTime == 0;
    }

    }
