package controllers;

import domain.Direction;
import domain.PacmanModel;
import domain.PlayerStat;
import dto.Parameters;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import objects.Ghost;
import objects.Pacman;
import java.util.ArrayList;
import java.util.List;

public class PlayFieldView {
    @FXML
    protected Pane wallsPane;
    @FXML
    protected Pane cornersPane;
    @FXML
    protected Pane dotsPane;
    @FXML
    protected Pane ghostsPane;
    @FXML
    protected AnchorPane playFieldPane;
    @FXML
    protected Label scoreLabel;
    @FXML
    protected Label scoreLabel1;
    protected Pacman pacman;
    protected Pacman pacwoman;
    protected List<Ghost> ghostList;
    protected Ghost redGhost;
    protected Ghost pinkGhost;
    protected Ghost blueGhost;
    protected Ghost orangeGhost;

    protected void initialize() {
        PacmanModel.lifeCount = 1;
        pacman = new Pacman(184, 269, 175, 259, Direction.LEFT, PacmanModel.createPathToImage("Pacman_Yellow.gif"), 180.0);
        playFieldPane.getChildren().addAll(pacman.getHitbox(), pacman.getImage());
        if (Parameters.getCountOfPlayers() == 2) {
            PacmanModel.lifeCount = 60;
            scoreLabel1.setVisible(true);
            if (Parameters.getStatus() == PlayerStat.HOST) {
                pacwoman = new Pacman(214, 269, 205, 259, Direction.RIGHT, PacmanModel.createPathToImage("Pacman_Yellow.gif"), 0.0);
                Parameters.setPacmanPotentialDirection(Direction.LEFT);
                Parameters.setPacwomanPotentialDirection(Direction.RIGHT);
                Parameters.setPacmanRotationAngle(180.0);
                Parameters.setPacwomanRotationAngle(0.0);
                playFieldPane.getChildren().addAll(pacwoman.getHitbox(), pacwoman.getImage());
            }
            else {
                pacwoman = pacman;
                pacman = new Pacman(214, 269, 205, 259, Direction.RIGHT, PacmanModel.createPathToImage("Pacman_Yellow.gif"), 0.0);
                Parameters.setPacmanPotentialDirection(Direction.RIGHT);
                Parameters.setPacwomanPotentialDirection(Direction.LEFT);
                Parameters.setPacmanRotationAngle(0.0);
                Parameters.setPacwomanRotationAngle(180.0);
                playFieldPane.getChildren().addAll(pacman.getHitbox(), pacman.getImage());
            }
        }
        redGhost = new Ghost(86, 110, 75, 99, Direction.LEFT, PacmanModel.createPathToImage("Red_Ghost.gif"));
        pinkGhost = new Ghost(285, 110, 274, 99, Direction.DOWN, PacmanModel.createPathToImage("Pink_Ghost.gif"));
        blueGhost = new Ghost(285, 309, 274, 299, Direction.RIGHT, PacmanModel.createPathToImage("Blue_Ghost.gif"));
        orangeGhost = new Ghost(86, 310, 75, 299, Direction.UP, PacmanModel.createPathToImage("Orange_Ghost.gif"));
        ghostList = new ArrayList<>(List.of(redGhost, pinkGhost, blueGhost, orangeGhost));
    }
}
