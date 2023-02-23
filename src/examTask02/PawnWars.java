package examTask02;

import java.util.*;
import java.util.stream.Collectors;

public class PawnWars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] matrix = new char[8][8];

        int whiteRow = -1;
        int whiteCol = -1;
        int blackRow = -1;
        int blackCol = -1;

        for (int row = 0; row < 8; row++) {
            List<Character> characterList = Arrays.stream(scanner.nextLine().split(""))
                    .map(e -> e.charAt(0))
                    .collect(Collectors.toList());

            for (int col = 0; col < characterList.size(); col++) {
                char currentChar = characterList.get(col);
                if (currentChar == 'w') {
                    whiteRow = row;
                    whiteCol = col;
                } else if (currentChar == 'b') {
                    blackRow = row;
                    blackCol = col;
                }
                matrix[row][col] = currentChar;
            }
        }

        boolean isHit = false;

        while (whiteRow != 0 && blackRow != 7 && !isHit) {

            if (whiteHitBlack(whiteRow, whiteCol, blackRow, blackCol)) {
                System.out.printf("Game over! White capture on %s.", coordinates(blackRow, blackCol));
                isHit = true;
            }
            whiteRow--;

            if (blackHitWhite(whiteRow, whiteCol, blackRow, blackCol)) {
                System.out.printf("Game over! Black capture on %s.", coordinates(whiteRow, whiteCol));
                isHit = true;
            }
            blackRow++;

        }
        if (!isHit) {
            if (whiteRow == 0) {
                System.out.printf("Game over! White pawn is promoted to a queen at %s.",
                        coordinates(whiteRow, whiteCol));
            } else {
                System.out.printf("Game over! Black pawn is promoted to a queen at %s.",
                        coordinates(blackRow, blackCol));
            }
        }
    }

    private static boolean blackHitWhite(int whiteRow, int whiteCol, int blackRow, int blackCol) {
        return blackRow + 1 == whiteRow && (blackCol + 1 == whiteCol || blackCol - 1 == whiteCol);
    }

    private static boolean whiteHitBlack(int whiteRow, int whiteCol, int blackRow, int blackCol) {
        return whiteRow - 1 == blackRow && (whiteCol + 1 == blackCol || whiteCol - 1 == blackCol);
    }

    private static String coordinates(int row, int col) {
        char[] rowArr = new char[]{'8', '7', '6', '5', '4', '3', '2', '1'};
        char[] colArr = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        return String.valueOf(colArr[col]) + rowArr[row];
    }
}
