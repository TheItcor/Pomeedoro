package itcor.pomeedoro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import static itcor.pomeedoro.TimeFormatter.interpretSeconds;

public class Main extends Application {
    private Timeline timeline;
    private Label textStatus;
    private Label textTimer;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        var timer = new PomeedoroTimer();
        var root = new BorderPane();


        // Timer Group
        textTimer = new Label(interpretSeconds(timer.getSeconds()));
        textStatus = new Label("Work");
        var timeBox = new VBox(5, textTimer, textStatus);
        timeBox.setAlignment(Pos.CENTER);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            if (timer.isFinished()) {
                System.out.println("Time stops!");
                timeline.stop();
                timer.nextStatus();
                textStatus.setText(timer.getStatusWork());
                textTimer.setText(interpretSeconds(timer.getSeconds()));
            } else {
                timer.tick();
                textTimer.setText(interpretSeconds(timer.getSeconds()));
                System.out.println("seconds: " + timer.getSeconds());
            }
        });

        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE); // Infinity time going

        // Pause/Play/Skip Buttons
        var stopButton = new Button("[x]");
        stopButton.setOnAction(actionEvent -> {
            timeline.stop();
        });

        var playButton = new Button(">");
        playButton.setOnAction(actionEvent -> {
            timeline.play();
        });

        var skipButton = new Button(">>");
        skipButton.setOnAction(actionEvent -> {
            timer.nextStatus();
            textStatus.setText(timer.getStatusWork());
            textTimer.setText(interpretSeconds(timer.getSeconds()));
            timeline.stop();
        });

        var panelBox = new HBox(3, stopButton, playButton, skipButton);
        panelBox.setAlignment(Pos.CENTER);

        // Timer & Buttons
        var centralButtonsBox = new VBox(5, timeBox, panelBox);
        centralButtonsBox.setAlignment(Pos.CENTER);


        // Work & Relax
        var workLabel1 = new Label("[w]");
        var workLabel2 = new Label("[w]");
        var relaxLabel1 = new Label("[r]");
        var relaxLabel2 = new Label("[r]");
        var workRelaxPanel = new VBox(10, workLabel1, relaxLabel1, workLabel2, relaxLabel2);
        workRelaxPanel.setAlignment(Pos.CENTER);


        // Position on root
        root.setCenter(centralButtonsBox); // set Timer & Buttons
        root.setRight(workRelaxPanel);

        var scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(320);
        stage.setHeight(320);
        stage.setTitle("Pomeedoro");
        stage.show();
    }
}
