package controllers;

import domain.Boundaries;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlayFieldController implements Initializable {
    @FXML
    private Pane pacmanPane;
    @FXML
    private ImageView pacmanImage;
    @FXML
    private HBox wall1;
    @FXML
    private HBox wall2;
    @FXML
    private HBox wall3;
    @FXML
    private HBox wall4;
    @FXML
    private HBox wall5;
    @FXML
    private Rectangle wall6;
    @FXML
    private Pane corner1;
    @FXML
    private Pane corner2;
    @FXML
    private Pane corner3;
    private Direction direction;
    private Direction potentialDirection;

    public void setPotentialDirection(Direction potentialDirection) {
        this.potentialDirection = potentialDirection;
    }

    Boundaries pacmanBoundary;
    private enum Direction {
        LEFT, RIGHT, UP, DOWN, NONE
    }
    private void setDirection(Direction dir) {
        direction = dir;
    }
    @FXML
    private void keyDispatcher(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.A) {
            pacmanImage.setRotate(180);
            setPotentialDirection(Direction.LEFT);
        }
        else if (keyEvent.getCode() == KeyCode.W) {
            pacmanImage.setRotate(270);
            setPotentialDirection(Direction.UP);
        }
        else if (keyEvent.getCode() == KeyCode.D) {
            pacmanImage.setRotate(0);
            setPotentialDirection(Direction.RIGHT);
        }
        else if (keyEvent.getCode() == KeyCode.S) {
            pacmanImage.setRotate(90);
            setPotentialDirection(Direction.DOWN);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setPotentialDirection(Direction.LEFT);
        setDirection(Direction.LEFT);
        setPotentialDirection(Direction.LEFT);
        Runnable pacmanMove = new Runnable() {
            @Override
            public void run() {
                List<Pane> corners = new ArrayList<>();
                corners.add(corner1);
                corners.add(corner2);
                corners.add(corner3);

                AnimationTimer timer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        System.out.println(direction + " " + potentialDirection);
                        long interseptions = corners.stream().filter(bounds -> bounds.getBoundsInParent().intersects(pacmanPane.getBoundsInParent())).count();
                        if (interseptions > 0) {
                            if (potentialDirection != direction) {
                                setDirection(potentialDirection);
                            }
                            else {
                                setDirection(Direction.NONE);
                                setPotentialDirection(Direction.NONE);
                            }
                        }
                        /*if (interseptions < 0) { // pacmanPane.getBoundsInParent().intersects(leftWall.getBoundsInParent()) ||
                            if (direction == Direction.LEFT) {  // pacmanPane.getBoundsInParent().intersects(topWall.getBoundsInParent())
                                pacmanPane.setLayoutX(pacmanPane.getLayoutX() + 2);
                                pacmanImage.setLayoutX(pacmanImage.getLayoutX() + 2);
                            }
                            else if (direction == Direction.UP) {
                                pacmanPane.setLayoutY(pacmanPane.getLayoutY() + 2);
                                pacmanImage.setLayoutY(pacmanImage.getLayoutX() + 2);
                            }
                            else if (direction == Direction.RIGHT) {
                                pacmanPane.setLayoutX(pacmanPane.getLayoutX() - 2);
                                pacmanImage.setLayoutX(pacmanImage.getLayoutX() - 2);
                            }
                            else if (direction == Direction.DOWN) {
                                pacmanPane.setLayoutY(pacmanPane.getLayoutY() - 2);
                                pacmanImage.setLayoutY(pacmanImage.getLayoutX() - 2);
                            }
                            setPotentialDirection(Direction.NONE);
                        }*/
                        if (direction == Direction.LEFT) {
                            pacmanPane.setLayoutX(pacmanPane.getLayoutX() - 1);
                            pacmanImage.setLayoutX(pacmanImage.getLayoutX() - 1);
                        }
                        else if (direction == Direction.UP) {
                            pacmanPane.setLayoutY(pacmanPane.getLayoutY() - 1);
                            pacmanImage.setLayoutY(pacmanImage.getLayoutY() - 1);
                        }
                        else if (direction == Direction.RIGHT) {
                            pacmanPane.setLayoutX(pacmanPane.getLayoutX() + 1);
                            pacmanImage.setLayoutX(pacmanImage.getLayoutX() + 1);
                        }
                        else if (direction == Direction.DOWN) {
                            pacmanPane.setLayoutY(pacmanPane.getLayoutY() + 1);
                            pacmanImage.setLayoutY(pacmanImage.getLayoutY() + 1);
                        }
                    }
                };
                timer.start();
            }
        };
        Thread pacmanAI = new Thread(pacmanMove);

        pacmanAI.start();
    }
}
