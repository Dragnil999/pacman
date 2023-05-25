package controllers;

import domain.Direction;
import domain.PacmanModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import master.Main;
import objects.Ghost;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class PlayFieldController extends PlayFieldView implements Initializable {
    @FXML
    private void keyDispatcher(KeyEvent keyEvent) {
        if (PacmanModel.lifeCount <= 0) {
            pacman.setDisabled();
            deathScreen();
            initialize();
            startNewGame();
        }
        else if (PacmanModel.winner) {
            pacman.setDisabled();
            winScreen();
            PacmanModel.winner = false;
            initialize();
            dotsPane.getChildren().forEach(obj -> obj.setVisible(true));
            startNewGame();
        }

        if (keyEvent.getCode() == KeyCode.A) {
            pacman.setRotationAngle(180);
            pacman.setPotentialDirection(Direction.LEFT);
        }
        else if (keyEvent.getCode() == KeyCode.W) {
            pacman.setRotationAngle(270);
            pacman.setPotentialDirection(Direction.UP);
        }
        else if (keyEvent.getCode() == KeyCode.D) {
            pacman.setRotationAngle(0);
            pacman.setPotentialDirection(Direction.RIGHT);
        }
        else if (keyEvent.getCode() == KeyCode.S) {
            pacman.setRotationAngle(90);
            pacman.setPotentialDirection(Direction.DOWN);
        }
    }
    private void deathScreen() {
        Alert gameOverStage = new Alert(Alert.AlertType.INFORMATION);
        gameOverStage.setTitle("Game over");
        gameOverStage.setHeaderText("");
        gameOverStage.setContentText("You are dead. Try again");
        gameOverStage.setResizable(false);
        gameOverStage.showAndWait();
    }
    private void winScreen() {
        ScoreData.setScore(pacman.getScore());
        Stage winStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("win-screen-view.fxml"));
            winStage.setScene(new Scene(loader.load(), 368, 487));
            winStage.setResizable(false);
            winStage.setTitle("Won!!!");
            winStage.showAndWait();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    private void startNewGame() {
        Runnable pacmanMove = PacmanModel.movement(pacman, cornersPane, wallsPane, dotsPane, ghostsPane);
        Thread pacmanAI = new Thread(pacmanMove);
        pacmanAI.start();

        Timer timer = new Timer();
        for (Ghost ghost : ghostList) {
            timer.schedule(activateTheGhost(ghost), 0);
        }
        Timer scoreTimer = new Timer();
        scoreTimer.scheduleAtFixedRate(changeScore(), 0, 50);
    }
    private TimerTask activateTheGhost(Ghost ghost) {
        return new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    playFieldPane.getChildren().addAll(ghost.getHitbox());
                    ghostsPane.getChildren().addAll(ghost.getHitbox(), ghost.getImage());

                    Runnable ghostMove = PacmanModel.movement(ghost, cornersPane, wallsPane, dotsPane, ghostsPane);
                    Thread ghostAI = new Thread(ghostMove);
                    ghostAI.start();
                });
            }
        };
    }
    private TimerTask changeScore() {
        return new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    scoreLabel.setText(pacman.getScore().toString());
                });
            }
        };
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initialize();
            startNewGame();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
