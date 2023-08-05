package com.whosvictorm.hotelsystem.model.entities;

import com.whosvictorm.hotelsystem.model.enums.RoomType;

import java.util.Objects;

public class Room {

    private Integer roomId;
    private Integer roomNumber;
    private Integer roomFloor;
    private RoomType roomType;
    private String roomAvailable;

    public Room(){
    }

    public Room(Integer roomId, Integer roomNumber, Integer roomFloor, RoomType roomType, String roomAvailable) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomFloor = roomFloor;
        this.roomType = roomType;
        this.roomAvailable = roomAvailable;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(Integer roomFloor) {
        this.roomFloor = roomFloor;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getRoomAvailable() {
        return roomAvailable;
    }

    public void setRoomAvailable(String roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId, room.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }

    public String transformEnumm(RoomType type){
        String transform = roomType.name();
        return transform;
    }
}
