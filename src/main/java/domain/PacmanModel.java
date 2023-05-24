package domain;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import objects.Creature;
import objects.Ghost;
import objects.Pacman;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class PacmanModel {
    public static boolean eatingMode = false;
    public static Integer lifeCount;
    public static boolean winner = false;
    private static Integer eatingTimeStart;
    public static String createPathToImage(String imgName){
        return String.format("%s\\src\\main\\resources\\images\\%s", new File("").getAbsolutePath(), imgName);
    }
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
    private static void setNormalGhostImages(Pane ghostsPane) {
        List<Node> ghostList = ghostsPane.getChildren().stream().filter(obj -> obj instanceof ImageView).toList();
        ((ImageView) ghostList.get(0)).setImage(new Image(createPathToImage("Red_Ghost.gif")));
        ((ImageView) ghostList.get(1)).setImage(new Image(createPathToImage("Pink_Ghost.gif")));
        ((ImageView) ghostList.get(2)).setImage(new Image(createPathToImage("Blue_Ghost.gif")));
        ((ImageView) ghostList.get(3)).setImage(new Image(createPathToImage("Orange_Ghost.gif")));
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
                            if ((creature.getDirection().ordinal() % 2 == 0 && creature.getPotentialDirection().ordinal() % 2 == 0) ||
                            creature.getDirection().ordinal() % 2 != 0 && creature.getPotentialDirection().ordinal() % 2 != 0) {
                                creature.setDirection(creature.getPotentialDirection());
                                creature.getImage().setRotate(((Pacman) creature).getRotationAngle());
                            }
                            dotsPane.getChildren().stream().
                                    filter(Node::isVisible).
                                    filter(bounds -> bounds.getBoundsInParent().intersects(creature.getHitbox().getBoundsInParent())).
                                    forEach(obj -> {
                                        obj.setVisible(false);
                                        if (obj.isCache()) {
                                            eatingMode = true;
                                            eatingTimeStart = (int) (new Date().getTime());
                                            ghostsPane.getChildren().stream().filter(ghost -> ghost instanceof ImageView).
                                                    forEach(ghost -> ((ImageView) ghost).
                                                            setImage(new Image(createPathToImage("Eadible_Ghost.gif"))));
                                        }
                                        ((Pacman) creature).setScore(((Pacman) creature).getScore() + 10);
                                    });
                            if (ghostsPane.getChildren().stream().
                                    anyMatch(hitbox -> hitbox.getBoundsInParent().intersects(creature.getHitbox().getBoundsInParent()))) {
                                if (eatingMode) {
                                    ghostsPane.getChildren().stream().
                                            filter(hitbox -> hitbox.getBoundsInParent().intersects(creature.getHitbox().getBoundsInParent())).
                                            forEach(obj -> obj.setVisible(false));
                                }
                                else {
                                    lifeCount--;
                                    ghostsPane.getChildren().stream().
                                            filter(Node::isVisible).
                                            forEach(obj -> obj.setVisible(true));
                                }
                            }
                        }
                        if (eatingMode && ((int) (new Date().getTime()) - eatingTimeStart) > 3000) {
                            eatingMode = false;
                            setNormalGhostImages(ghostsPane);
                            ghostsPane.getChildren().stream().filter(obj -> !obj.isVisible()).forEach(obj -> obj.setVisible(true));
                        }
                        if (cornersPane.getChildren().stream().anyMatch(bounds -> bounds.getBoundsInParent().intersects(creature.getHitbox().getBoundsInParent()))) {
                            creature.setDirection(creature.getPotentialDirection());
                            if (creature instanceof Pacman) {
                                creature.getImage().setRotate(((Pacman) creature).getRotationAngle());
                            }
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
