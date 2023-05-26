package controllers;

import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WinScreenController implements Initializable {
    @FXML
    private VBox leaderboard;
    @FXML
    private TextField nameField;
    @FXML
    private Button saveButton;

    @FXML
    private void saveResult() {
        Database.addToDB(nameField.getText(), ScoreData.getScore());
        saveButton.setDisable(true);
        showResults();
    }
    @FXML
    private void backToGame(ActionEvent event) {
        Database.closeDB();
        ((Stage)((Node) event.getSource()).getScene().getWindow()).hide();
    }
    private void showResults() {
        List<String> results = Database.readDB();
        leaderboard.getChildren().clear();
        for (String score : results) {
            leaderboard.getChildren().add(new Label(score));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Database.connection("192.168.0.14", "5432");
        if (ScoreData.getScore() == -1) {
            saveButton.setDisable(true);
        }
        showResults();
    }
}
