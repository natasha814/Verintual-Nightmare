package src.main.java.openworld.Location;

import java.util.Scanner;

import src.main.java.openworld.Item;
import src.main.java.openworld.JumpScare;
import src.main.java.openworld.Player.Player;

public class LockedShed extends Location {
    private Item item;

    public LockedShed(String name, String description, Item item) {
        super(name, description);
        this.item = item;
    }

    public void tryToEnter(Player player) {
        if (player.getInventory().searchForItem(Item.KEY)) {
            System.out.println("You unlock the shed door, welcome inside...");
            enter(player);
        } else {
            System.out.println("You don't have the key. Better luck next time.");
        }

    }

    @Override
    public void enter(Player player) {
        JumpScare jumpScare = new JumpScare();
        jumpScare.imageJump("src/main/java/openworld/images/shed.jpg", 2000);
        System.out.println("You enter the " + getName() + ": " + getDescription());
        
        itemOptions(player);
    }

    @Override
    public void itemOptions(Player player) {
        if (item == Item.DEAD_BODY) {
            System.out.println("You can smell something awful in here");
            System.out.println("...");
            System.out.println("You see something in the corner");
            System.out.println("Is that a dead body?");
            Scanner userInput = new Scanner(System.in);
            System.out.println("Do you want a closer look? [Y/N]");
            char info = userInput.nextLine().toLowerCase().charAt(0);
            if (info == 'y') {
                // YOURE GETTING CHASED AAAAAAA
            }
        } else if (item != null) {
            String a_or_an = "a ";
            if (checkFirstLetterVowel(item)) {
                a_or_an = "an ";
            }
            System.out.println("You see " + a_or_an + item.name().toLowerCase() + " here.");
            Scanner userIn = new Scanner(System.in);
            System.out.println("Would you like to pick it up? [Y/N]");
            char info = userIn.nextLine().toLowerCase().charAt(0);
            if (info == 'y') {
                player.getInventory().addItem(item);
            }
        }
    }

    private boolean checkFirstLetterVowel(Item item) {
        String itemString = item.name();
        char lower_char = Character.toLowerCase(itemString.charAt(0));
        if (lower_char == 'a' || lower_char == 'e' || lower_char == 'i' || lower_char == 'o' || lower_char == 'u') {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getName() + ": " + getDescription();
    }

    @Override
    public Item getItem() {
        return item;
    }

}
