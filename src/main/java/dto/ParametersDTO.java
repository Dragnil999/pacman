package dto;

import java.io.Serializable;

public class ParametersDTO implements Serializable {
    private Double pacmanCordX;
    private Double pacmanCordY;
    private Double pacwomanCordX;
    private Double pacwomanCordY;
    private Double redGhostCordX;
    private Double redGhostCordY;
    private Double pinkGhostCordX;
    private Double pinkGhostCordY;
    private Double blueGhostCordX;
    private Double blueGhostCordY;
    private Double orangeGhostCordX;
    private Double orangeGhostCordY;
    private Integer pacmanScore;
    private Integer pacwomanScore;
    public ParametersDTO() {

    }

    public Double getPacmanCordX() {
        return pacmanCordX;
    }

    public void setPacmanCordX(Double pacmanCordX) {
        this.pacmanCordX = pacmanCordX;
    }

    public Double getPacmanCordY() {
        return pacmanCordY;
    }

    public void setPacmanCordY(Double pacmanCordY) {
        this.pacmanCordY = pacmanCordY;
    }

    public Double getPacwomanCordX() {
        return pacwomanCordX;
    }

    public void setPacwomanCordX(Double pacwomanCordX) {
        this.pacwomanCordX = pacwomanCordX;
    }

    public Double getPacwomanCordY() {
        return pacwomanCordY;
    }

    public void setPacwomanCordY(Double pacwomanCordY) {
        this.pacwomanCordY = pacwomanCordY;
    }

    public Double getRedGhostCordX() {
        return redGhostCordX;
    }

    public void setRedGhostCordX(Double redGhostCordX) {
        this.redGhostCordX = redGhostCordX;
    }

    public Double getRedGhostCordY() {
        return redGhostCordY;
    }

    public void setRedGhostCordY(Double redGhostCordY) {
        this.redGhostCordY = redGhostCordY;
    }

    public Double getPinkGhostCordX() {
        return pinkGhostCordX;
    }

    public void setPinkGhostCordX(Double pinkGhostCordX) {
        this.pinkGhostCordX = pinkGhostCordX;
    }

    public Double getPinkGhostCordY() {
        return pinkGhostCordY;
    }

    public void setPinkGhostCordY(Double pinkGhostCordY) {
        this.pinkGhostCordY = pinkGhostCordY;
    }

    public Double getBlueGhostCordX() {
        return blueGhostCordX;
    }

    public void setBlueGhostCordX(Double blueGhostCordX) {
        this.blueGhostCordX = blueGhostCordX;
    }

    public Double getBlueGhostCordY() {
        return blueGhostCordY;
    }

    public void setBlueGhostCordY(Double blueGhostCordY) {
        this.blueGhostCordY = blueGhostCordY;
    }

    public Double getOrangeGhostCordX() {
        return orangeGhostCordX;
    }

    public void setOrangeGhostCordX(Double orangeGhostCordX) {
        this.orangeGhostCordX = orangeGhostCordX;
    }

    public Double getOrangeGhostCordY() {
        return orangeGhostCordY;
    }

    public void setOrangeGhostCordY(Double orangeGhostCordY) {
        this.orangeGhostCordY = orangeGhostCordY;
    }

    public Integer getPacmanScore() {
        return pacmanScore;
    }

    public void setPacmanScore(Integer pacmanScore) {
        this.pacmanScore = pacmanScore;
    }

    public Integer getPacwomanScore() {
        return pacwomanScore;
    }

    public void setPacwomanScore(Integer pacwomanScore) {
        this.pacwomanScore = pacwomanScore;
    }
}
