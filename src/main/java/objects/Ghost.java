package objects;

import domain.Direction;

public class Ghost extends Creature {
    public Ghost(double x, double y, double x1, double y1, Direction direction, String path) {
        super(path, x, y, x1, y1);
        setDirection(direction);
        setPotentialDirection(direction);
    }

}
