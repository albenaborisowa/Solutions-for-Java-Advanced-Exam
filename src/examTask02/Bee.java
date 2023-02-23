package examTask02;

import java.util.*;

public class Bee {
    private static int beeRow = 0;
    private static int beeCol = 0;
    private static int pollinatedFlowers = 0;
    private static boolean beeIsInMatrix = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];


        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();

            if (line.contains("B")) {
                beeRow = row;
                beeCol = line.indexOf("B");
            }
        }
        String command = scanner.nextLine();

        while (!command.equals("End")) {

            switch (command) {
                case "up":
                    moveBee(matrix, -1, 0);
                    break;
                case "down":
                    moveBee(matrix, 1, 0);
                    break;
                case "left":
                    moveBee(matrix, 0, -1);
                    break;
                case "right":
                    moveBee(matrix, 0, 1);
                    break;
            }
            if (!beeIsInMatrix) {
                break;
            }
            command = scanner.nextLine();
        }


        if (!beeIsInMatrix) {
            System.out.println("The bee got lost!");
        }
        if (pollinatedFlowers < 5) {
            System.out.printf("The bee couldn't pollinate the flowers, she needed %d flowers more%n",
                    5 - pollinatedFlowers);
        } else {
            System.out.printf("Great job, the bee manage to pollinate %d flowers!%n",
                    pollinatedFlowers);

        }

        printMatrix(matrix);
    }

    private static void moveBee(char[][] matrix, int rowMutator, int colMutator) {
        int nextRow = beeRow + rowMutator;
        int nextCol = beeCol + colMutator;

        boolean isBonusHit = false;

        if (!isInBounds(matrix, nextRow, nextCol)) {
            matrix[beeRow][beeCol] = '.';
            beeIsInMatrix = false;
            return;
        }
        if (matrix[nextRow][nextCol] == 'f') {
            pollinatedFlowers++;
        } else if (matrix[nextRow][nextCol] == 'O') {
            isBonusHit = true;
        }
        matrix[beeRow][beeCol] = '.';
        matrix[nextRow][nextCol] = 'B';
        beeRow = nextRow;
        beeCol = nextCol;

        if (isBonusHit) {
            moveBee(matrix, rowMutator, colMutator);
        }
    }

    private static boolean isInBounds(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
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
