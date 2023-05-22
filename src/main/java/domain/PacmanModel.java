package domain;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import objects.Creature;
import objects.Ghost;
import objects.Pacman;

public class PacmanModel {
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
    private static void moveDirectionChooser(Ghost ghost) {
        if (ghost.getDirection() == Direction.LEFT) {
            ghost.getDirectionChooser().setLayoutX(ghost.getDirectionChooser().getLayoutX() - 1);
            ghost.getDirectionChooserHitbox().setLayoutX(ghost.getDirectionChooserHitbox().getLayoutX() - 1);
        }
        else if (ghost.getDirection() == Direction.UP) {
            ghost.getDirectionChooser().setLayoutY(ghost.getDirectionChooser().getLayoutY() + 1);
            ghost.getDirectionChooserHitbox().setLayoutY(ghost.getDirectionChooserHitbox().getLayoutY() + 1);
        }
        else if (ghost.getDirection() == Direction.RIGHT) {
            ghost.getDirectionChooser().setLayoutX(ghost.getDirectionChooser().getLayoutX() + 1);
            ghost.getDirectionChooserHitbox().setLayoutX(ghost.getDirectionChooserHitbox().getLayoutX() + 1);
        }
        else if (ghost.getDirection() == Direction.DOWN) {
            ghost.getDirectionChooser().setLayoutY(ghost.getDirectionChooser().getLayoutY() + 1);
            ghost.getDirectionChooserHitbox().setLayoutY(ghost.getDirectionChooserHitbox().getLayoutY() + 1);
        }
    }
    private static Direction chooseDirection(Ghost ghost, Pane wallsPane) {
        double x = ghost.getDirectionChooser().getLayoutX();
        double y = ghost.getDirectionChooser().getLayoutY();
        if (wallsPane.getChildren().stream().noneMatch(bounds -> bounds.getBoundsInParent().intersects(ghost.getDirectionChooser().getBoundsInParent()))) {
           return Direction.LEFT;
        }
        return Direction.DOWN;
    }
    public static Runnable movement(Creature creature, Pane cornersPane, Pane wallsPane, Pane dotsPane) {
        return new Runnable() {
            @Override
            public void run() {
                AnimationTimer timer = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        long cornerIntersections = cornersPane.getChildren().stream().filter(bounds -> bounds.getBoundsInParent().intersects(creature.getHitbox().getBoundsInParent())).count();
                        long wallIntersections = wallsPane.getChildren().stream().filter(bounds -> bounds.getBoundsInParent().intersects(creature.getImage().getBoundsInParent())).count();
                        if (creature instanceof Pacman) {
                            dotsPane.getChildren().stream().
                                    filter(Node::isVisible).
                                    filter(bounds -> bounds.getBoundsInParent().intersects(creature.getHitbox().getBoundsInParent())).
                                    forEach(obj -> {
                                        obj.setVisible(false);
                                    });
                        }
                        else if (creature instanceof Ghost) {
                            if (cornersPane.getChildren().stream().noneMatch(bounds -> bounds.getBoundsInParent().intersects(((Ghost) creature).getDirectionChooserHitbox().getBoundsInParent()))) {
                                moveDirectionChooser((Ghost) creature);
                            }
                            else {
                                creature.setPotentialDirection(chooseDirection((Ghost) creature, wallsPane));
                                ((Ghost) creature).getDirectionChooserHitbox().setLayoutX(creature.getHitbox().getLayoutX());
                                ((Ghost) creature).getDirectionChooserHitbox().setLayoutY(creature.getHitbox().getLayoutY() + 30);
                                ((Ghost) creature).getDirectionChooser().setLayoutX(((Ghost) creature).getDirectionChooserHitbox().getLayoutX() - 9);
                                ((Ghost) creature).getDirectionChooser().setLayoutY(((Ghost) creature).getDirectionChooserHitbox().getLayoutY() - 9);
                                System.out.println("bebra");
                            }
                        }
                        if (cornerIntersections > 0) {
                            creature.setDirection(creature.getPotentialDirection());
                        }
                        else if (wallIntersections > 0) {
                            pushFromWall(creature);
                            creature.setPotentialDirection(Direction.NONE);
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
