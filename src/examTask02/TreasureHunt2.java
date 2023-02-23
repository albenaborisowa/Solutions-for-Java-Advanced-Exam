package examTask02;

import java.util.*;

public class TreasureHunt_OtAvtora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int[] size = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = size[0];
        int cols = size[1];

        char[][] matrix = readMatrix(scanner, rows);
        String[] commands = readDirections(scanner);
        int[] playersPosition = findPlayersPosition(matrix);
        int playerRow = playersPosition[0];
        int playerCol = playersPosition[1];

        List<String> movementList = new LinkedList<>();

        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            switch (command) {
                case "up":
                    if (playerRow - 1 >= 0 && matrix[playerRow - 1][playerCol] != 'T') {
                        playerRow--;
                        movementList.add(command);
                    }
                    break;
                case "down":
                    if (playerRow + 1 < rows && matrix[playerRow + 1][playerCol] != 'T') {
                        playerRow++;
                        movementList.add(command);
                    }
                    break;
                case "left":
                    if (playerCol - 1 >= 0 && matrix[playerRow][playerCol - 1] != 'T') {
                        playerCol--;
                        movementList.add(command);
                    }
                    break;
                case "right":
                    if (playerCol + 1 < cols && matrix[playerRow][playerCol + 1] != 'T') {
                        playerCol++;
                        movementList.add(command);
                    }
                    break;
            }
            if (checkPosition(matrix, playerRow, playerCol, movementList)) {
                return;
            }
        }
        System.out.println("The map is fake!");
    }

    private static boolean checkPosition(char[][] matrix, int playerRow, int playerCol, List<String> movementList) {
        if (matrix[playerRow][playerCol] == 'X') {
            System.out.println("I've found the treasure!");
            System.out.println("The right path is " + String.join(", ", movementList));
            return true;
        }
        return false;
    }

    private static int[] findPlayersPosition(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'Y') {
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{0};
    }

    private static String[] readDirections(Scanner scanner) {
        String command = scanner.nextLine();
        List<String> list = new LinkedList<>();
        while (!command.equals("Finish")) {
            list.add(command);
            command = scanner.nextLine();
        }
        return list.toArray(String[]::new);
    }

    private static char[][] readMatrix(Scanner scanner, int rows) {
        char[][] matrix = new char[rows][];
        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().replace(" ", "").toCharArray();
        }
        return matrix;
    }
}
