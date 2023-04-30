module master {
    requires javafx.controls;
    requires javafx.fxml;


    opens master to javafx.fxml;
    exports master;
    exports controllers;
    opens controllers to javafx.fxml;
}