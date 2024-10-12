import java.util.ArrayList;
import java.util.List;
import Location.Location;

public class Player {
    private String name;
    private Location currentLocation;
    private List<Item> inventory;

    public Player(String name, Location currentLocation){
        this.name = name;
        this.currentLocation = currentLocation;
        this.inventory = new ArrayList<Item>();
    }

    public void moveTo(Location newLocation){
        currentLocation = newLocation;
        currentLocation.enter(this);
    }

    public void addItem(Item item){
        inventory.add(item);
        System.out.println("You've picked up: "  + item);
    }

}
