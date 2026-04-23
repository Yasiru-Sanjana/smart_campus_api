/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartCampusApplication.model;

/**
 *
 * @author yasirusanjana
 */
public class SensorReading implements BaseModel{
    private String id;          // Unique reading event ID ( UUIDrecommended )
    private long timestamp ;    // Epoch time (ms) when the reading was captured
    private double value ;      // The actual metric value recorded by the hardware

    public SensorReading(String id, long timestamp, double value) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
    }
    
    public SensorReading() {
        this.id = "";
        this.timestamp = 0;
        this.value = 0.0;
    }

    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    
}
