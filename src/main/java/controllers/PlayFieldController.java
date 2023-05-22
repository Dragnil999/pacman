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
import objects.Ghost;
import objects.Pacman;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayFieldController extends PlayFieldView implements Initializable {
    Pacman pacman;
    Ghost redGhost;
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
        try {
            pacman = new Pacman(184, 269, 175, 259, Direction.LEFT);
            playFieldPane.getChildren().addAll(pacman.getHitbox(), pacman.getImage());
            redGhost = new Ghost(186, 189, 175, 180);
            playFieldPane.getChildren().addAll(redGhost.getHitbox(), redGhost.getImage(), redGhost.getDirectionChooser(), redGhost.getDirectionChooserHitbox());

            Runnable pacmanMove = PacmanModel.movement(pacman, cornersPane, wallsPane, dotsPane);
            Thread pacmanAI = new Thread(pacmanMove);
            pacmanAI.start();

            Runnable redGhostMove = PacmanModel.movement(redGhost, cornersPane, wallsPane, dotsPane);
            Thread redGhostAI = new Thread(redGhostMove);
            redGhostAI.start();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
