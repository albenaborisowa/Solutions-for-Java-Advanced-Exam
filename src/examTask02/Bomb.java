package examTask02;

import java.util.*;
import java.util.stream.Collectors;

public class Bomb {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(",");

        char[][] matrix = new char[size][size];

        int bombsTotalCount = 0;
        int sapperRow = 0;
        int sapperCol = 0;

        for (int r = 0; r < size; r++) {
            List<Character> characterList = Arrays.stream(scanner.nextLine().split(" "))
                    .map(e -> e.charAt(0))
                    .collect(Collectors.toList());

            for (int c = 0; c < characterList.size(); c++) {
                char currentChar = characterList.get(c);
                if (currentChar == 's') {
                    sapperRow = r;
                    sapperCol = c;
                } else if (currentChar == 'B') {
                    bombsTotalCount++;
                }
                matrix[r][c] = currentChar;
            }
        }

        int bombsFound = 0;

        for (int i = 0; i < commands.length; i++) {

            String currentCommand = commands[i];

            switch (currentCommand) {
                case "up":
                    if (sapperRow != 0) {
                        sapperRow--;
                    }
                    break;
                case "down":
                    if (sapperRow != size - 1) {
                        sapperRow++;
                    }
                    break;
                case "left":
                    if (sapperCol != 0) {
                        sapperCol--;
                    }
                    break;
                case "right":
                    if (sapperCol != size - 1) {
                        sapperCol++;
                    }
                    break;
            }

            if (matrix[sapperRow][sapperCol] == 'B') {
                System.out.println("You found a bomb!");
                matrix[sapperRow][sapperCol] = '+';
                bombsFound++;
                if (bombsFound == bombsTotalCount) {
                    System.out.println("Congratulations! You found all bombs!");
                    return;
                }
            } else if (matrix[sapperRow][sapperCol] == 'e') {
                System.out.printf("END! %d bombs left on the field%n", bombsTotalCount - bombsFound);
                return;
            }
        }
        System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)",
                bombsTotalCount - bombsFound, sapperRow, sapperCol);
    }
}
