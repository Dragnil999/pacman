module master {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens master to javafx.fxml;
    exports master;
    exports controllers;
    opens controllers to javafx.fxml;
}