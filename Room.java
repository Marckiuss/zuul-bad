
import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private String itemDescription;
    private String itemWeight;
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String itemDescription, String itemWeight) 
    {
        this.description = description;
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
        exits = new HashMap<String, Room>();
    }

    public Room getExit(String direction){
        Room nextRoom = null;
        nextRoom = exits.get(direction);
        return nextRoom;
    }

    public String getExitsString(){
        String textoADevolver = exits.keySet().toString().substring(1, exits.keySet().toString().length()-1).replaceAll(",", "");
        return textoADevolver;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direction, Room location) 
    {
        if(direction.equals("north"))
            exits.put(direction, location);
        if(direction.equals("northEast"))
            exits.put(direction, location);
        if(direction.equals("northWest"))
            exits.put(direction, location);
        if(direction.equals("south"))
            exits.put(direction, location);
        if(direction.equals("southEast"))
            exits.put(direction, location);
        if(direction.equals("southWest"))
            exits.put(direction, location);
        if(direction.equals("east"))
            exits.put(direction, location);
        if(direction.equals("west"))
            exits.put(direction, location);

    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Devuelve un texto con la descripcion completa de la habitacion, que 
     * incluye la descripcion corta de la sala y las salidas de la misma. Por ejemplo:
     *     You are in the lab
     *     Exits: north west southwest
     * @return Una descripcion completa de la habitacion incluyendo sus salidas
     */
    public String getLongDescription(){
        String textoADevolver = "You are in " + getDescription() + "\n" + getItem() + "\n" + "Exits: " + getExitsString();
        return textoADevolver;
    }

    public String getItem(){
        String aDevolver = "";
        if(itemDescription != null){
            aDevolver = "There'something in the room!:  \n" + "item: " + itemDescription + "\n"+ "weight: " + itemWeight;
        }
        return aDevolver;
    }
}

