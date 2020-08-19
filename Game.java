
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private Room previousRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Player();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Item pickaxe, rayGun, alienSecret, spaceFood, rover;

        //create items
        pickaxe = new Item("Pickaxe", 2000);
        rayGun = new Item("Ray gun", 500);
        alienSecret = new Item("Alien Secret", 1000);
        spaceFood = new Item("Space food", 800);
        rover = new Item("Rover",10000);

        Room earth, iss, moon, mars, jupiter, saturn, ess;

        // create the rooms
        earth = new Room("the earth. Starting point");
        iss = new Room("the international space station");
        moon = new Room("the moon");
        mars = new Room("mars");
        jupiter = new Room("jupiter");
        saturn = new Room("saturn");
        ess = new Room("the european space station");

        // initialise room exits
        earth.setExits("north", iss);
        earth.setExits("northEast", moon);
        earth.setExits("southEast", ess);
        iss.setExits("east", moon);
        iss.setExits("west", mars);
        iss.setExits("south", earth);
        moon.setExits("west",iss);
        moon.setExits("southWest",earth);
        mars.setExits("east", iss);
        mars.setExits("south", jupiter);
        jupiter.setExits("north", mars);
        jupiter.setExits("south", saturn);
        saturn.setExits("north", jupiter);
        ess.setExits("northWest", earth);

        // initialise room items
        iss.addItem(pickaxe);
        iss.addItem(spaceFood);
        ess.addItem(pickaxe);
        moon.addItem(rover);
        mars.addItem(rayGun);
        saturn.addItem(alienSecret);

        // start game outside
        player.setCurrentRoom(earth); 
        previousRoom = earth;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        player.look();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            player.goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            player.look();
        }
        else if (commandWord.equals("eat")) {
            player.eat();
        }
        else if (commandWord.equals("back")){
            player.back();
        }
        else{
            System.out.println("You can't do that :/");
        }
        return wantToQuit;
    }

    // implementations of user commands:
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
