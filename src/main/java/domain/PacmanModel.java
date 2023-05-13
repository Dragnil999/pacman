package domain;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import objects.Creature;
import objects.Pacman;

public class PacmanModel {
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
                            dotsPane.getChildren().stream().filter(obj -> obj instanceof Pane).filter(bounds -> bounds.getBoundsInParent().intersects(creature.getHitbox().getBoundsInParent())).forEach(obj -> obj.setVisible(false));
                        }
                        if (cornerIntersections > 0) {
                            creature.setDirection(creature.getPotentialDirection());
                        }
                        else if (wallIntersections > 0) {
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
                            creature.setPotentialDirection(Direction.NONE);
                            creature.setDirection(creature.getPotentialDirection());
                        }
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
                };
                timer.start();
            }
        };
    }
}
