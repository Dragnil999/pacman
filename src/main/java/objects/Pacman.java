package objects;

import domain.Direction;
import javafx.scene.image.Image;
import master.Main;

import java.net.URL;
import java.nio.file.Path;

public class Pacman extends Creature {
    private double rotationAngle;
    private Integer score;

    public Pacman(double x, double y, double x1, double y1, Direction direction, String path, double rotationAngle) {
        super(path, x, y, x1, y1);
        setPotentialDirection(direction);
        setDirection(direction);
        getImage().setRotate(rotationAngle);
        score = 0;
    }
    public void setDisabled() {
        getImage().setDisable(true);
        getHitbox().setDisable(true);
        getImage().setVisible(false);
        getHitbox().setVisible(false);
    }
    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
