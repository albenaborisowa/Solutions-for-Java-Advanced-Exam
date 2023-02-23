package examTask02;

import java.util.*;

public class ReVolt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int commandsCount = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];
        int[] positions = new int[2];

        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();

            if (line.contains("f")) {
                positions[0] = row;
                positions[1] = line.indexOf("f");
            }
        }

        boolean playerWon = false;

        while (commandsCount-- > 0 && !playerWon) {
            String command = scanner.nextLine();

            switch (command) {
                case "up":
                    //след като са създадени интове за модификация в метода,
                    // за всеки case просто добавяме числото за модификация
                    playerWon = movePlayer(positions, matrix, -1, 0);
                    break;
                case "down":
                    playerWon = movePlayer(positions, matrix, +1, 0);
                    break;
                case "left":
                    playerWon = movePlayer(positions, matrix, 0, -1);
                    break;
                case "right":
                    playerWon = movePlayer(positions, matrix, 0, +1);
                    break;
            }
        }

        if (playerWon) {
            System.out.println("Player won!");
        } else {
            System.out.println("Player lost!");
        }
        printMatrix(matrix);
    }

    private static boolean movePlayer(int[] positions, char[][] matrix,
                                      int rowModification, int colModification) {
        int playerRow = positions[0];
        int playerCol = positions[1];
        int newRow = ensureIndexIsInBounds(playerRow + rowModification, matrix.length);
        int newCol = ensureIndexIsInBounds(playerCol + colModification, matrix.length);
        // в задачата B и T (бонус и капан) не се променят,
        // когато минем през тях - остават като В и Т в матрицата

        boolean hasWon = false;
        if (matrix[newRow][newCol] == 'B') {
            newRow = ensureIndexIsInBounds(newRow + rowModification, matrix.length);
            newCol = ensureIndexIsInBounds(newCol + colModification, matrix.length);

        } else if (matrix[newRow][newCol] == 'T') {
            newRow = playerRow;
            newCol = playerCol;
        }
        if (matrix[newRow][newCol] == 'F') {
            hasWon = true;
        }
        matrix[playerRow][playerCol] = '-';
        matrix[newRow][newCol] = 'f';
        positions[0] = newRow;
        positions[1] = newCol;
        return hasWon;
    }

    private static int ensureIndexIsInBounds(int index, int matrixLength) {
        if (index < 0) {
            index = matrixLength - 1;
        } else if (index >= matrixLength) {
            index = 0;
        }
        return index;
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] arr : matrix) {
            for (char symbol : arr) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
