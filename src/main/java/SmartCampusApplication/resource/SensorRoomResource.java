/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartCampusApplication.resource;

import SmartCampusApplication.Database.GenericDAO;
import SmartCampusApplication.Database.MockDatabase;
import SmartCampusApplication.exception.RoomNotEmptyException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import SmartCampusApplication.model.Room;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

/**
 *
 * @author yasirusanjana
 */
@Path("/room")
public class SensorRoomResource {
    
    private GenericDAO<Room> roomDAO = new GenericDAO<>(MockDatabase.rooms);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms(){
        return roomDAO.getAll();
    }
    
    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    
    // @PathParam("roomId") will find the value in the url and save store it in String roomId.
    public Room getRoomById(@PathParam("roomId") String roomId)  {
        return roomDAO.getById(roomId);
        }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object addRoom(Room newRoom){
        
        // This condition will check if the data is missing 
        if (newRoom == null || newRoom.getId() == null || newRoom.getId().isEmpty()) {
            return "Invalid Input"; 
        }else{
            roomDAO.add(newRoom);
            // The room is returned back to confirm
            return newRoom;
        }  
    }
    
    @DELETE
    @Path("/{roomId}")
    public String deleteRoom(@PathParam("roomId") String roomId) {
        
        //Reading the database direcly to do checks
        Room room = MockDatabase.rooms.get(roomId);
        
        if (room == null) {
            return "Room not found.";
        }
        
        //Check if the room has active sensor and stop the delete request 
        if (!room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("This room cannot be deleted because it still contains sensors.");
        }
        
        //Sending a text to confirm the delete
        roomDAO.delete(roomId);
        return "Room " + roomId + " has been removed.";   
    }
     
    
    
    
    
}
