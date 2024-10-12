package src.main.java.openworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.*;

import src.main.java.openworld.Location.ClownHouse;
import src.main.java.openworld.Location.Forest;
import src.main.java.openworld.Location.Graveyard;
import src.main.java.openworld.Location.HauntedHouse;
import src.main.java.openworld.Location.Location;
import src.main.java.openworld.Location.LockedShed;
import src.main.java.openworld.Player.Player;

import java.io.File;

public class World {
    private static Player player;
    private static List<Location> locations = new ArrayList<>();
    private static Location forest = new Forest("Forest", "A dark forest filled with unknown dangers.", null);
    private static Location forestWithAxe = new Forest("Forest", "A dark forest filled with unknown dangers.",
            Item.AXE);
    private static Location clownHouse = new ClownHouse("Clown Tent", "A creepy tent filled with clowns", null);
    private static Location clownHouseWithKey = new ClownHouse("Clown Tent", "A creepy tent filled with clowns",
            Item.KEY);
    private static Location hauntedHouse = new HauntedHouse("Haunted House", "A creaky house filled with secrets",
            null);
    private static Location hauntedHouseWithKnife = new HauntedHouse("Haunted House",
            "A creaky house filled with secrets", Item.KNIFE);
    private static Location lockedShed = new LockedShed("Locked Shed", "A very old and grotty looking shed",
            Item.DEAD_BODY);
    private static Location graveyard = new Graveyard("Graveyard", "A damp foul-smelling graveyard", null);
    private static Location[][] gridOfLocations = { { graveyard, graveyard, forest, forest, forest },
            { lockedShed, forest, forest, hauntedHouseWithKnife, hauntedHouse },
            { forest, forest, forest, forest, forest },
            { forest, forestWithAxe, clownHouse, forest, forest },
            { forest, forest, clownHouseWithKey, forest, forest } };

    public World() {
    }

    private void initializeWorld(Scanner userInput) {
        Location hauntedHouse = new HauntedHouse("Huanted House", "A creepy house filled with clowns.", Item.KEY);
        Location forest = new Forest("Forest", "A dark forest filled with unknown dangers.", Item.AXE);

        locations.add(hauntedHouse);
        locations.add(forest);

        System.out.println("Enter username:");
        String userName = userInput.nextLine();
        System.out.println("Username is: " + userName);

        player = new Player(userName);
    }

    public static void main(String[] args) {
        World world = new World();
        JumpScare jumpScare = new JumpScare();
        jumpScare.imageJump("src/main/java/openworld/forest.jpg", 2000);

        String background = "src/main/resources/background.wav";
        String scream = "src/main/resources/jumpscare.wav";
        String jumpscareRand = "src/main/resources/randomScare.wav";
        String laughingScare = "src/main/resources/laugh.wav";

        Thread backgroundThread = new Thread(() -> {
            AudioManager.playAudio(background);
        });

        Thread jumpscareThread = new Thread(() -> {
            AudioManager.playAudio(scream);
        });

        Thread jumpscareRandomThread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    int randomInterval = 10000 + random.nextInt(4000);

                    Thread.sleep(randomInterval);

                    File audioFile = new File(jumpscareRand);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();

                    // Add a listener to close the clip when finished
                    clip.addLineListener(event -> {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip.close();
                        }
                    });

                    // Wait for the audio to finish playing
                    while (clip.isRunning()) {
                        Thread.sleep(100); // Check every 100 ms if the clip is still running
                    }

                    audioStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread laughingThread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    int randomInterval = 1100 + random.nextInt(4000);

                    Thread.sleep(randomInterval);

                    File audioFile = new File(laughingScare);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();

                    // Add a listener to close the clip when finished
                    clip.addLineListener(event -> {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip.close();
                        }
                    });

                    // Wait for the audio to finish playing
                    while (clip.isRunning()) {
                        Thread.sleep(100); // Check every 100 ms if the clip is still running
                    }

                    audioStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        backgroundThread.start();

        jumpscareRandomThread.start();

        laughingThread.start();

        Scanner userInput = new Scanner(System.in);

        world.initializeWorld(userInput);

        System.out.println(
                "Time for you to choose your fate. Do not be too quick to choose. Press 'I' for more information: ");

        boolean valid = false;
        // boolean valid2 = false;

        // User must press 'I' to continue
        while (!valid) {
            if (userInput.hasNextLine()) {
                char info = userInput.nextLine().charAt(0);
                if (info != 'I') {
                    jumpScare.imageJump("src/main/java/openworld/scary1.png", 500);
                    jumpscareThread.start();

                    System.out.println("Only Capitalized 'I'. Do not make me mad.");
                } else {
                    valid = true;
                }
            }
        }

        // Move on to the game grid after location selection
        int rows = 5, cols = 5;
        int dotRow = 2, dotCol = 2; // Initial position of the dot (center of the grid)
        Location prevLocation = null;

        while (true) {
            Location currentLocation = gridOfLocations[dotRow][dotCol];
            if (prevLocation == null || currentLocation.getName() != prevLocation.getName()) {
                currentLocation.enter(player);
            } else if (currentLocation != prevLocation) {
                if (currentLocation.getItem() != null) {

                }
            }
            prevLocation = currentLocation;
            // Print the grid with the current position of the dot
            GameGrid.printGrid(rows, cols, dotRow, dotCol);

            // Get user input for moving the dot
            System.out.print(
                    "Move (w - north, s - south, d - east, a - west, q - quit, or i - to view your inventory): ");
            if (userInput.hasNextLine()) {
                char move = userInput.nextLine().toLowerCase().charAt(0);

                // Update dot's position based on input
                switch (move) {
                    case 'w':
                        if (dotRow > 0)
                            dotRow--; // Move up
                        break;
                    case 's':
                        if (dotRow < rows - 1)
                            dotRow++; // Move down
                        break;
                    case 'd':
                        if (dotCol < cols - 1)
                            dotCol++; // Move right
                        break;
                    case 'a':
                        if (dotCol > 0)
                            dotCol--; // Move left
                        break;
                    case 'i':
                        System.out.println(player.getInventory().toString());
                        break;
                    case 'q':
                        jumpscareThread.start();
                        System.out.println("Quitting the game.");
                        System.exit(0); // Exit the program
                    default:
                        System.out.println("Invalid input! Use w, a, s, d, or q.");
                }
            }
            // Location currentLocation = gridOfLocations[dotRow][dotCol];
            // currentLocation.enter(player);
        }
    }
}