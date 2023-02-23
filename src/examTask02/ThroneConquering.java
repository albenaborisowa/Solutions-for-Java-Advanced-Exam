package examTask02;

import java.util.*;

public class ThroneConquering {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int energy = Integer.parseInt(scanner.nextLine());
        int rows = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[rows][];
        readMatrix(matrix, scanner);

        int parisRow = findParis(matrix)[0];
        int parisCol = findParis(matrix)[1];
        matrix[parisRow][parisCol] = '-';

        String line = scanner.nextLine();
        while (energy > 0) {
            String[] data = line.split(" ");
            String command = data[0];
            int spartanRow = Integer.parseInt(data[1]);
            int spartanCol = Integer.parseInt(data[2]);
            matrix[spartanRow][spartanCol] = 'S';

            switch (command) {
                case "up":
                    if (parisRow != 0) {
                        parisRow--;
                    }
                    break;
                case "down":
                    if (parisRow != rows - 1) {
                        parisRow++;
                    }
                    break;
                case "left":
                    if (parisCol != 0) {
                        parisCol--;
                    }
                    break;
                case "right":
                    if (parisCol != matrix.length - 1) {
                        parisCol++;
                    }
                    break;
            }

            energy--;

            if (matrix[parisRow][parisCol] == 'S') {
                energy -= 2;
                matrix[parisRow][parisCol] = '-';
            } else if (matrix[parisRow][parisCol] == 'H') {
                matrix[parisRow][parisCol] = '-';
                System.out.printf("Paris has successfully abducted Helen! Energy left: %d%n", energy);
                break;
            }

            if (energy <= 0) {
                matrix[parisRow][parisCol] = 'X';
                System.out.printf("Paris died at %d;%d.%n", parisRow, parisCol);
                break;
            }

            line = scanner.nextLine();
        }

        printMatrix(matrix);
    }

    public static void readMatrix(char[][] matrix, Scanner scanner) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] arr : matrix) {
            for (char n : arr) {
                System.out.print(n + "");
            }
            System.out.println();
        }
    }

    public static int[] findParis(char[][] matrix) {
        int[] parisPosition = new int[2];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'P') {
                    parisPosition[0] = row;
                    parisPosition[1] = col;
                }
            }
        }
        return parisPosition;
    }
}
