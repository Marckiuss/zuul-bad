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

    /**
     * Constructor for objects of class Item
     */
    public Item(String description, int weight, boolean pickable)
    {
        this.itemDescription = description;
        this.itemWeight = weight;
        this.pickable = pickable;
    }

    public String getDescription(){
        String aDevolver = "";
        if(itemDescription != null){
            aDevolver = "Item: " + itemDescription + "\n"+ "Weight: " + itemWeight + "\n";
        }
        return aDevolver;
    }
    
    public int getWeight(){
        return itemWeight;
    }
    
    public boolean isPickable(){
        return pickable;
    }
} 