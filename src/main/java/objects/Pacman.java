package objects;

import domain.Direction;

public class Pacman extends Creature {
    private double rotationAngle;
    private Integer score;

    public Pacman(double x, double y, double x1, double y1, Direction direction, String path, double rotationAngle) {
        super(path, x, y, x1, y1);
        setPotentialDirection(direction);
        setDirection(direction);
        getImage().setFitWidth(22.75);
        getImage().setFitHeight(22.75);
        this.rotationAngle = rotationAngle;
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
