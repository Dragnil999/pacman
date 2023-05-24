package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlayFieldView {
    @FXML
    protected Pane wallsPane;
    @FXML
    protected Pane cornersPane;
    @FXML
    protected Pane dotsPane;
    protected Pane ghostsPane;
    @FXML
    protected AnchorPane playFieldPane;

    protected void initialize() {
        ghostsPane = new Pane();
        ghostsPane.setPrefSize(playFieldPane.getPrefWidth(), playFieldPane.getPrefHeight());
    }
}
