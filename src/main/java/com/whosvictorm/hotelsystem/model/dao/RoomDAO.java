package com.whosvictorm.hotelsystem.model.dao;

import com.whosvictorm.hotelsystem.db.DB;
import com.whosvictorm.hotelsystem.db.DbException;
import com.whosvictorm.hotelsystem.model.entities.Room;
import com.whosvictorm.hotelsystem.model.enums.RoomType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public static List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();

        Connection conn = DB.getConnection();

        try{
            String query = "SELECT * FROM rooms";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int id = resultSet.getInt("roomId");
                int roomNumber = resultSet.getInt("roomNumber");
                int roomFloor = resultSet.getInt("roomFloor");
                String roomType = resultSet.getString("roomType");
                String roomAvailable = resultSet.getString("roomAvailable");

                RoomType rType = RoomType.valueOf(roomType);

                Room room = new Room(id, roomNumber, roomFloor, rType, roomAvailable);
                rooms.add(room);
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return rooms;
    }

    public static Integer labelRooms(String string){
        Connection conn = DB.getConnection();

        int count = 0;

        try{
            String query = "SELECT count(*) FROM rooms WHERE roomAvailable = '" + string + "'";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return count;
    }

    public static Integer totalRooms(){
        Connection conn = DB.getConnection();

        int count = 0;

        try{
            String query = "SELECT count(*) FROM rooms";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return count;
    }


}
