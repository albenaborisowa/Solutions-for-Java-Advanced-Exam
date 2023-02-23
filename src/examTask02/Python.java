package examTask02;

import java.util.*;
import java.util.stream.Collectors;

public class Python {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        String[] commands = scanner.nextLine().split(", ");

        char[][] matrix = new char[size][size];

        int pythonRow = 0;
        int pythonCol = 0;
        int food = 0;

        for (int row = 0; row < size; row++) {
            List<Character> characterList = Arrays.stream(scanner.nextLine().split(" "))
                    .map(e -> e.charAt(0))
                    .collect(Collectors.toList());
            for (int col = 0; col < size; col++) {
                char currentChar = characterList.get(col);
                matrix[row][col] = currentChar;
                if (matrix[row][col] == 's') {
                    pythonRow = row;
                    pythonCol = col;
                } else if (matrix[row][col] == 'f') {
                    food++;
                }
            }
        }
        int pythonLength = 1;

        boolean isPythonAlive = true;

        for (int i = 0; i < commands.length; i++) {
            String currentCommand = commands[i];
            matrix[pythonRow][pythonCol] = '*';

            switch (currentCommand) {
                case "up":
                    pythonRow--;
                    break;
                case "down":
                    pythonRow++;
                    break;
                case "left":
                    pythonCol--;
                    break;
                case "right":
                    pythonCol++;
                    break;
            }

            if (pythonRow < 0 || pythonRow >= size) {
                if (pythonRow < 0) {
                    pythonRow = size - 1;
                }
                if (pythonRow >= size) {
                    pythonRow = 0;
                }

            } else if (pythonCol < 0 || pythonCol >= size) {
                if (pythonCol < 0) {
                    pythonCol = size - 1;
                }
                if (pythonCol >= size) {
                    pythonCol = 0;
                }
            }

            if (matrix[pythonRow][pythonCol] == 'f') {
                pythonLength++;
                food--;
            }

            if (food == 0) {
                break;
            }

            if (matrix[pythonRow][pythonCol] == 'e') {
                isPythonAlive = false;
                break;
            }

            matrix[pythonRow][pythonCol] = 's';
        }

        if (food == 0) {
            System.out.printf("You win! Final python length is %d%n", pythonLength);
        } else if (!isPythonAlive) {
            System.out.println("You lose! Killed by an enemy!");
        } else {
            System.out.printf("You lose! There is still %d food to be eaten.%n", food);
        }
    }
}
