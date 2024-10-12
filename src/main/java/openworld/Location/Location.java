package src.main.java.openworld.Location;

import src.main.java.openworld.Player.Player;

public abstract class Location {

    private String name;
    private String description;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    @Override
    public abstract String toString();

    public abstract void enter(Player player);

}

