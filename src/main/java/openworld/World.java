package src.main.java.openworld;

import java.util.ArrayList;
import java.util.List;
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
    private static Location forestWithAxe = new Forest("Forest", "A dark forest filled with unknown dangers.", Item.AXE);
    private static Location clownHouse = new ClownHouse("Clown Tent", "A creepy tent filled with clowns", null);
    private static Location clownHouseWithKey = new ClownHouse("Clown Tent", "A creepy tent filled with clowns", Item.KEY);
    private static Location hauntedHouse = new HauntedHouse("Haunted House", "A creaky house filled with secrets", null);
    private static Location hauntedHouseWithKnife = new HauntedHouse("Haunted House", "A creaky house filled with secrets", Item.KNIFE);
    private static Location lockedShed = new LockedShed("Locked Shed", "A very old and grotty looking shed", Item.DEAD_BODY);
    private static Location graveyard = new Graveyard("Graveyard", "A damp foul-smelling graveyard", null);
    private static Location[][] gridOfLocations = {{graveyard, graveyard, forest, forest, forest},
                                                    {lockedShed, forest, forest, hauntedHouseWithKnife, hauntedHouse},
                                                    {forest, forest, forest, forest, forest},
                                                    {forest, forestWithAxe, clownHouse, forest, forest},
                                                    {forest, forest, clownHouseWithKey, forest, forest}};
    private URL url;
    private Clip clip;

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

        player = new Player(userName, hauntedHouse);
    }

    public static void main(String[] args) {
        World world = new World();
        JumpScare jumpScare = new JumpScare();
        jumpScare.imageJump("src/main/java/openworld/forest.jpg", 2000);

        String filePath = "src/main/resources/background.wav";
        String filePath2 = "src/main/resources/jumpscare.wav";

        Thread audioThread = new Thread(() -> {
            try {
                File audioFile = new File(filePath);

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

        Thread jumpscareThread = new Thread(() -> {
            try {

                File audioFile = new File(filePath2);

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

        audioThread.start();

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
                    valid=true;
                }
            }
        }

        // Move on to the game grid after location selection
        int rows = 5, cols = 5;
        int dotRow = 2, dotCol = 2; // Initial position of the dot (center of the grid)

        while (true) {
            Location currentLocation = gridOfLocations[dotRow][dotCol];
            currentLocation.enter(player);
            // Print the grid with the current position of the dot
            GameGrid.printGrid(rows, cols, dotRow, dotCol);

            // Add a small delay between grid prints to slow down the loop
            try {
                Thread.sleep(500); // 500 milliseconds (0.5 seconds) delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Get user input for moving the dot
            System.out.print("Move (w - north, s - south, d - east, a - west, q - quit, or i - to view your inventory): ");
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
                        System.out.println("Quitting the game.");
                        userInput.close();
                        return; // Exit the program
                    default:
                        System.out.println("Invalid input! Use w, a, s, d, or q.");
                }
            }
            // Location currentLocation = gridOfLocations[dotRow][dotCol];
            // currentLocation.enter(player);
        }
    }
}