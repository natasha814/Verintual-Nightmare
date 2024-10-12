import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Location.Location;
import Location.ClownHouse;
import Location.Forest;

public class World {
    private Player player;
    private List<Location> locations;

    public World(Player player) {
        this.player = player;
        this.locations = new ArrayList<>();
        initializeLocations();
    }

    // Initialize locations
    private void initializeLocations() {
        Location clownHouse = new ClownHouse("Clown House", "A creepy house filled with clowns.");
        Location forest = new Forest("Forest", "A dark forest filled with unknown dangers.", "axe");
        locations.add(clownHouse);
        locations.add(forest);
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter username:");

        String userName = userInput.nextLine();
        System.out.println("Username is: " + userName);

        System.out.println(
                "Time for you to choose your fate. Do not be too quick to choose. Press 'I' for more information.");

        char info = userInput.nextLine().charAt(0);
        if (info == 'I') {
            System.out.println("Clown House: " + clownHouse.getDescription());
            System.out.println("You have two paths. Choose wisely.");
        } else {
            System.out.println("Only Capitalized 'I'. Do not make me mad.");
        }

        System.out.println("Ready? Enter 'C' for Clown House or 'F' for Forest.");

        char choice = userInput.nextLine().charAt(0);

        World world = new World(new Player(userName, null));

        if (choice == 'C') {
            world.player.moveTo(world.locations.get(0));
        } else if (choice == 'F') {
            world.player.moveTo(world.locations.get(1));
        } else {
            System.out.println("Invalid choice. Please enter 'C' or 'F'.");
        }

        userInput.close();
    }
}
