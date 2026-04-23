/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartCampusApplication.Database;

import SmartCampusApplication.model.Room;
import SmartCampusApplication.model.Sensor;
import SmartCampusApplication.model.SensorReading;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yasirusanjana
 */
public class MockDatabase {
    // ConcurrentHashMaps will be used to improve thread safety
    public static final Map<String, Room> rooms = new ConcurrentHashMap<>();
    public static final Map<String, Sensor> sensors = new ConcurrentHashMap<>();
    public static final Map<String, List<SensorReading>> readings = new ConcurrentHashMap<>();
    
    //since everyting is public, the data can be used without getters and setters
    
    // some sample data is added below to test the api without inserting data in every run
    static {
        //adding a room
        Room r1 = new Room("r1","Library",100);
        rooms.put(r1.getId(), r1);
        
        //adding a sensor
        Sensor s1 = new Sensor("TP1", "Temperature", "Active", 40.4, r1.getId());
        sensors.put(s1.getId(), s1);
        
        //add the senser to the room
        r1.getSensorIds().add(s1.getId());
        
        //Initialize the readings list to recode senser readings
        readings.put(s1.getId(), new ArrayList<>());
        
        List<SensorReading> sensorReadings = readings.get(s1.getId());
        sensorReadings.add(new SensorReading("Rec-01",System.currentTimeMillis(),27.0));
        sensorReadings.add(new SensorReading("Rec-02",System.currentTimeMillis(),36.0));
        
    
    }
}
