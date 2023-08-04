package com.whosvictorm.hotelsystem.controllers;

import com.whosvictorm.hotelsystem.applications.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String name;

    @FXML
    Label lblName;

    @FXML
    Button btLogout;

    @FXML
    Button btHome;

    @FXML
    Button btReception;

    public void onBtHomeAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        Parent root = fxmlLoader.load();
        LoginController lc = fxmlLoader.getController();
        closeHudTab();
        lc.openNewTab("/Fxml/MainView.fxml","/Fxml/InitialMenu.fxml");
    }

    public void onBtReceptionAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        Parent root = fxmlLoader.load();
        LoginController lc = fxmlLoader.getController();
        closeHudTab();
        lc.openNewTab("/Fxml/MainView.fxml","/Fxml/Reception.fxml");
    }

    public void onBtLogoutAction(ActionEvent event){
        returnToLoginTab();
        closeHudTab();
    }

    private void returnToLoginTab() {
        try {

//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
//            root = fxmlLoader.load();
//
//            Stage newTab = new Stage();
//            newTab.setScene(new Scene(root));
//            newTab.setTitle("Login");
//            newTab.show();

            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("/Fxml/Login.fxml"));

            ScrollPane scrollPane = fxmlLoader.load();

            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);

            Stage primaryStage = new Stage();
            scene = new Scene(scrollPane);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void closeHudTab() {
        Stage logoutStage = (Stage) btLogout.getScene().getWindow();
        logoutStage.close();
    }


    public void displayName(String username){
        lblName.setText("Welcome, " + username);
    }

}
