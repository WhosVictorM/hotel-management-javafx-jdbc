package com.whosvictorm.hotelsystem.controllers;

import com.whosvictorm.hotelsystem.applications.StartApplication;
import com.whosvictorm.hotelsystem.db.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {


    private Stage stage;
    private Scene scene;

    @FXML
    TextField txtFieldUsername;

    @FXML
    PasswordField pwFieldPassword;

    @FXML
    Button btLogin;

    @FXML
    Label lblErrorMessage;

    public void onBtNewAction(ActionEvent event) throws IOException {

        if (!txtFieldUsername.getText().isBlank() && !pwFieldPassword.getText().isBlank()) {
            StartApplication.setUsername(txtFieldUsername.getText());
            validateLogin();
        } else {
            lblErrorMessage.setText("Please enter username and password");
        }
    }

    private void validateLogin() {
        DB connection = new DB();
        Connection conn = connection.getConnection();

        String verifyLogin = "SELECT * FROM useraccounts WHERE Username = '" + txtFieldUsername.getText() + "' AND Password = '" + pwFieldPassword.getText() + "'";
        try {
            Statement statement = conn.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            if (queryResult.next()) {
                openNewTab("/Fxml/MainView.fxml", "/Fxml/InitialMenu.fxml");
                closeLoginTab();
            } else {
                lblErrorMessage.setText("Invalid login, try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void openNewTab(String fxml1, String fxml2) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml1));
            Parent root = fxmlLoader.load();

            FXMLLoader newHBoxTab = new FXMLLoader(getClass().getResource(fxml2));
            HBox newHBox = newHBoxTab.load();

            Scene sc = new Scene(root);
            HBox mainHbox = ((HBox) ((ScrollPane) sc.getRoot()).getContent());

            Node mainMenu = mainHbox.getChildren().get(0);
            mainHbox.getChildren().clear();
            mainHbox.getChildren().add(mainMenu);
            mainHbox.getChildren().addAll(newHBox.getChildren());

            MainController mc = fxmlLoader.getController();
            mc.displayName(StartApplication.getUsername());

            Stage newTab = new Stage();
            newTab.setScene(sc);
            newTab.setTitle("Hotel HUD");
            newTab.setResizable(false);
            newTab.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeLoginTab() {
        Stage loginStage = (Stage) btLogin.getScene().getWindow();
        loginStage.close();
    }

}