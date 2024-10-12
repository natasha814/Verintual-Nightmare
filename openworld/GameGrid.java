package openworld;

public class GameGrid {

    public static void printGrid(int rows, int cols, int dotRow, int dotCol) {
        // Print the grid
        for (int row = 0; row < rows; row++) {
            // Print the row separator
            System.out.print("   +");
            for (int col = 0; col < cols; col++) {
                System.out.print("---+");
            }
            System.out.println();

            // Print the row number
            System.out.printf(" %d |", row);

            // Print the cells
            for (int col = 0; col < cols; col++) {
                if (row == dotRow && col == dotCol) {
                    System.out.print(" * |"); // Print the dot
                } else {
                    System.out.print("   |");
                }
            }
            System.out.println();
        }

        // Print the bottom row separator
        System.out.print("   +");
        for (int col = 0; col < cols; col++) {
            System.out.print("---+");
        }
        System.out.println();
    }

}
