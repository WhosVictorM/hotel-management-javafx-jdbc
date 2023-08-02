package com.whosvictorm.hotelsystem.controllers;

import com.whosvictorm.hotelsystem.db.DB;
import com.whosvictorm.hotelsystem.db.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

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
                openNewTab();
                closeLoginTab();
            } else {
                lblErrorMessage.setText("Invalid login, try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openNewTab() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/MainView.fxml"));
            root = fxmlLoader.load();

            MainController mc = fxmlLoader.getController();
            mc.displayName(txtFieldUsername.getText());

            Stage newTab = new Stage();
            newTab.setScene(new Scene(root));
            newTab.setTitle("Hotel HUD");
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