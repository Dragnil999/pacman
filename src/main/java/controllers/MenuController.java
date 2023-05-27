package controllers;

import client.Client;
import domain.PlayerStat;
import dto.Parameters;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import javafx.stage.Window;
import master.Main;
import server.ClientHandler;
import server.Server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Label ipAddress;
    @FXML
    private TextField hostIPAddress;
    private void showWaitingStage() {
        Stage waitingStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("waiting-view.fxml"));
            waitingStage.setScene(new Scene(loader.load(), 250, 115));
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        waitingStage.setResizable(false);
        Parameters.connectedProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Platform.runLater(waitingStage::hide);
            }
        });
        Server server = new Server();
        server.start();
        waitingStage.setOnCloseRequest((windowEvent -> {Parameters.setCountOfPlayers(1);}));
        waitingStage.showAndWait();
    }
    @FXML
    private void startSingleplayer(ActionEvent event) {
        Parameters.setCountOfPlayers(1);
        Parameters.setStatus(PlayerStat.HOST);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("play-field-view.fxml"));
        Scene playScene = null;
        try {
            playScene = new Scene(loader.load(), 368, 487);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).setScene(playScene);
    }
    @FXML
    private void startAsHost(ActionEvent event) {
        Parameters.setCountOfPlayers(2);
        Parameters.setStatus(PlayerStat.HOST);
        showWaitingStage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("play-field-view.fxml"));
        Scene playScene = null;
        try {
            playScene = new Scene(loader.load(), 368, 487);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).setScene(playScene);
    }
    @FXML
    private void startAsGuest(ActionEvent event) {
        Parameters.setCountOfPlayers(2);
        Parameters.setStatus(PlayerStat.CLIENT);
        Client client = new Client();
        if (hostIPAddress.getText().matches("^(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")) {
            if (!client.connect(hostIPAddress.getText(), 3345)) {
                return;
            };
        }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("play-field-view.fxml"));
        Scene playScene = null;
        try {
            playScene = new Scene(loader.load(), 368, 487);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).setScene(playScene);
    }
    @FXML
    private void leaderboardView() {
        Stage winStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("win-screen-view.fxml"));
            winStage.setScene(new Scene(loader.load(), 368, 487));
            winStage.setResizable(false);
            winStage.setTitle("Leaderboard");
            winStage.showAndWait();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    @FXML
    public void exit() {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ipAddress.setText(inetAddress.getHostAddress());
            hostIPAddress.setText(inetAddress.getHostAddress());
        }
        catch (Exception exception) {
            ipAddress.setText("localhost");
            System.out.println(exception.getMessage());
        }
    }
}