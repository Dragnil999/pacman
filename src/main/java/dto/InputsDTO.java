package dto;

import domain.Direction;

import java.io.Serializable;

public class InputsDTO implements Serializable {
    private Direction potentialDirection;
    private Double rotationAngle;
    private Integer score;
    public InputsDTO() {
        this.potentialDirection = Parameters.getPacmanPotentialDirection();
        this.rotationAngle = Parameters.getPacmanRotationAngle();
        this.score = Parameters.getPacmanScore();
    }

    public Direction getPotentialDirection() {
        return potentialDirection;
    }

    public void setPotentialDirection(Direction potentialDirection) {
        this.potentialDirection = potentialDirection;
    }

    public Double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(Double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
