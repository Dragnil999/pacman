package controllers;

import domain.Direction;
import domain.PacmanModel;
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
    protected Pacman pacman;
    protected List<Ghost> ghostList;
    protected Ghost redGhost;
    protected Ghost pinkGhost;
    protected Ghost blueGhost;
    protected Ghost orangeGhost;

    protected void initialize() {
        PacmanModel.lifeCount = 1;
        pacman = new Pacman(184, 269, 175, 259, Direction.LEFT);
        playFieldPane.getChildren().addAll(pacman.getHitbox(), pacman.getImage());
        redGhost = new Ghost(86, 110, 75, 99, Direction.LEFT);
        /*redGhost = new Ghost(186, 189, 175, 180);*/
        pinkGhost = new Ghost(286, 110, 275, 99, Direction.DOWN);
        blueGhost = new Ghost(286, 310, 275, 299, Direction.RIGHT);
        orangeGhost = new Ghost(86, 310, 75, 299, Direction.UP);
        ghostList = new ArrayList<>(List.of(redGhost, pinkGhost, blueGhost, orangeGhost));
    }
}
