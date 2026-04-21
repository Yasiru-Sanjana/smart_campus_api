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
public class Room {
    private String id;              // Unique identifier , e.g.,"LIB -301"
    private String name;            // Human - readable name , e.g.," Library
    private int capacity;           // Maximum occupancy for safety
    private List<String> sensorIds = new ArrayList<>();     // Collection of IDs of sensors deployed in this room
    
    
    
}
