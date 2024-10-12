package src.main.java.openworld.Player;

import src.main.java.openworld.Item;
import src.main.java.openworld.Location.Location;

public class Player {
    private String name;
    private Location currentLocation;
    private Inventory inventory;

    public Player(String name, Location currentLocation) {
        this.name = name;
        this.currentLocation = currentLocation;
        this.inventory = new Inventory(Item.TORCH, null);
    }

    public void moveTo(Location newLocation) {
        newLocation.enter(this);
        currentLocation = newLocation;
        currentLocation.enter(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

}
