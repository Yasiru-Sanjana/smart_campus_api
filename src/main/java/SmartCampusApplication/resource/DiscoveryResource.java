/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartCampusApplication.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yasirusanjana
 */
@Path("/")          // This will use the same path as the SmartCampusApplication class
public class DiscoveryResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getDiscoveryInfo(){
        
        Map<String, Object> metadata = new HashMap<>();
        
        //versioning info
        metadata.put("version", "1.0");
        
        //administrative contact details
        metadata.put("contact", "yasiru.20231569@iit.ac.lk");
    
        // map of Primary Resource Collections
        // new map will be used for a better design
        
        Map<String, Object> collections = new HashMap<>();
        collections.put("rooms", "/api/v1/rooms");
        collections.put("sensors", "/api/v1/sensors");
        
        //put the new map inside the main map
        metadata.put("endpoints", collections);
        
        //return the map
        return metadata;
    }
    
}
