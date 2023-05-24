package objects;

import domain.Direction;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import master.Main;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ghost extends Creature {
    public Ghost(double x, double y, double x1, double y1, Direction direction, String path) {
        super(path, x, y, x1, y1);
        setDirection(direction);
        setPotentialDirection(direction);
    }

}
