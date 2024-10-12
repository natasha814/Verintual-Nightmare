import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Location.Forest;

public class World {
    private Player player;
    private List<Locations> locations;

    public World(Player player) {
        this.player = player;
        this.location = new ArrayList<>();
        initializeLocations();

    }

    private void initializeLocations() {
        Location clownHouse = new Clown_House("Clown House", "wkufjidhfhwf");
        Location forest = new Forest("Forest", "wkufjidhfhwf", "axe");
    }

    locations.add(clownHouse);locations.add(forest);

    // if user chooses Clown House

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);  
        System.out.println("Enter username");

        String userName = userInput.nextLine();  
        System.out.println("Username is: " + userName);  

        System.out.println("Time for you to choose your fate. Do not be too quick to choose, press 'I' for more information");
        
        if 
        
        
        System.out.println("Ready? Enter 'C' for Clown House or 'F' for Forest");
        

        player.moveTo(choice);

        
    }

}