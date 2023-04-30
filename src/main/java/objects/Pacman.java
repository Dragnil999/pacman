package objects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import master.Main;

import java.util.Objects;

public class Pacman {
    private static int numberOfPacmans = 0;
    private Point2D pacmanLocation;
    private Point2D potentialPacmanLocation;
    private Point2D pacmanVelocity;
    private Image image;

    public Pacman(int x, int y) {
        numberOfPacmans++;
        image = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("Pacman_Left.png")));
        pacmanLocation = new Point2D(x, y);
        potentialPacmanLocation = pacmanLocation.add(pacmanVelocity);
    }

    public Point2D getPotentialPacmanLocation() {
        return potentialPacmanLocation;
    }

    public void setPotentialPacmanLocation(Point2D potentialPacmanLocation) {
        this.potentialPacmanLocation = potentialPacmanLocation;
    }

    public Point2D getPacmanLocation() {
        return pacmanLocation;
    }

    public void setPacmanLocation(Point2D pacmanLocation) {
        this.pacmanLocation = pacmanLocation;
    }

    public static int getNumberOfPacmans() {
        return numberOfPacmans;
    }

    public static void setNumberOfPacmans(int numberOfPacmans) {
        Pacman.numberOfPacmans = numberOfPacmans;
    }

    public Point2D getPacmanVelocity() {
        return pacmanVelocity;
    }

    public void setPacmanVelocity(Point2D pacmanVelocity) {
        this.pacmanVelocity = pacmanVelocity;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
