package itcor.pomeedoro;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        var root = new BorderPane();


        // Timer Group
        var textTimer = new Label("00:00");
        var textStatus = new Label("Relax");
        var timeBox = new VBox(5, textTimer, textStatus);
        timeBox.setAlignment(Pos.CENTER);

        // Pause/Play/Skip Buttons
        var stopButton = new Button("[x]");
        var playButton = new Button(">");
        var skipButton = new Button(">>");
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
        scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(320);
        stage.setHeight(320);
        stage.setTitle("Pomeedoro");
        stage.show();
    }
}
