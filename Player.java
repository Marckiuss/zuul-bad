import java.util.Stack;
import java.util.ArrayList;
public class Player
{
    private Stack<Room> rooms;
    private Room currentRoom;
    private ArrayList<Item> pickedItems;
    private int carryingLimit;
    private int currentWeight;
    private boolean haveRead;
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        rooms = new Stack<Room>();
        pickedItems = new ArrayList<Item>();
        carryingLimit = 2500;
        haveRead = false;
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

    public void take(String id){
        ArrayList<Item> availableItems = currentRoom.getRoomItems();
        boolean picked = false;
        for(int i = 0; i < availableItems.size() && !picked; i++){
            Item item = availableItems.get(i);
            if(item.getId().equals(id) && currentWeight + item.getWeight() < carryingLimit){
                picked = true;
                pickedItems.add(item);
                availableItems.remove(item);
                currentWeight += item.getWeight();
                System.out.println("You've picked up" + item.getDescription() + "You can carry another " + (carryingLimit - currentWeight) + " grams");                
            }
            else{
                System.out.println("You can't pick that :/ Try to drop something");
            }
        }
    }

    public void getItems(){
        if(!pickedItems.isEmpty()){
            for(Item item : pickedItems ){
                System.out.println(item.getDescription());
            }
            System.out.println("Total weight: " + currentWeight + "\n" + "You can carry another " + (carryingLimit - currentWeight) + " grams \n");
        }
        else{
            System.out.println("Your pockets are empty! :( \n");
        }
    }

    public void drop(String id){
        if(!pickedItems.isEmpty()){
            for(int i = 0; i < pickedItems.size(); i++){
                Item item = pickedItems.get(i);
                if(item.getId().equals(id) && item.isPickable()){
                    currentWeight -= item.getWeight();
                    currentRoom.addItem(item);
                    pickedItems.remove(item);
                    System.out.println("You've dropped " + item.getDescription() + "\n" + "You can now carry " + (carryingLimit - currentWeight) + " grams \n");
                }
            }
        }
        else{
            System.out.println("Your pockets are empty, boy");
        }
    }

    public void read(String id){
        if(currentRoom.getDescription().equals("saturn") && !haveRead){
            carryingLimit += carryingLimit;
            haveRead = true;
            System.out.println("You've discovered what god really is. You acknowledge every single thing about the universe, but make sure you make it usefull");
            System.out.println("Carrying limit doubled. Enjoy");
        }
        else{
            System.out.println("Nothing to read here");
        }
    }
}   