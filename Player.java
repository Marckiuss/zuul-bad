import java.util.Stack;
import java.util.ArrayList;
public class Player
{
    private Stack<Room> rooms;
    private Room currentRoom;
    private ArrayList<Item> pickedItems;
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        rooms = new Stack<Room>();
        pickedItems = new ArrayList<Item>();
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

    public void take(String itemDescription){
        ArrayList<Item> availableItems = currentRoom.getRoomItems();
        boolean picked = false;
        for(int i = 0; i < availableItems.size() && !picked; i++){
            Item item = availableItems.get(i);
            if(item.getDescription().equals(item.getDescription())){
                picked = true;
                pickedItems.add(item);
                currentRoom.removeItem(item);
                System.out.println("You've picked up" + item.getDescription());                
            }
            else{
                System.out.println("Oh, it seems you can't do that");
            }
        }
    }

    public void getItems(){
        int actualWeight = 0;
        if(!pickedItems.isEmpty()){
            for(Item item : pickedItems ){
                System.out.println(item.getDescription());
                actualWeight += item.getWeight();
            }
            System.out.println("Total weight: " + actualWeight + "\n");
        }
        else{
            System.out.println("Your pockets are empty! :( \n");
        }
    }

    public void drop(String Description){
        for(int i = 0; i < pickedItems.size(); i++){
            Item item = pickedItems.get(i);
            if(item.getDescription().equals(Description) && item.isPickable()){
                currentRoom.addItem(item);
                pickedItems.remove(item);
                System.out.println("You've dropped " + item.getDescription() + "\n");
            }
        }
    }
}   