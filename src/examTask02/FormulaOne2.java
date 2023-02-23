package examTask02;

import java.util.*;

public class FormulaOneWithStatics {
    private static int playerRow = 0;
    private static int playerCol = 0;
    private static boolean playerWon = false;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int commandsCount = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];

        for (int row = 0; row < size; row++) {
            String matrixRow = scanner.nextLine();
            matrix[row] = matrixRow.toCharArray();

            if (matrixRow.contains("P")) {
                playerRow = row;
                playerCol = matrixRow.indexOf("P");
            }
        }

        while (commandsCount-- > 0 && !playerWon) {
            String command = scanner.nextLine();

            switch (command) {
                case "up":
                    movePlayer(matrix, -1, 0);
                    break;
                case "down":
                    movePlayer(matrix, 1, 0);
                    break;
                case "left":
                    movePlayer(matrix, 0, -1);
                    break;
                case "right":
                    movePlayer(matrix, 0, 1);
                    break;
            }
        }

        System.out.println(playerWon ? "Well done, the player won first place!"
                : "Oh no, the player got lost on the track!");

        printMatrix(matrix);
    }

    private static void movePlayer(char[][] matrix, int rowModifier, int colModifier) {
        int nextRow = playerRow + rowModifier;
        int nextCol = playerCol + colModifier;

        if (isOutOfBounds(matrix, nextRow, nextCol)) {
            if (nextRow < 0 || nextRow >= matrix.length) {

                nextRow = nextRow < 0 ? matrix.length - 1 : 0;
            } else {
                nextCol = nextCol < 0 ? matrix[nextRow].length - 1 : 0;
            }
        }

        if (matrix[nextRow][nextCol] == 'T') {
            return;
        } else if (matrix[nextRow][nextCol] == 'B') {
            matrix[playerRow][playerCol] = '.';
            playerRow = nextRow;
            playerCol = nextCol;
            movePlayer(matrix, rowModifier, colModifier);
            return;
        } else if (matrix[nextRow][nextCol] == 'F') {
           playerWon = true;
        }

        if (matrix[playerRow][playerCol] != 'B') {
            matrix[playerRow][playerCol] = '.';
        }

        matrix[nextRow][nextCol] = 'P';
        playerRow = nextRow;
        playerCol = nextCol;
    }

    private static boolean isOutOfBounds(char[][] matrix, int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix.length;
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
