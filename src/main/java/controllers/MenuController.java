package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import master.Main;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    private InetAddress inetAddress;
    @FXML
    private Label ipAddress;
    @FXML
    private void startSingleplayer(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("play-field-view.fxml"));
        Scene playScene = null;
        try {
            playScene = new Scene(loader.load(), 368, 487);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).setScene(playScene);
        /*Stage playStage = new Stage();
        playStage.setTitle("Pacman");
        playStage.setScene(playScene);
        playStage.setResizable(false);
        playStage.show();*/
    }
    @FXML
    private void startAsHost() {

    }
    @FXML
    private void startAsGuest() {

    }
    @FXML
    public void exit() {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            inetAddress = InetAddress.getLocalHost();
            ipAddress.setText(inetAddress.getHostAddress());
        }
        catch (Exception exception) {
            ipAddress.setText("localhost");
            System.out.println(exception.getMessage());
        }
    }
}