/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartCampusApplication.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yasirusanjana
 */
public class Room implements BaseModel{
    private String id;              // Unique identifier , e.g.,"LIB -301"
    private String name;            // Human - readable name , e.g.," Library
    private int capacity;           // Maximum occupancy for safety
    private List<String> sensorIds = new ArrayList<>();     // Collection of IDs of sensors deployed in this room

    public Room(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }
    
    public Room() {
        this.id = "";
        this.name = "";
        this.capacity = 0;
        this.sensorIds = new ArrayList<>();
    }
    
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getSensorIds() {
        return sensorIds;
    }

    public void addSensorId(String sensorId) {
        this.sensorIds.add(sensorId);
    }
    
    
    
}
