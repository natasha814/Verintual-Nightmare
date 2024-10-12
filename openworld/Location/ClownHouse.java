package openworld.Location;

import openworld.Player;

public class ClownHouse extends Location {

    public ClownHouse(String name, String description) {
        super(name, description);
    }

    @Override
    public void enter(Player player) {
        System.out.println("You enter the " + getName() + ": " + getDescription());
    }

    @Override
    public String toString() {
        return getName() + ": " + getDescription();
    }
}
