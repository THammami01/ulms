module ULMS {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
	requires sqlite.jdbc;
    exports main.models;
	opens main;
}
