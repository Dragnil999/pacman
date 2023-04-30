package domain;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Boundaries {
    private double rightBoundary;
    private double leftBoundary;
    private double topBoundary;
    private double bottomBoundary;
    public Boundaries(Pane node) {
        leftBoundary = node.getLayoutX();
        topBoundary = node.getLayoutY();
        rightBoundary = node.getLayoutX() + node.getPrefWidth();
        bottomBoundary = node.getLayoutY() + node.getPrefHeight();
    }
    public boolean intersection(Boundaries node) {
        if (node.getRightBoundary() > this.getLeftBoundary()) {
            return true;
        }
        return false;
    }
    public void updateBoundaries(Pane node) {
        leftBoundary = node.getLayoutX();
        topBoundary = node.getLayoutY();
        rightBoundary = node.getLayoutX() + node.getPrefWidth();
        bottomBoundary = node.getLayoutY() + node.getPrefHeight();
    }

    public double getRightBoundary() {
        return rightBoundary;
    }

    public void setRightBoundary(double rightBoundary) {
        this.rightBoundary = rightBoundary;
    }

    public double getLeftBoundary() {
        return leftBoundary;
    }

    public void setLeftBoundary(double leftBoundary) {
        this.leftBoundary = leftBoundary;
    }

    public double getTopBoundary() {
        return topBoundary;
    }

    public void setTopBoundary(double topBoundary) {
        this.topBoundary = topBoundary;
    }

    public double getBottomBoundary() {
        return bottomBoundary;
    }

    public void setBottomBoundary(double bottomBoundary) {
        this.bottomBoundary = bottomBoundary;
    }
}
