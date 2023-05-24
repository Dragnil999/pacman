package objects;

import domain.Direction;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Creature  {
    private Direction direction;
    private Direction potentialDirection;
    private Pane hitbox;
    private ImageView image;
    public Creature(String path, double x, double y, double x1, double y1) {
        this.image = new ImageView(new Image(path));
        this.image.setFitHeight(23);
        this.image.setFitWidth(23);
        this.image.setLayoutX(x1);
        this.image.setLayoutY(y1);
        hitbox = new Pane();
        hitbox.setLayoutX(x);
        hitbox.setLayoutY(y);
        hitbox.setPrefSize(1, 1);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getPotentialDirection() {
        return potentialDirection;
    }

    public void setPotentialDirection(Direction potentialDirection) {
        this.potentialDirection = potentialDirection;
    }
    public Pane getHitbox() {
        return hitbox;
    }

    public void setHitbox(Pane hitbox) {
        this.hitbox = hitbox;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
