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
    protected Rectangle wall1;
    @FXML
    protected Rectangle wall2;
    @FXML
    protected Rectangle wall3;
    @FXML
    protected Rectangle wall4;
    @FXML
    protected Rectangle wall5;
    @FXML
    protected Rectangle wall6;
    @FXML
    protected Rectangle wall7;
    @FXML
    protected Rectangle wall8;
    @FXML
    protected Rectangle wall9;
    @FXML
    protected Rectangle wall10;
    @FXML
    protected Rectangle wall11;
    @FXML
    protected Rectangle wall12;
    @FXML
    protected Rectangle wall13;
    @FXML
    protected Rectangle wall14;
    @FXML
    protected Rectangle wall15;
    @FXML
    protected Rectangle wall16;
    @FXML
    protected Rectangle wall17;
    @FXML
    protected Rectangle wall18;
    @FXML
    protected Rectangle wall19;
    @FXML
    protected Rectangle wall20;

    @FXML
    protected Pane corner1;
    @FXML
    protected Pane corner2;
    @FXML
    protected Pane corner3;
    @FXML
    protected Pane corner4;
    @FXML
    protected Pane corner5;
    @FXML
    protected Pane corner6;
    @FXML
    protected Pane corner7;
    @FXML
    protected Pane corner8;
    @FXML
    protected Pane corner9;
    @FXML
    protected Pane corner10;
    @FXML
    protected Pane corner11;
    @FXML
    protected Pane corner12;
    @FXML
    protected Pane corner13;
    @FXML
    protected Pane corner14;
    @FXML
    protected Pane corner15;
    @FXML
    protected Pane corner16;
    @FXML
    protected Pane corner17;
    @FXML
    protected Pane corner18;
    @FXML
    protected Pane corner19;
    @FXML
    protected Pane corner20;
    @FXML
    protected Pane corner21;
    @FXML
    protected Pane corner22;
    @FXML
    protected Pane corner23;
    @FXML
    protected Pane corner24;
    @FXML
    protected Pane corner25;
    @FXML
    protected Pane corner26;
    @FXML
    protected Pane corner27;
    @FXML
    protected Pane wallsPane;
    @FXML
    protected Pane cornersPane;
    @FXML
    protected Pane dotsPane;
    @FXML
    protected AnchorPane playFieldPane;
    protected List<Rectangle> walls;
    protected List<Pane> corners;

    protected void initialize() {
        corners = new ArrayList<Pane>(List.of(corner1, corner2, corner3, corner4, corner5, corner6, corner7, corner8, corner9, corner10, corner11, corner12,
                corner13, corner14, corner15, corner16, corner17, corner18, corner19, corner20, corner21, corner22, corner23, corner24, corner25, corner26,
                corner27));

        walls = new ArrayList<Rectangle>(List.of(wall1, wall2, wall3, wall4, wall5, wall6, wall7, wall8, wall9, wall10,
                wall11, wall12, wall13, wall14, wall15, wall16, wall17, wall18, wall19, wall20));
    }
}
