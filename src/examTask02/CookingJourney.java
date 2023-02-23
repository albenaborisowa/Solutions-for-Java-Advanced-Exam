package examTask02;

import java.util.*;

public class CookingJourney {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[size][size];

        int currentRow = 0;
        int currentCol = 0;

        int firstPilarRow = 0;
        int firstPilarCol = 0;

        int secondPilarRow = 0;
        int secondPilarCol = 0;

        boolean weFoundFirstPillar = false;

        for (int row = 0; row < size; row++) {
            String[] input = scanner.nextLine().split("");
            for (int col = 0; col < size; col++) {
                matrix[row][col] = input[col];

                if (matrix[row][col].equals("S")) {
                    currentRow = row;
                    currentCol = col;
                }

                if (matrix[row][col].equals("P") && !weFoundFirstPillar) {
                    firstPilarRow = row;
                    firstPilarCol = col;
                    weFoundFirstPillar = true;
                }
                if (matrix[row][col].equals("P") && weFoundFirstPillar) {
                    secondPilarRow = row;
                    secondPilarCol = col;
                }
            }
        }
        int money = 0;

        while (money < 50) {
            String command = scanner.nextLine();

            int oldRow = currentRow;
            int oldCol = currentCol;

            switch (command) {
                case "up":
                    currentRow--;
                    break;
                case "down":
                    currentRow++;
                    break;
                case "left":
                    currentCol--;
                    break;
                case "right":
                    currentCol++;
                    break;
            }

            if (currentRow < 0 || currentCol < 0 || currentRow >= size || currentCol >= size) {
                matrix[oldRow][oldCol] = "-";
                break;
            }
            if (currentRow == firstPilarRow && currentCol == firstPilarCol) {
                currentRow = secondPilarRow;
                currentCol = secondPilarCol;
                matrix[oldRow][oldCol] = "-";
                matrix[firstPilarRow][firstPilarCol] = "-";
                matrix[currentRow][currentCol] = "S";
            } else if (currentRow == secondPilarRow && currentCol == secondPilarCol) {
                currentRow = firstPilarRow;
                currentCol = firstPilarCol;
                matrix[oldRow][oldCol] = "-";
                matrix[secondPilarRow][secondPilarCol] = "-";
                matrix[currentRow][currentCol] = "S";
            } else if (matrix[currentRow][currentCol].equals("-")) {
                matrix[oldRow][oldCol] = "-";
                matrix[currentRow][currentCol] = "S";
            } else {
                money += Integer.parseInt(matrix[currentRow][currentCol]);
                matrix[oldRow][oldCol] = "-";
                matrix[currentRow][currentCol] = "S";
            }

        }

        if (money >= 50) {
            System.out.println("Good news! You succeeded in collecting enough money!");
        } else {
            System.out.println("Bad news! You are out of the pastry shop.");
        }

        System.out.println("Money: " + money);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}

