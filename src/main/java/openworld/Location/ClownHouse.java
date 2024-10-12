package src.main.java.openworld.Location;

import java.util.Scanner;

import src.main.java.openworld.Item;
import src.main.java.openworld.Player.Player;

import java.io.File;
import javax.sound.sampled.*;

public class ClownHouse extends Location {
    private Item item;

    public ClownHouse(String name, String description, Item item) {
        super(name, description);
        this.item = item;
    }

    String circusPath = "src/main/resources/circus.wav";

    Thread circusThread = new Thread(() -> {
        try {

            File audioFile = new File(circusPath);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            Clip clip = AudioSystem.getClip();

            clip.open(audioStream);

            clip.start();

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
            Thread.sleep(clip.getMicrosecondLength() / 1000);
            audioStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    });

    @Override
    public void enter(Player player) {

        circusThread.start();

        System.out.println("You enter the " + getName() + ": " + getDescription());
        if (item != null) {
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

    public Item getItem() {
        return item;
    }
}
