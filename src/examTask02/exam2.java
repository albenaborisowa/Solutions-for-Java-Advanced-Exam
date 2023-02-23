package examTask02;

import java.util.*;

public class exam2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String carNumber = scanner.nextLine();
        String[][] matrix = new String[size][size];

        int carRow = 0;
        int carCol = 0;

        int firstTunnelRow = 0;
        int firstTunnelCol = 0;

        int secondTunnelRow = 0;
        int secondTunnelCol = 0;

        boolean firstTunnelIsFound = false;

        for (int row = 0; row < size; row++) {
            String[] line = scanner.nextLine().split("\\s+");
            for (int col = 0; col < size; col++) {
                matrix[row][col] = line[col];
                if (matrix[row][col].equals("T") && !firstTunnelIsFound) {
                    firstTunnelRow = row;
                    firstTunnelCol = col;
                    firstTunnelIsFound = true;
                }
                if (matrix[row][col].equals("T") && firstTunnelIsFound) {
                    secondTunnelRow = row;
                    secondTunnelCol = col;
                }
            }
        }

        int kms = 0;
        boolean carWon = false;
        String command = scanner.nextLine();

        while (!command.equals("End")) {
            matrix[carRow][carCol] = ".";

            switch (command) {
                case "up":
                    carRow--;
                    break;
                case "down":
                    carRow++;
                    break;
                case "left":
                    carCol--;
                    break;
                case "right":
                    carCol++;
                    break;
            }
            if (matrix[carRow][carCol].equals(".")) {
                kms += 10;
            } else if (carRow == firstTunnelRow && carCol == firstTunnelCol) {
                kms += 30;
                carRow = secondTunnelRow;
                carCol = secondTunnelCol;
                matrix[firstTunnelRow][firstTunnelCol] = ".";
                matrix[carRow][carCol] = ".";
            } else if (carRow == secondTunnelRow && carCol == secondTunnelCol) {
                kms += 30;
                carRow = firstTunnelRow;
                carCol = firstTunnelCol;
                matrix[secondTunnelRow][secondTunnelCol] = ".";
                matrix[carRow][carCol] = ".";
            } else if (matrix[carRow][carCol].equals("F")) {
                kms += 10;
                carWon = true;
                break;
            }

            command = scanner.nextLine();
        }
        matrix[carRow][carCol] = "C";
        if (carWon) {
            System.out.printf("Racing car %s finished the stage!%n", carNumber);
        } else {
            System.out.printf("Racing car %s DNF.%n", carNumber);
        }

        System.out.printf("Distance covered %d km.%n", kms);

        printMatrix(matrix);
    }

    private static void printMatrix(String[][] matrix) {
        for (String[] arr : matrix) {
            for (String symbol : arr) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
