package com.whosvictorm.hotelsystem.controllers;

import com.whosvictorm.hotelsystem.applications.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label lblName;

    @FXML
    Button btLogout;

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

    private synchronized <T> void loadView(String absoluteName, Consumer<T> initialazingAction){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = fxmlLoader.load();

            Scene scene = StartApplication.getScene();
            HBox mainHBox = (HBox) ((ScrollPane) scene.getRoot()).getContent();

            Node mainMenu = mainHBox.getChildren().get(0);
            mainHBox.getChildren().clear();
            mainHBox.getChildren().add(mainMenu);
            mainHBox.getChildren().addAll(newVBox.getChildren());

            T controller = fxmlLoader.getController();
            initialazingAction.accept(controller);

        } catch (IOException e){
            com.example.javafxjdbc.model.util.Alerts.showAlert("IO Exception", "Error Loading View", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void displayName(String username){
        lblName.setText("Welcome, " + username);
    }
}
