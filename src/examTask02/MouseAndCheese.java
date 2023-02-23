package examTask02;

import java.util.*;

public class MouseAndCheese {
    private static int mouseRow = 0;
    private static int mouseCol = 0;
    private static int eatenCheese = 0;
    private static boolean mouseIsInMatrix = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];

        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();

            if (line.contains("M")) {
                mouseRow = row;
                mouseCol = line.indexOf("M");
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("end")) {


            // Move mouse will be called with the correct mutators in each case the mutators are defining the
            // movement direction
            // UP { -1, 0}
            // DOWN { 1, 0}
            // LEFT { 0, -1}
            // RIGHT { 0, 1}

            switch (command) {
                case "up":
                    moveMouse(matrix, -1, 0);
                    break;
                case "down":
                    moveMouse(matrix, 1, 0);
                    break;
                case "left":
                    moveMouse(matrix, 0, -1);
                    break;
                case "right":
                    moveMouse(matrix, 0, 1);
                    break;
            }

            if (!mouseIsInMatrix) {
                break;
            }

            command = scanner.nextLine();
        }

        if (!mouseIsInMatrix) {
            System.out.println("Where is the mouse?");
        }

        if (eatenCheese >= 5) {
            System.out.println("Great job, the mouse is fed " + eatenCheese + " cheeses!");
        } else {
            System.out.println("The mouse couldn't eat the cheeses, she needed " +
                    (5 - eatenCheese) + " cheeses more.");
        }

        printMatrix(matrix);
    }

    /**
     * @param matrix     char[][] the mouse matrix
     * @param rowMutator int mutator that will be added to the current row of the mouse
     * @param colMutator int mutator that will be added to the current col of the mouse
     */
    private static void moveMouse(char[][] matrix, int rowMutator, int colMutator) {
        int nextRow = mouseRow + rowMutator;
        int nextCol = mouseCol + colMutator;

        boolean isBonusHit = false;

        if (!isInBounds(matrix, nextRow, nextCol)) {
            matrix[mouseRow][mouseCol] = '-';
            mouseIsInMatrix = false;
            return;
        }

        if (matrix[nextRow][nextCol] == 'c') {
            eatenCheese++;
        } else if (matrix[nextRow][nextCol] == 'B') {
            isBonusHit = true;
        }

        matrix[mouseRow][mouseCol] = '-';
        matrix[nextRow][nextCol] = 'M';
        mouseRow = nextRow;
        mouseCol = nextCol;

        if (isBonusHit) {
            // Call this same method with the same mutators again if bonus is hit
            moveMouse(matrix, rowMutator, colMutator);
        }
    }

    private static boolean isInBounds(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] arr : matrix) {
            for (char c : arr) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
