/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartCampusApplication.resource;

import static SmartCampusApplication.Database.MockDatabase.readings;
import static SmartCampusApplication.Database.MockDatabase.sensors;
import SmartCampusApplication.model.Sensor;
import SmartCampusApplication.model.SensorReading;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author yasirusanjana
 */

//The @path for this class is done by SensorResource class
public class SensorReadingResource {
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SensorReading> getAllReadings(@PathParam("sensorId") String sensorId) {
        // The below code can either send the correct info or Default to a empty list if id is not found
        // The empty list is sent to avoid sending nothing.
        return readings.getOrDefault(sensorId, new ArrayList<>());
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object addReading(@PathParam("sensorId") String sensorId, SensorReading newReading){
        
        //Make sure the ID is enterd
        if (newReading.getId() == null || newReading.getId().isEmpty()) {
            return "The Id is invalid or not Entered";     
        }
        
        //Make sure the time is enterd and add time if not
        if (newReading.getTimestamp() == 0) {
            newReading.setTimestamp(System.currentTimeMillis());
        }
        
        //If the senser doesn't contain recoding in database, add a new list
        if (readings.get(sensorId) == null) {
            readings.put(sensorId, new ArrayList<SensorReading>());
        }
        
        //Add the reading
        readings.get(sensorId).add(newReading);
        
        
        Sensor sensor = sensors.get(sensorId);
        if (sensor != null) {
            sensor.setCurrentValue(newReading.getValue());
        }
        
        return newReading;
    
    
    
    
    } 
    
    
}
