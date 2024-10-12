package src.main.java.openworld.Player;

import src.main.java.openworld.Item;
import src.main.java.openworld.Location.Location;

public class Player {
    private String name;
    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory(Item.TORCH, null);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

}
