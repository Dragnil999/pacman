package controllers;

import domain.Direction;
import domain.PacmanModel;
import domain.PlayerStat;
import dto.Parameters;
import javafx.animation.AnimationTimer;
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
            Parameters.setPacmanPotentialDirection(Direction.LEFT);
            Parameters.setPacmanRotationAngle(180.0);
        }
        else if (keyEvent.getCode() == KeyCode.W) {
            pacman.setRotationAngle(270);
            pacman.setPotentialDirection(Direction.UP);
            Parameters.setPacmanPotentialDirection(Direction.UP);
            Parameters.setPacmanRotationAngle(270.0);
        }
        else if (keyEvent.getCode() == KeyCode.D) {
            pacman.setRotationAngle(0);
            pacman.setPotentialDirection(Direction.RIGHT);
            Parameters.setPacmanPotentialDirection(Direction.RIGHT);
            Parameters.setPacmanRotationAngle(0.0);
        }
        else if (keyEvent.getCode() == KeyCode.S) {
            pacman.setRotationAngle(90);
            pacman.setPotentialDirection(Direction.DOWN);
            Parameters.setPacmanPotentialDirection(Direction.DOWN);
            Parameters.setPacmanRotationAngle(90.0);
        }
    }
    private void deathScreen() {
        Alert gameOverStage = new Alert(Alert.AlertType.INFORMATION);
        gameOverStage.setTitle("Game over");
        gameOverStage.setHeaderText("");
        gameOverStage.setContentText("You are dead. Try again");
        gameOverStage.setResizable(false);
        gameOverStage.showAndWait();
        if (Parameters.getCountOfPlayers() == 2) {
            System.exit(0);
        }
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

        if (Parameters.getCountOfPlayers() == 2) {
            Runnable pacwomanMove = PacmanModel.movement(pacwoman, cornersPane, wallsPane, dotsPane, ghostsPane);
            Thread pacwomanAI = new Thread(pacwomanMove);
            pacwomanAI.start();
        }

        Timer timer = new Timer();
        for (Ghost ghost : ghostList) {
            timer.schedule(activateTheGhost(ghost), 0);
        }
        Timer scoreTimer = new Timer();
        scoreTimer.scheduleAtFixedRate(changeScore(), 0, 50);
        if (Parameters.getCountOfPlayers() == 2) {
            updateDirections();
        }
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
                    if (Parameters.getCountOfPlayers() == 2) {
                        if (Parameters.getStatus() == PlayerStat.HOST) {
                            scoreLabel.setText(pacman.getScore().toString());
                            scoreLabel1.setText(pacwoman.getScore().toString());
                        }
                        else {
                            scoreLabel.setText(pacwoman.getScore().toString());
                            scoreLabel1.setText(pacman.getScore().toString());
                        }
                    }
                    else {
                        scoreLabel.setText(pacman.getScore().toString());
                    }
                });
            }
        };
    }
    private void updateDirections() {
        AnimationTimer update = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Platform.runLater(() -> {
                    pacwoman.setPotentialDirection(Parameters.getPacwomanPotentialDirection());
                    pacwoman.setRotationAngle(Parameters.getPacwomanRotationAngle());
                    if (Parameters.getStatus() == PlayerStat.HOST) {
                        Parameters.setRedGhostPotentialDirection(redGhost.getPotentialDirection());
                        Parameters.setPinkGhostPotentialDirection(pinkGhost.getPotentialDirection());
                        Parameters.setBlueGhostPotentialDirection(blueGhost.getPotentialDirection());
                        Parameters.setOrangeGhostPotentialDirection(orangeGhost.getPotentialDirection());
                    }
                    else if (Parameters.getStatus() == PlayerStat.CLIENT) {
                        redGhost.setPotentialDirection(Parameters.getRedGhostPotentialDirection());
                        pinkGhost.setPotentialDirection(Parameters.getPinkGhostPotentialDirection());
                        blueGhost.setPotentialDirection(Parameters.getBlueGhostPotentialDirection());
                        orangeGhost.setPotentialDirection(Parameters.getOrangeGhostPotentialDirection());
                    }
                });
            }
        };
        update.start();
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
