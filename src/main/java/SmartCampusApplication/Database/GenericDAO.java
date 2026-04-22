/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SmartCampusApplication.Database;
import SmartCampusApplication.model.BaseModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yasirusanjana
 */
public class GenericDAO <T extends BaseModel>{
    
    private final Map<String, T> items;

    public GenericDAO(Map<String, T> items) {
        this.items = items;
    }

    public List<T> getAll() {
        return new ArrayList<>(items.values());
    }
    
    public T getById(String id) {
        return items.get(id);
    }
    
    public void add(T item) {
        items.put(item.getId(), item); 
    }
    
    public void delete(String id) {
        items.remove(id);
    }
    
    
}
