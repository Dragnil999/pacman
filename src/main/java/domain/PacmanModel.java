package domain;

import controllers.PlayFieldController;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import objects.Creature;
import objects.Ghost;
import objects.Pacman;

import java.util.Random;

public class PacmanModel {
    private static boolean eatingMode = true;
    public static Integer lifeCount;
    public static boolean winner = false;
    private static void pushFromWall(Creature creature) {
        if (creature.getDirection() == Direction.LEFT) {
            creature.getHitbox().setLayoutX(creature.getHitbox().getLayoutX() + 2);
            creature.getImage().setLayoutX(creature.getImage().getLayoutX() + 2);
        }
        else if (creature.getDirection() == Direction.UP) {
            creature.getHitbox().setLayoutY(creature.getHitbox().getLayoutY() + 2);
            creature.getImage().setLayoutY(creature.getImage().getLayoutY() + 2);
        }
        else if (creature.getDirection() == Direction.RIGHT) {
            creature.getHitbox().setLayoutX(creature.getHitbox().getLayoutX() - 2);
            creature.getImage().setLayoutX(creature.getImage().getLayoutX() - 2);
        }
        else if (creature.getDirection() == Direction.DOWN) {
            creature.getHitbox().setLayoutY(creature.getHitbox().getLayoutY() - 2);
            creature.getImage().setLayoutY(creature.getImage().getLayoutY() - 2);
        }
    }
    private static void move(Creature creature) {
        if (creature.getDirection() == Direction.LEFT) {
            creature.getHitbox().setLayoutX(creature.getHitbox().getLayoutX() - 1);
            creature.getImage().setLayoutX(creature.getImage().getLayoutX() - 1);
        }
        else if (creature.getDirection() == Direction.UP) {
            creature.getHitbox().setLayoutY(creature.getHitbox().getLayoutY() - 1);
            creature.getImage().setLayoutY(creature.getImage().getLayoutY() - 1);
        }
        else if (creature.getDirection() == Direction.RIGHT) {
            creature.getHitbox().setLayoutX(creature.getHitbox().getLayoutX() + 1);
            creature.getImage().setLayoutX(creature.getImage().getLayoutX() + 1);
        }
        else if (creature.getDirection() == Direction.DOWN) {
            creature.getHitbox().setLayoutY(creature.getHitbox().getLayoutY() + 1);
            creature.getImage().setLayoutY(creature.getImage().getLayoutY() + 1);
        }
    }
    private static Direction chooseDirection(Ghost ghost, Pane wallsPane) {
        double x = ghost.getImage().getLayoutX();
        double y = ghost.getImage().getLayoutY();
        int count = 0;
        Random random = new Random();
        int dir = random.nextInt(4);
        int i = -1;
        Direction direction = Direction.values()[dir];
        while (count < 4) {
            if (dir % 2 == 0) {
                ghost.getImage().setLayoutX(ghost.getImage().getLayoutX() + i);
            }
            else {
                ghost.getImage().setLayoutY(ghost.getImage().getLayoutY() + i);
            }
            if (wallsPane.getChildren().stream().noneMatch(bounds -> bounds.getBoundsInParent().intersects(ghost.getImage().getBoundsInParent()))) {
                ghost.getImage().setLayoutX(x);
                ghost.getImage().setLayoutY(y);
                break;
            }
            else {
                ghost.getImage().setLayoutX(x);
                ghost.getImage().setLayoutY(y);
                count++;
                dir = random.nextInt(4);
                if (dir == 0 || dir == 3) {
                    i = -2;
                }
                else {
                    i = 2;
                }
                direction = Direction.values()[dir];
            }
        }
        return direction;
    }
    public static Runnable movement(Creature creature, Pane cornersPane, Pane wallsPane, Pane dotsPane, Pane ghostsPane) {
        return new Runnable() {
            @Override
            public void run() {
                AnimationTimer timer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        if (lifeCount <= 0) {
                            ghostsPane.getChildren().clear();
                            stop();
                        }
                        if (dotsPane.getChildren().stream().noneMatch(Node::isVisible)) {
                            winner = true;
                            ghostsPane.getChildren().clear();
                            stop();
                        }
                        if (creature instanceof Pacman) {
                            dotsPane.getChildren().stream().
                                    filter(Node::isVisible).
                                    filter(bounds -> bounds.getBoundsInParent().intersects(creature.getHitbox().getBoundsInParent())).
                                    forEach(obj -> {
                                        obj.setVisible(false);
                                        ((Pacman) creature).setScore(((Pacman) creature).getScore() + 10);
                                    });
                            if (ghostsPane.getChildren().stream().
                                    anyMatch(hitbox -> hitbox.getBoundsInParent().intersects(creature.getHitbox().getBoundsInParent()))) {
                                lifeCount--;
                            }
                        }
                        if (cornersPane.getChildren().stream().anyMatch(bounds -> bounds.getBoundsInParent().intersects(creature.getHitbox().getBoundsInParent()))) {
                            creature.setDirection(creature.getPotentialDirection());
                        }
                        else if (wallsPane.getChildren().stream().anyMatch(bounds -> bounds.getBoundsInParent().intersects(creature.getImage().getBoundsInParent()))) {
                            pushFromWall(creature);
                            creature.setPotentialDirection(Direction.NONE);
                            if (creature instanceof Ghost) {
                                creature.setPotentialDirection(chooseDirection((Ghost) creature, wallsPane));
                            }
                            creature.setDirection(creature.getPotentialDirection());
                        }
                        move(creature);

                    }
                };
                timer.start();
            }
        };
    }
}
