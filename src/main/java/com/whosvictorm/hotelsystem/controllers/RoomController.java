package com.whosvictorm.hotelsystem.controllers;

import com.whosvictorm.hotelsystem.model.dao.RoomDAO;
import com.whosvictorm.hotelsystem.model.entities.Room;
import com.whosvictorm.hotelsystem.model.enums.RoomType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomController implements Initializable {


    @FXML
    TableView<Room> roomTableView;

    @FXML
    TableColumn<Room, Integer> idColumn;

    @FXML
    TableColumn<Room, Integer> numberColumn;

    @FXML
    TableColumn<Room, Integer> floorColumn;

    @FXML
    TableColumn<Room, RoomType> typeColumn;

    @FXML
    TableColumn<Room, String> availableColumn;

    @FXML
    Label lblTotalRooms;

    @FXML
    Label lblRoomsAvailable;

    @FXML
    Label lblRoomsToClear;

    @FXML
    Label lblBookedRooms;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRoomId()).asObject());
        numberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRoomNumber()).asObject());
        floorColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRoomFloor()).asObject());
        typeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRoomType()));
        availableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoomAvailable()));

        List<Room> rooms = RoomDAO.getRooms();

        roomTableView.getItems().addAll(rooms);
        setLabelText();
    }

    public void setLabelText(){
        int available = RoomDAO.labelRooms("Available");
        lblRoomsAvailable.setText(String.valueOf(available));

        int total = RoomDAO.totalRooms();
        lblTotalRooms.setText(String.valueOf(total));

        int clear = RoomDAO.labelRooms("Cleaning");
        lblRoomsToClear.setText(String.valueOf(clear));

        int booked = RoomDAO.labelRooms("Booked");
        lblBookedRooms.setText(String.valueOf(booked));

    }
}
