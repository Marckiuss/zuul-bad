import java.util.Stack;
public class Player
{
    private Stack<Room> rooms;
    private Room currentRoom;
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        rooms = new Stack<Room>();
    }

    public void setCurrentRoom(Room room){
        currentRoom = room;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            rooms.push(currentRoom);
            currentRoom = nextRoom;
            look();
        }
    }

    /**
     * Prints the room information
     */
    public void look(){
        System.out.println(currentRoom.getLongDescription());
    }

    public void eat(){
        System.out.println("You have eaten now and you are not hungry any more");
    }

    /**
     * travels to the previous room you were
     */
    public void back(){
        if(!rooms.isEmpty()){
            currentRoom = rooms.pop();
            look();
        }
    }
}