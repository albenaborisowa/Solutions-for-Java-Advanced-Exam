package examTask02;

import java.util.*;

public class TreasureHunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] size = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(size[0]);
        int cols = Integer.parseInt(size[1]);

        String[][] matrix = new String[rows][cols];
        int playerRow = 0;
        int playerCol = 0;

        for (int row = 0; row < rows; row++) {
            String[] line = scanner.nextLine().split(" ");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = line[col];

                if (matrix[row][col].equals("Y")) {
                    playerRow = row;
                    playerCol = col;
                }
            }
        }
        List<String> movementsList = new LinkedList<>();
        String command = scanner.nextLine();

        while (!command.equals("Finish")) {

            int oldRow = playerRow;
            int oldCol = playerCol;
            movementsList.add(command);

            switch (command) {
                case "up":
                    playerRow--;
                    break;
                case "down":
                    playerRow++;
                    break;
                case "left":
                    playerCol--;
                    break;
                case "right":
                    playerCol++;
                    break;
            }
            if ((playerRow < 0 || playerRow >= rows || playerCol < 0 || playerCol >= cols) ||
                    matrix[playerRow][playerCol].equals("T")) {
                playerRow = oldRow;
                playerCol = oldCol;
                movementsList.remove(movementsList.size() - 1);

            }

            if (matrix[playerRow][playerCol].equals("X")) {
                System.out.println("I've found the treasure!");
                System.out.print("The right path is " + String.join(", ", movementsList));
                return;
            }

            command = scanner.nextLine();
        }

        System.out.println("The map is fake!");
    }
}
