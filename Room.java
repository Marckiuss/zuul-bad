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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }
    
    public Room getExit(String direction){
        Room nextRoom = null;
        if(direction.equals("north")){
            nextRoom = northExit;
        }
        if(direction.equals("south")){
            nextRoom = southExit;
        }
        if(direction.equals("east")){
            nextRoom = eastExit;
        }
        if(direction.equals("west")){
            nextRoom = westExit;
        }
        if(direction.equals("southEast")){
            nextRoom = southEastExit;
        }
        return nextRoom;
    }
    
    public String getExitString(){
        String exit = "";
        if(northExit != null)
            exit += northExit;
        if(eastExit != null)
            exit += eastExit;
        if(southExit != null)
            exit += southExit;
        if(westExit != null)
            exit += westExit;
        if(southEastExit != null)
            exit += southEastExit;
        return exit;
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
            northExit = location;
        if(direction.equals("south"))
            southExit = location;
        if(direction.equals("east"))
            eastExit = location;
        if(direction.equals("west"))
            westExit = location;
        if(direction.equals("southEast"))
            southEastExit = location;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

}
