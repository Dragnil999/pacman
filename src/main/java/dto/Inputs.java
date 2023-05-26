package dto;

import domain.Direction;

public class Inputs {
    private static Direction potentialDirection;

    public static Direction getPotentialDirection() {
        return potentialDirection;
    }

    public static void setPotentialDirection(Direction potentialDirection) {
        Inputs.potentialDirection = potentialDirection;
    }
}
