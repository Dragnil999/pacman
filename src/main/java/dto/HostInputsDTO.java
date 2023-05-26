package dto;

import domain.Direction;

import java.io.Serializable;

public class HostInputsDTO implements Serializable {
    private Direction pacmanDirection;
    private Direction redGhostDirection;
    private Direction pinkGhostDirection;
    private Direction blueGhostDirection;
    private Direction orangeGhostDirection;
    private Double rotationAngle;
    private Integer score;
    public HostInputsDTO() {
        pacmanDirection = Parameters.getPacmanPotentialDirection();
        redGhostDirection = Parameters.getRedGhostPotentialDirection();
        pinkGhostDirection = Parameters.getPinkGhostPotentialDirection();
        blueGhostDirection = Parameters.getBlueGhostPotentialDirection();
        orangeGhostDirection = Parameters.getOrangeGhostPotentialDirection();
        rotationAngle = Parameters.getPacmanRotationAngle();
        score = Parameters.getPacmanScore();
    }
    public Direction getPacmanDirection() {
        return pacmanDirection;
    }

    public void setPacmanDirection(Direction pacmanDirection) {
        this.pacmanDirection = pacmanDirection;
    }

    public Direction getRedGhostDirection() {
        return redGhostDirection;
    }

    public void setRedGhostDirection(Direction redGhostDirection) {
        this.redGhostDirection = redGhostDirection;
    }

    public Direction getPinkGhostDirection() {
        return pinkGhostDirection;
    }

    public void setPinkGhostDirection(Direction pinkGhostDirection) {
        this.pinkGhostDirection = pinkGhostDirection;
    }

    public Direction getBlueGhostDirection() {
        return blueGhostDirection;
    }

    public void setBlueGhostDirection(Direction blueGhostDirection) {
        this.blueGhostDirection = blueGhostDirection;
    }

    public Direction getOrangeGhostDirection() {
        return orangeGhostDirection;
    }

    public void setOrangeGhostDirection(Direction orangeGhostDirection) {
        this.orangeGhostDirection = orangeGhostDirection;
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
