import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Location.Location;
import Location.Forest;
import Location.ClownHouse;

public class World {
    private Player player;
    private List<Location> locations;
    private List<Location> locations;

    public World(Player player) {
        this.player = player;
        this.locations = new ArrayList<>();
        this.locations = new ArrayList<>();
        initializeLocations();

    }

    private void initializeLocations() {
        Location clownHouse = new ClownHouse("Clown House", "wkufjidhfhwf");
        Location forest = new Forest("Forest", "wkufjidhfhwf", "axe");
    }

    locations.add(clownHouse);locations.add(forest);

    // if user chooses Clown House

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);  
        System.out.println("Enter username");

        String userName = userInput.nextLine();  
        System.out.println("Username is: " + userName);  

        char info = userInput.nextLine();
        System.out.println("Time for you to choose your fate. Do not be too quick to choose, press 'I' for more information");
        
        if (info = 'I'){

        } else{
            System.out.println("Capitallized I. Do not make me mad.");
        }
        
        
        System.out.println("Ready? Enter 'C' for Clown House or 'F' for Forest");
        

        player.moveTo();

        
    }

}