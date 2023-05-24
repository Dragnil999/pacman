package objects;

import domain.Direction;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Ghost extends Creature {
    public Ghost(double x, double y, double x1, double y1, Direction direction) {
        super("C:\\Users\\danil\\IdeaProjects\\pacman\\src\\main\\resources\\images\\Red_Ghost.png", x, y, x1, y1);
        setDirection(direction);
        setPotentialDirection(direction);
    }

}
