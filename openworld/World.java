package openworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import openworld.Location.Location;
import openworld.Player.Player;
import openworld.Location.Forest;
import openworld.Location.ClownHouse;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class World {
    private Player player;
    private static List<Location> locations = new ArrayList<>();

    public World() {

    }

    private void initializeWorld(Scanner userInput) {
        Location clownHouse = new ClownHouse("Clown House", "A creepy house filled with clowns.", Item.KEY);
        Location forest = new Forest("Forest", "A dark forest filled with unknown dangers.", Item.AXE);
        locations.add(clownHouse);
        locations.add(forest);

        System.out.println("Enter username:");
        String userName = userInput.nextLine();
        System.out.println("Username is: " + userName);

        player = new Player(userName, forest);
    }

    public static void main(String[] args) {
        World world = new World();

        new Thread(() -> {
            try {
                // Load the sound file
                File musicFile = new File("/Users/zetachazapi/Verintual-Nightmare/openworld/defaultMusic.wav"); // Adjust the path if necessary
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start(); // Play the music
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music indefinitely

                // Ensure the clip remains open while the program runs
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    clip.stop();
                    clip.close();
                }));
            } catch (UnsupportedAudioFileException e) {
                System.err.println("The specified audio file is unsupported: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("An I/O error occurred: " + e.getMessage());
            } catch (LineUnavailableException e) {
                System.err.println("Audio line for playing back the audio is unavailable: " + e.getMessage());
            }
        }).start();

        // Using one Scanner for the entire program
        Scanner userInput = new Scanner(System.in);

        // Initialize the world
        world.initializeWorld(userInput);

        System.out.println(
                "Time for you to choose your fate. Do not be too quick to choose. Press 'I' for more information: ");

        boolean valid = false;
        boolean valid2 = false;

        // User must press 'I' to continue
        while (!valid) {
            if (userInput.hasNextLine()) {
                char info = userInput.nextLine().charAt(0);
                if (info != 'I') {
                    System.out.println("Only Capitalized 'I'. Do not make me mad.");
                } else {
                    valid = true;
                    System.out.println("You have two paths. Choose wisely.");

                    // Storing location info
                    Location location1 = locations.get(0);
                    Location location2 = locations.get(1);
                    char loc1FirstChar = location1.getName().charAt(0);
                    char loc2FirstChar = location2.getName().charAt(0);

                    System.out.println(location1.toString());
                    System.out.println("or");
                    System.out.println(location2.toString());
                    System.out.println("Ready? Enter '" + loc1FirstChar + "' for " + location1.getName() + " or '"
                            + loc2FirstChar + "' for " + location2.getName());

                    // Choose between two locations
                    while (!valid2) {
                        if (userInput.hasNextLine()) {
                            char choice = userInput.nextLine().charAt(0);
                            if (choice != loc1FirstChar && choice != loc2FirstChar) {
                                System.out.println("Invalid choice. Please enter '" + loc1FirstChar + "' or '"
                                        + loc2FirstChar + "'.");
                            } else {
                                valid2 = true;
                                if (choice == loc1FirstChar) {
                                    world.player.moveTo(location1);
                                } else {
                                    world.player.moveTo(location2);
                                }
                            }
                        }
                    }
                }
            }
        }

        // Move on to the game grid after location selection
        int rows = 5, cols = 5;
        int dotRow = 2, dotCol = 2; // Initial position of the dot (center of the grid)

        while (true) {
            // Print the grid with the current position of the dot
            GameGrid.printGrid(rows, cols, dotRow, dotCol);

            // Get user input for moving the dot
            System.out.print("Move (n - north, s - south, e - east, w - west, q - quit): ");
            if (userInput.hasNextLine()) {
                char move = userInput.nextLine().charAt(0);

                // Update dot's position based on input
                switch (move) {
                    case 'n':
                        if (dotRow > 0)
                            dotRow--; // Move up
                        break;
                    case 's':
                        if (dotRow < rows - 1)
                            dotRow++; // Move down
                        break;
                    case 'e':
                        if (dotCol < cols - 1)
                            dotCol++; // Move right
                        break;
                    case 'w':
                        if (dotCol > 0)
                            dotCol--; // Move left
                        break;
                    case 'q':
                        System.out.println("Quitting the game.");
                        userInput.close();
                        return; // Exit the program
                    default:
                        System.out.println("Invalid input! Use n, s, e, w, or q.");
                }
            }
        }
    }
}