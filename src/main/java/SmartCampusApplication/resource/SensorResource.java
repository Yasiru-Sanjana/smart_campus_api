/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartCampusApplication.resource;

import SmartCampusApplication.Database.GenericDAO;
import SmartCampusApplication.Database.MockDatabase;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.ArrayList;
import SmartCampusApplication.model.Room;
import SmartCampusApplication.model.Sensor;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 *
 * @author yasirusanjana
 */

@Path("/sensors")   
public class SensorResource {
    
    private GenericDAO<Sensor> sensorDAO = new GenericDAO<>(MockDatabase.sensors);
    
        @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sensor> getSensors(@QueryParam("type") String type) {
        
    // get the full list to do checks
    List<Sensor> Sensors = sensorDAO.getAll();
    
    // If type is null, this will work as a normal GET
    if (type == null || type.isEmpty()) {
        return sensorDAO.getAll();
    }

    // If 'type' is not null, create a new list for the matches
    List<Sensor> filteredList = new ArrayList<>();
    
    for (Sensor s : Sensors) {
        // Compair using equalsIgnoreCase
        if (s.getType().equalsIgnoreCase(type)) {
            filteredList.add(s);
        }
    }
    // Return the list with the given type
    return filteredList;
}


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object addSensor(Sensor newSensor){
        
        // This condition will check if the data is missing 
        if (newSensor == null || newSensor.getId() == null || newSensor.getId().isEmpty()) {
            return "Invalid Input, required information might be missing"; 
        }
        
        //Reading the database direcly to do checks
        Room room = MockDatabase.rooms.get(newSensor.getRoomId());
        
        if (room == null){
            return "A room with the ID does not exist";
        }
        
        sensorDAO.add(newSensor);
        // Link the sensor to the room in the database
        room.addSensorId(newSensor.getId());
        // The sensor is returned back to confirm
        return newSensor;
        
    }
    
    @DELETE
    @Path("/{sensorId}")
    public String deleteRoom(@PathParam("sensorId") String sensorId) {
        
        //Reading the database direcly to do checks
        Sensor sensor = MockDatabase.sensors.get(sensorId);
        
        if (sensor == null) {
            return "Sensor not found.";
        }
     
        //Sending a text to confirm the delete
        sensorDAO.delete(sensorId);
        return "Room " + sensorId + " has been removed.";   
    }
    
    @Path("/{sensorId}/readings")
    public SensorReadingResource getReadingHistory(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource();
    }
    

}


