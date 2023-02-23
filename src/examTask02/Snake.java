package examTask02;

import java.util.*;

public class Snake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        int snakeRow = 0;
        int snakeCol = 0;

        int firstBurrowRow = 0;
        int firstBurrowCol = 0;

        int secondBurrowRow = 0;
        int secondBurrowCol = 0;

        boolean firstBurrowIsFound = false;

        char[][] matrix = new char[size][size];

        for (int row = 0; row < size; row++) {
            char[] line = scanner.nextLine().toCharArray();
            for (int col = 0; col < size; col++) {
                matrix[row][col] = line[col];
                if (matrix[row][col] == 'S') {
                    snakeRow = row;
                    snakeCol = col;
                }
                if (matrix[row][col] == 'B' && !firstBurrowIsFound) {
                    firstBurrowRow = row;
                    firstBurrowCol = col;
                    firstBurrowIsFound = true;
                }
                if (matrix[row][col] == 'B' && firstBurrowIsFound) {
                    secondBurrowRow = row;
                    secondBurrowCol = col;
                }
            }
        }
        int foodEaten = 0;
        String command = scanner.nextLine();

        while (foodEaten < 10) {

            matrix[snakeRow][snakeCol] = '.';

            switch (command) {
                case "up":
                    snakeRow--;
                    break;
                case "down":
                    snakeRow++;
                    break;
                case "left":
                    snakeCol--;
                    break;
                case "right":
                    snakeCol++;
                    break;
            }
            if (snakeRow < 0 || snakeRow >= size || snakeCol < 0 || snakeCol >= size) {
                break;
            }
            if (matrix[snakeRow][snakeCol] == '*') {
                matrix[snakeRow][snakeCol] = '.';
                foodEaten++;
            } else if (snakeRow == firstBurrowRow && snakeCol == firstBurrowCol) {
                snakeRow = secondBurrowRow;
                snakeCol = secondBurrowCol;
                matrix[firstBurrowRow][firstBurrowCol] = '.';
                matrix[snakeRow][snakeCol] = '.';
            } else if (snakeRow == secondBurrowRow && snakeCol == secondBurrowCol) {
                snakeRow = firstBurrowRow;
                snakeCol = firstBurrowCol;
                matrix[secondBurrowRow][secondBurrowCol] = '.';
                matrix[snakeRow][snakeCol] = '.';
            }
            if (foodEaten >= 10) {
                break;
            }
            command = scanner.nextLine();
        }
        if (foodEaten == 10) {
            matrix[snakeRow][snakeCol] = 'S';
            System.out.println("You won! You fed the snake.");
        } else {
            System.out.println("Game over!");
        }
        System.out.println("Food eaten: " + foodEaten);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}

