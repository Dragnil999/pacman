package dto;

import domain.Direction;
import domain.PlayerStat;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Parameters {
    private static int countOfPlayers;
    private static PlayerStat status;
    private static IntegerProperty connected = new SimpleIntegerProperty(0);
    private static Direction pacmanPotentialDirection;
    private static Direction pacwomanPotentialDirection;
    private static Direction redGhostPotentialDirection;
    private static Direction pinkGhostPotentialDirection;
    private static Direction blueGhostPotentialDirection;
    private static Direction orangeGhostPotentialDirection;
    private static Integer pacmanScore;
    private static Integer pacwomanScore;
    private static double pacmanRotationAngle;
    private static double pacwomanRotationAngle;

    public static int getCountOfPlayers() {
        return countOfPlayers;
    }

    public static Direction getPacmanPotentialDirection() {
        return pacmanPotentialDirection;
    }

    public static void setPacmanPotentialDirection(Direction pacmanPotentialDirection) {
        Parameters.pacmanPotentialDirection = pacmanPotentialDirection;
    }

    public static Direction getPacwomanPotentialDirection() {
        return pacwomanPotentialDirection;
    }

    public static void setPacwomanPotentialDirection(Direction pacwomanPotentialDirection) {
        Parameters.pacwomanPotentialDirection = pacwomanPotentialDirection;
    }
    public static void setCountOfPlayers(int countOfPlayers) {
        Parameters.countOfPlayers = countOfPlayers;
    }

    public static int getConnected() {
        return connected.get();
    }

    public static IntegerProperty connectedProperty() {
        return connected;
    }

    public static void setConnected(int connected) {
        Parameters.connected.set(connected);
    }

    public static PlayerStat getStatus() {
        return status;
    }

    public static void setStatus(PlayerStat status) {
        Parameters.status = status;
    }

    public static Direction getRedGhostPotentialDirection() {
        return redGhostPotentialDirection;
    }

    public static void setRedGhostPotentialDirection(Direction redGhostPotentialDirection) {
        Parameters.redGhostPotentialDirection = redGhostPotentialDirection;
    }

    public static Direction getPinkGhostPotentialDirection() {
        return pinkGhostPotentialDirection;
    }

    public static void setPinkGhostPotentialDirection(Direction pinkGhostPotentialDirection) {
        Parameters.pinkGhostPotentialDirection = pinkGhostPotentialDirection;
    }

    public static Direction getBlueGhostPotentialDirection() {
        return blueGhostPotentialDirection;
    }

    public static void setBlueGhostPotentialDirection(Direction blueGhostPotentialDirection) {
        Parameters.blueGhostPotentialDirection = blueGhostPotentialDirection;
    }

    public static Direction getOrangeGhostPotentialDirection() {
        return orangeGhostPotentialDirection;
    }

    public static void setOrangeGhostPotentialDirection(Direction orangeGhostPotentialDirection) {
        Parameters.orangeGhostPotentialDirection = orangeGhostPotentialDirection;
    }

    public static Integer getPacmanScore() {
        return pacmanScore;
    }

    public static void setPacmanScore(Integer pacmanScore) {
        Parameters.pacmanScore = pacmanScore;
    }

    public static Integer getPacwomanScore() {
        return pacwomanScore;
    }

    public static void setPacwomanScore(Integer pacwomanScore) {
        Parameters.pacwomanScore = pacwomanScore;
    }

    public static Double getPacmanRotationAngle() {
        return pacmanRotationAngle;
    }

    public static void setPacmanRotationAngle(Double pacmanRotationAngle) {
        Parameters.pacmanRotationAngle = pacmanRotationAngle;
    }

    public static Double getPacwomanRotationAngle() {
        return pacwomanRotationAngle;
    }

    public static void setPacwomanRotationAngle(Double pacwomanRotationAngle) {
        Parameters.pacwomanRotationAngle = pacwomanRotationAngle;
    }
}
