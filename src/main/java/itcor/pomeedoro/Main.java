package itcor.pomeedoro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import static itcor.pomeedoro.TimeFormatter.interpretSeconds;

/**
 * Main class provides GUI and appeals to PomeedoroTimer.
 */
public class Main extends Application {
    private Timeline timeline;
    private Label textStatus;
    private Label textTimer;
    private AudioClip beep;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        // Load the alert sound
        beep = new AudioClip(getClass().getResource("/sound/alert.wav").toExternalForm());
        beep.setVolume(0.8);

        var timer = new PomeedoroTimer();   // Pomodoro logics
        var root = new BorderPane();        // GUI plane


        // Timer Group
        textTimer = new Label(interpretSeconds(timer.getSeconds())); // label that shows the time
        textStatus = new Label("Work");                           // label that shows the status (Work or Relax)
        var timeBox = new VBox(5, textTimer, textStatus);
        timeBox.setAlignment(Pos.CENTER);
        // for CSS
        textTimer.setId("timerLabel");
        textStatus.setId("statusLabel");

        // Logics for each second of the GUI timer
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            // IF time is over THEN change status ELSE continue...
            if (timer.isFinished()) {
                Log.info("Timer stops!");
                timeline.stop();
                timer.nextStatus();
                beep.play();

                // Update labels:
                textStatus.setText(timer.getStatusWork());
                textTimer.setText(interpretSeconds(timer.getSeconds()));
            } else {
                timer.tick(); // second--
                textTimer.setText(interpretSeconds(timer.getSeconds()));
                Log.info("Seconds left: " + timer.getSeconds());
            }
        });

        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE); // Infinity time going

        // Pause/Play/Skip Buttons
        // Pause the timer
        var stopButton = new Button("■");
        stopButton.setOnAction(actionEvent -> {
            timeline.stop();
            Log.info("Pause!");
        });

        // Starts the timer
        var playButton = new Button(">");
        playButton.setOnAction(actionEvent -> {
            timeline.play();
            Log.info("Play!");
        });

        // Skips change status: Work -> Relax ; Relax -> Work
        var skipButton = new Button(">>");
        skipButton.setOnAction(actionEvent -> {
            timeline.stop();
            timer.nextStatus();
            // Update labels:
            textStatus.setText(timer.getStatusWork());
            textTimer.setText(interpretSeconds(timer.getSeconds()));
            Log.info("Skip! Status for now: " + timer.getStatusWork());
        });

        // Buttons places
        var panelBox = new HBox(3, stopButton, playButton, skipButton);
        panelBox.setAlignment(Pos.CENTER);


        // Settings/5 min+
        // New modal window for settings
        var dialog = new Stage();
        dialog.initOwner(stage);
        dialog.initModality(Modality.WINDOW_MODAL);
        var planeSettings = new BorderPane();
        var sceneSettings = new Scene(planeSettings, 300, 300);
        sceneSettings.getStylesheets().add(getClass().getResource("/css/settings.css").toExternalForm());
        dialog.setScene(sceneSettings);

        // Settings button
        var settings = new Button("⚙");
        settings.setOnAction(actionEvent -> {
            timeline.stop();
            var textSettings = new Label("Settings");

            // Work Time Spinner
            var workSpinner = new Spinner<Integer>();
            workSpinner.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, (timer.getWORK_TIME_SECONDS()/60), 1)
            ); // Max: 99 minutes, min: 1 minute
            workSpinner.setEditable(true);
            var textWorkTime = new Label("Work time:");
            var workSettings = new HBox(3, textWorkTime, workSpinner);
            workSettings.setAlignment(Pos.CENTER);

            // Relax Time Spinner
            var relaxSpinner = new Spinner<Integer>();
            relaxSpinner.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, (timer.getRELAX_TIME_SECONDS()/60), 1)
            );  // Max: 99 minutes, min: 1 minute
            relaxSpinner.setEditable(true);
            var textRelaxTime = new Label("Relax time:");
            var relaxSettings = new HBox(3, textRelaxTime, relaxSpinner);
            relaxSettings.setAlignment(Pos.CENTER);


            // Ok button
            var okSaveButton = new Button("ok");
            okSaveButton.setOnAction(actionEvent1 -> {
                timer.setRELAX_TIME_SECONDS(relaxSpinner.getValue() * 60);
                Log.info("Relax seconds: " + timer.getRELAX_TIME_SECONDS());

                timer.setWORK_TIME_SECONDS(workSpinner.getValue() * 60);
                Log.info("Work seconds: " + timer.getWORK_TIME_SECONDS());

                dialog.close();
            });

            // Cancel button
            var cancelButton = new Button("cancel");
            cancelButton.setOnAction(actionEvent1 -> {
                dialog.close();
            });

            // Ok & Cancel button
            var okCancelBox = new HBox(3, okSaveButton, cancelButton);
            okCancelBox.setAlignment(Pos.CENTER);


            // Panels & box show
            var panelSettingsBox = new VBox(3, textSettings, workSettings, relaxSettings, okCancelBox);
            panelSettingsBox.setAlignment(Pos.CENTER);
            planeSettings.setCenter(panelSettingsBox);
            dialog.showAndWait();

            timer.resetTimer();
            textTimer.setText(interpretSeconds(timer.getSeconds())); // update timer
        });


        // 5 minutes more
        var fiveMinutes = new Button("5 min+");
        fiveMinutes.setOnAction(actionEvent -> {
            timer.addFiveMinutes();
            textTimer.setText(interpretSeconds(timer.getSeconds()));
            Log.info("Add more 5 minutes.");
        });

        // For CSS
        settings.setId("downPanelButton");
        fiveMinutes.setId("downPanelButton");

        // Buttons places
        var downPanelBox = new HBox(3, settings, fiveMinutes);
        downPanelBox.setAlignment(Pos.CENTER);

        // Timer & Buttons
        var centralButtonsBox = new VBox(5, timeBox, panelBox, downPanelBox);
        centralButtonsBox.setAlignment(Pos.CENTER);


//        // Work & Relax
//        var workLabel1 = new Label("[w]");
//        var workLabel2 = new Label("[w]");
//        var relaxLabel1 = new Label("[r]");
//        var relaxLabel2 = new Label("[r]");
//        var workRelaxPanel = new VBox(10, workLabel1, relaxLabel1, workLabel2, relaxLabel2);
//        workRelaxPanel.setAlignment(Pos.CENTER);


        // Position on root
        root.setCenter(centralButtonsBox); // set Timer & Buttons
//        root.setRight(workRelaxPanel);

        var scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(320);
        stage.setHeight(320);
        stage.setTitle("Pomeedoro");
        stage.show();
    }
}
