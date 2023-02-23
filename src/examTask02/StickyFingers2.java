package examTask02;

import java.util.*;
import java.util.stream.Collectors;

public class StickyFingers2 {
    private static int thiefRow = 0;
    private static int thiefCol = 0;
    private static int money = 0;
    private static boolean gotCough = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(",");

        char[][] matrix = new char[size][size];

        for (int row = 0; row < size; row++) {
            List<Character> charsList = Arrays.stream(scanner.nextLine().split(" "))
                    .map(s -> s.charAt(0))
                    .collect(Collectors.toList());
            for (int col = 0; col < size; col++) {
                char currentChar = charsList.get(col);
                if (currentChar == 'D') {
                    thiefRow = row;
                    thiefCol = col;
                }
                matrix[row][col] = currentChar;
            }
        }


        for (int i = 0; i < commands.length; i++) {


            switch (commands[i]) {
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
            if (gotCough) {
                break;
            }
        }
        if (!gotCough) {
            System.out.printf("Your last theft has finished successfully with %d$ in your pocket.%n", money);
        }
        printMatrix(matrix);
    }

    private static void movePlayer(char[][] matrix, int rowModifier, int colModifier) {
        int nextRow = thiefRow + rowModifier;
        int nextCol = thiefCol + colModifier;

        matrix[thiefRow][thiefCol] = '+';

        if (!isInBounds(nextRow, nextCol, matrix)) {
            System.out.println("You cannot leave the town, there is police outside!");
            return;
        }
        if (matrix[nextRow][nextCol] == 'P') {
            gotCough = true;
            System.out.printf("You got caught with %d$, and you are going to jail.%n", money);
            matrix[nextRow][nextCol] = '#';

        } else if (matrix[nextRow][nextCol] == '$') {
            money += nextRow * nextCol;
            matrix[nextRow][nextCol] = 'D';
            thiefRow = nextRow;
            thiefCol = nextCol;
            System.out.printf("You successfully stole %d$.%n", nextRow * nextCol);

        } else {
            matrix[nextRow][nextCol] = 'D';
            thiefRow = nextRow;
            thiefCol = nextCol;
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] arr : matrix) {
            for (char n : arr) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(int row, int col, char[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

}
