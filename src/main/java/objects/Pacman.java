package objects;

import domain.Direction;
import javafx.scene.image.Image;
import master.Main;

public class Pacman extends Creature {
    private double rotationAngle;

    public Pacman(double x, double y, double x1, double y1, Direction direction) {
        super("C:\\Users\\danil\\IdeaProjects\\pacman\\src\\main\\resources\\images\\Pacman_yellow.png", x, y, x1, y1);
        setPotentialDirection(direction);
        setDirection(direction);
    }
    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
}
