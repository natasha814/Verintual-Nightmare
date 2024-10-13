package src.main.java.openworld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Countdown {

    private JFrame frame;
    private JLabel label;
    private int countdownSeconds = 10;

    public Countdown() {

        // Create the frame

        frame = new JFrame("Countdown Timer");
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new FlowLayout());

        // Create a label to display the countdown
        label = new JLabel("QUICK YOU'RE BEING CHASED!!!");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.RED);
        frame.add(label);

        ImageIcon icon = new ImageIcon("src/main/java/openworld/images/zombie.jpg"); // Specify the path to your image
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH); // Resize the image
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Create a label for the resized image
        JLabel imageLabel = new JLabel(resizedIcon);
        frame.add(imageLabel);

        // Create a timer that counts down every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdownSeconds--;
                label.setText(countdownSeconds + " seconds");
                label.setForeground(Color.RED);

                if (countdownSeconds <= 0) {
                    ((Timer) e.getSource()).stop(); // Stop the timer
                    label.setText("Game Over.");
                    System.exit(0);
                }
            }
        });

        timer.start(); // Start the timer

        // Make the frame visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Countdown::new);
    }
}