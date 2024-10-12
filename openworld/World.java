package openworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import openworld.Location.Location;
import openworld.Location.Forest;
import openworld.Location.ClownHouse;

public class World {
    private Player player;
    private static List<Location> locations = new ArrayList<>();


    public World() {
        
    }

    // Initialize locations
    private void initializeWorld(Scanner userInput) {
        Location clownHouse = new ClownHouse("Clown House", "A creepy house filled with clowns.");
        Location forest = new Forest("Forest", "A dark forest filled with unknown dangers.", Item.AXE);
        locations.add(clownHouse);
        locations.add(forest);

        System.out.println("Enter username:");
        String userName = userInput.nextLine();
        System.out.println("Username is: " + userName);

        player = new Player(userName, forest);
    }
    

    public static void main(String[] args) {

        World world = new World();
        
        Scanner userInput = new Scanner(System.in);

        world.initializeWorld(userInput);

        Location testLocation = locations.get(0);

        System.out.println(
                "Time for you to choose your fate. Do not be too quick to choose. Press 'I' for more information: ");

        char info = userInput.nextLine().charAt(0);

        if (info != 'I') {
            System.out.println("Only Capitalized 'I'. Do not make me mad.");
        } else {
            System.out.println(testLocation.toString());
            System.out.println("You have two paths. Choose wisely.");
            System.out.println("Ready? Enter 'C' for Clown House or 'F' for Forest.");
            char choice = userInput.nextLine().charAt(0);

            if (choice == 'C') {
                world.player.moveTo(world.locations.get(0));
            } else if (choice == 'F') {
                world.player.moveTo(world.locations.get(1));
            } else {
                System.out.println("Invalid choice. Please enter 'C' or 'F'.");
            }
        }
        

        userInput.close();
    }
}
