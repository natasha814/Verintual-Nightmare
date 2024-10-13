package src.main.java.openworld;

public class GameGrid {

    public static void printGrid(int rows, int cols, int dotRow, int dotCol, String userCharacter) {
        // Print the grid
        for (int row = 0; row < rows; row++) {
            // Print the row separator
            System.out.print("   𖥔");
            for (int col = 0; col < cols; col++) {
                System.out.print("----𖥔");
            }
            System.out.println();

            // Print the row number
            System.out.printf(" %d |", row);

            // Print the cells
            for (int col = 0; col < cols; col++) {
                if (row == dotRow && col == dotCol) {
                    System.out.printf(" %s |", userCharacter); // Use printf for proper formatting
                } else {
                    System.out.print("    |");
                }
            }
            System.out.println();
        }

        // Print the bottom row separator
        System.out.print("   𖥔");
        for (int col = 0; col < cols; col++) {
            System.out.print("----𖥔");
        }
        System.out.println();
    }
}
