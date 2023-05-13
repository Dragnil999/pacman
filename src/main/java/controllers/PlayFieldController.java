package controllers;

import domain.Boundaries;
import domain.Direction;
import domain.PacmanModel;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import objects.Pacman;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayFieldController extends PlayFieldView implements Initializable {
    Pacman pacman;
    @FXML
    private void keyDispatcher(KeyEvent keyEvent) {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pacman = new Pacman(184, 269, 175, 259, Direction.LEFT);
        playFieldPane.getChildren().addAll(pacman.getHitbox(), pacman.getImage());
        Runnable pacmanMove = PacmanModel.movement(pacman, cornersPane, wallsPane, dotsPane);
        Thread pacmanAI = new Thread(pacmanMove);
        pacmanAI.start();
    }
}
