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

    /**
     * Constructor for objects of class Item
     */
    public Item(String description, int weight)
    {
        this.itemWeight = weight;
        this.itemDescription = description;
    }

    public String getDescription(){
        String aDevolver = "";
        if(itemDescription != null){
            aDevolver = "Item: " + itemDescription + "\n"+ "Weight: " + itemWeight;
        }
        return aDevolver;
    }
} 