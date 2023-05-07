package controllers;

import domain.Boundaries;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayFieldController extends PlayFieldView implements Initializable {
    @FXML
    protected Pane pacmanPane;
    @FXML
    protected ImageView pacmanImage;
    private Direction direction;
    private Direction potentialDirection;
    private double imageRotation;

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
            imageRotation = 180;
            setPotentialDirection(Direction.LEFT);
        }
        else if (keyEvent.getCode() == KeyCode.W) {
            imageRotation = 270;
            setPotentialDirection(Direction.UP);
        }
        else if (keyEvent.getCode() == KeyCode.D) {
            imageRotation = 0;
            setPotentialDirection(Direction.RIGHT);
        }
        else if (keyEvent.getCode() == KeyCode.S) {
            imageRotation = 90;
            setPotentialDirection(Direction.DOWN);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize();
        setDirection(Direction.LEFT);
        setPotentialDirection(Direction.LEFT);

        Runnable pacmanMove = new Runnable() {
            @Override
            public void run() {
                AnimationTimer timer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        System.out.println(direction + " " + potentialDirection);
                        long cornerIntersections = corners.stream().filter(bounds -> bounds.getBoundsInParent().intersects(pacmanPane.getBoundsInParent())).count();
                        long wallIntersections = walls.stream().filter(bounds -> bounds.getBoundsInParent().intersects(pacmanImage.getBoundsInParent())).count();
                        if (cornerIntersections > 0) {
                            setDirection(potentialDirection);
                        }
                        else if (wallIntersections > 0) {
                            if (direction == Direction.LEFT) {  // pacmanPane.getBoundsInParent().intersects(topWall.getBoundsInParent())
                                pacmanPane.setLayoutX(pacmanPane.getLayoutX() + 2);
                                pacmanImage.setLayoutX(pacmanImage.getLayoutX() + 2);
                            }
                            else if (direction == Direction.UP) {
                                pacmanPane.setLayoutY(pacmanPane.getLayoutY() + 2);
                                pacmanImage.setLayoutY(pacmanImage.getLayoutY() + 2);
                            }
                            else if (direction == Direction.RIGHT) {
                                pacmanPane.setLayoutX(pacmanPane.getLayoutX() - 2);
                                pacmanImage.setLayoutX(pacmanImage.getLayoutX() - 2);
                            }
                            else if (direction == Direction.DOWN) {
                                pacmanPane.setLayoutY(pacmanPane.getLayoutY() - 2);
                                pacmanImage.setLayoutY(pacmanImage.getLayoutY() - 2);
                            }
                            setPotentialDirection(Direction.NONE);
                            setDirection(potentialDirection);
                        }
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
