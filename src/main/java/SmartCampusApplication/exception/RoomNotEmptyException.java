/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartCampusApplication.exception;

/**
 *
 * @author yasirusanjana
 */
public class RoomNotEmptyException extends RuntimeException{
    public RoomNotEmptyException(String message) {
        super(message); // Passes the message up to the parent class
    }
}
    

