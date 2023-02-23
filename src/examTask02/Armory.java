package examTask02;

import java.util.*;

public class Armory {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];

        int officerRow = -1;
        int officerCol = -1;

        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            for (int col = 0; col < size; col++) {
                matrix[row][col] = line.charAt(col);
                if (matrix[row][col] == 'A') {
                    officerRow = row;
                    officerCol = col;
                }
            }
        }

        int totalMoney = 0;
        boolean officerLeaves = false;

        while (!officerLeaves || totalMoney >= 65) {
            if (officerLeaves || totalMoney >= 65) {
                break;
            }
            String command = scanner.nextLine();

            switch (command) {
                case "up":
                    if (officerRow - 1 >= 0) {
                        matrix[officerRow][officerCol] = '-';
                        officerRow--;
                    } else {
                        officerLeaves = true;
                    }
                    break;

                case "down":
                    if (officerRow + 1 < size) {
                        matrix[officerRow][officerCol] = '-';
                        officerRow++;
                    } else {
                        officerLeaves = true;
                    }
                    break;

                case "left":
                    if (officerCol - 1 >= 0) {
                        matrix[officerRow][officerCol] = '-';
                        officerCol--;
                    } else {
                        officerLeaves = true;
                    }
                    break;

                case "right":
                    if (officerCol + 1 < size) {
                        matrix[officerRow][officerCol] = '-';
                        officerCol++;
                    } else {
                        officerLeaves = true;
                    }
                    break;

            }

            char currentChar = matrix[officerRow][officerCol];
            if (currentChar == 'M') {
                matrix[officerRow][officerCol] = '-';
                for (int row = 0; row < size; row++) {
                    for (int col = 0; col < size; col++) {
                        if (matrix[row][col] == 'M') {
                            officerRow = row;
                            officerCol = col;
                        }
                    }
                }

            } else if (Character.isDigit(currentChar)) {
                totalMoney += Character.getNumericValue(currentChar);
                matrix[officerRow][officerCol] = '-';
            } else {
                matrix[officerRow][officerCol] = '-';
            }

        }
        if (officerLeaves) {
            System.out.println("I do not need more swords!");
        }
        if (totalMoney >= 65) {
            matrix[officerRow][officerCol] = 'A';
            System.out.println("Very nice swords, I will come back for more!");
        }

        System.out.printf("The king paid %d gold coins.%n", totalMoney);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
