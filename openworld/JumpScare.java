package openworld;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class JumpScare {

    public void imageJump(String filepath, int time){
        // Load the image
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filepath)); // Replace with your image path
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a JFrame
        JFrame frame = new JFrame("Image Popup");
        frame.setSize(500, 700); // Set the size of the window

        // Create a JLabel and set the image as an icon
        JLabel label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label); // Add the label to the frame

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        
        // Make the frame visible
        frame.setVisible(true);

        // Create a timer to close the frame after 1 second (500 milliseconds)
        Timer timer = new Timer(time, e -> {
            frame.dispose(); // Close the frame
        });
        timer.setRepeats(false); // Only execute once
        timer.start(); // Start the timer
    
    }
}
