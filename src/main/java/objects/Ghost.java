package objects;

import domain.Direction;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Ghost extends Creature {
    private double prefLocationX;
    private double prefLocationY;
    private Rectangle directionChooser;
    private Pane directionChooserHitbox;
    public Ghost(double x, double y, double x1, double y1) {
        super("C:\\Users\\danil\\IdeaProjects\\pacman\\src\\main\\resources\\images\\Red_Ghost.png", x, y, x1, y1);
        setDirection(Direction.LEFT);
        setPotentialDirection(Direction.LEFT);
        /*getImage().setFitHeight(23);
        getImage().setFitWidth(23);*/
        directionChooser = new Rectangle();
        directionChooserHitbox = new Pane();
        directionChooserHitbox.setLayoutX(x - 30.0);
        directionChooserHitbox.setLayoutY(y);
        directionChooser.setLayoutX(directionChooserHitbox.getLayoutX() - 11);
        directionChooser.setLayoutY(directionChooserHitbox.getLayoutY() - 9);
        directionChooser.setWidth(20);
        directionChooser.setHeight(20);
        directionChooser.setFill(Paint.valueOf("Dodgerblue"));
        //directionChooser.setPrefSize(x1, y1);
    }
    /*private Direction getPrefDirection() {

    }*/

    public double getPrefLocationX() {
        return prefLocationX;
    }

    public void setPrefLocationX(double prefLocationX) {
        this.prefLocationX = prefLocationX;
    }

    public double getPrefLocationY() {
        return prefLocationY;
    }

    public void setPrefLocationY(double prefLocationY) {
        this.prefLocationY = prefLocationY;
    }

    public Rectangle getDirectionChooser() {
        return directionChooser;
    }

    public void setDirectionChooser(Rectangle directionChooser) {
        this.directionChooser = directionChooser;
    }

    public Pane getDirectionChooserHitbox() {
        return directionChooserHitbox;
    }

    public void setDirectionChooserHitbox(Pane directionChooserHitbox) {
        this.directionChooserHitbox = directionChooserHitbox;
    }
}
