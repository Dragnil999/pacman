package master;

import controllers.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.Console;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu-view.fxml"));
        Scene menuScene = new Scene(fxmlLoader.load(), 600, 480);
        stage.setTitle("Pacman");
        stage.setScene(menuScene);
        stage.setResizable(false);
        stage.setOnCloseRequest(windowEvent -> ((MenuController) fxmlLoader.getController()).exit());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}