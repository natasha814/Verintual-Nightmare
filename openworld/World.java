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

        System.out.println(
                "Time for you to choose your fate. Do not be too quick to choose. Press 'I' for more information: ");

        Boolean valid = false;
        Boolean valid2 = false;

        while (valid != true) {
            char info = userInput.nextLine().charAt(0);
            if (info != 'I') {
                System.out.println("Only Capitalized 'I'. Do not make me mad.");
            } else {
                valid = true;
                System.out.println("You have two paths. Choose wisely.");
                // storing location info
                Location location1 = locations.get(0);
                Location location2 = locations.get(1);
                char loc1FirstChar = location1.getName().charAt(0);
                char loc2FirstChar = location2.getName().charAt(0);
                System.out.println(location1.toString());
                System.out.println("or");
                System.out.println(location2.toString());
                System.out.println("Ready? Enter '" + loc1FirstChar + "' for " + location1.getName() + " or '"
                        + loc2FirstChar + "' for " + location2.getName());

                while (valid2 != true) {
                    char choice = userInput.nextLine().charAt(0);
                    if (choice != loc1FirstChar && choice != loc2FirstChar) {
                        System.out.println(
                                "Invalid choice. Please enter '" + loc1FirstChar + "' or '" + loc2FirstChar + "'.");
                    } else if (choice == loc1FirstChar) {
                        valid2 = true;
                        world.player.moveTo(location1);
                    } else if (choice == loc2FirstChar) {
                        valid2 = true;
                        world.player.moveTo(location2);
                    }

                }

            }

        }

        userInput.close();
    }
}
