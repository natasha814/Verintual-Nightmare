package src.main.java.openworld.Location;

import java.io.File;
import java.util.Scanner;
import javax.sound.sampled.*;

import src.main.java.openworld.AudioManager;
import src.main.java.openworld.Item;
import src.main.java.openworld.JumpScare;
import src.main.java.openworld.Player.Player;

public class HauntedHouse extends Location {
    private Item item;
    private String hauntedPath = "src/main/resources/haunted.wav";

    public HauntedHouse(String name, String description, Item item) {
        super(name, description);
        this.item = item;
    }

    @Override
    public void enter(Player player) {
        JumpScare jumpScare = new JumpScare();

        // Play the audio using AudioManager
        Thread hauntedThread = new Thread(() -> {
            AudioManager.playAudio(hauntedPath);
        });

        hauntedThread.start(); // Start the new thread

        // Display the jumpscare and location description
        jumpScare.imageJump("src/main/java/openworld/images/house.jpg", 2000);
        System.out.println("You enter the " + getName() + ": " + getDescription());

        if (item != null) {
            String a_or_an = checkFirstLetterVowel(item) ? "an " : "a ";
            System.out.println("You see " + a_or_an + item.name().toLowerCase() + " here.");

            Scanner userIn = new Scanner(System.in);
            System.out.println("Would you like to pick it up? [Y/N]");
            char info = userIn.nextLine().toLowerCase().charAt(0);

            if (info == 'y') {
                player.getInventory().addItem(item);
            }
        }

        // Display the jumpscare and location description
        jumpScare.imageJump("src/main/java/openworld/images/house.jpg", 2000);
        System.out.println("You enter the " + getName() + ": " + getDescription());

        if (item != null) {
            String a_or_an = checkFirstLetterVowel(item) ? "an " : "a ";
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

    public Item getItem() {
        return item;
    }

}