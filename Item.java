/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int itemWeight;
    private String itemDescription;
    private boolean pickable;
    private String id;
    /**
     * Constructor for objects of class Item
     */
    public Item(String description, String id, int weight, boolean pickable)
    {
        this.itemDescription = description;
        this.itemWeight = weight;
        this.pickable = pickable;
        this.id = id;
    }

    public String getDescription(){
        String aDevolver = "";
        if(itemDescription != null){
            aDevolver = "Item: " + itemDescription + "\n"+ "Weight: " + itemWeight + "\n" + id + "\n";
        }
        return aDevolver;
    }
    
    public int getWeight(){
        return itemWeight;
    }
    
    public boolean isPickable(){
        return pickable;
    }
    
    public String getId(){
        return id;
    }
} 