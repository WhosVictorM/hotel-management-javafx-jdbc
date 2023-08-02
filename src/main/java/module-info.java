module com.whosvictorm.hotelsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens com.whosvictorm.hotelsystem.controllers;
    opens com.whosvictorm.hotelsystem.applications;

}