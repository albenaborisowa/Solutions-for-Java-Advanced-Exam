package examTask02;

import java.util.*;

public class StickyFingers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(",");

        String[][] matrix = new String[size][size];
        int dillingerRow = -1;
        int dillingerCol = -1;

        for (int row = 0; row < size; row++) {
            String[] line = scanner.nextLine().split(" ");
            for (int col = 0; col < size; col++) {
                matrix[row][col] = line[col];
                if (matrix[row][col].equals("D")) {
                    dillingerRow = row;
                    dillingerCol = col;
                }
            }
        }
        int money = 0;
        boolean gotCough = false;

        for (int i = 0; i < commands.length; i++) {

            switch (commands[i]) {
                case "up":
                    if (dillingerRow - 1 >= 0) {
                        matrix[dillingerRow][dillingerCol] = "+";
                        dillingerRow--;
                    } else {
                        System.out.println("You cannot leave the town, there is police outside!");
                    }
                    break;
                case "down":
                    if (dillingerRow + 1 < size) {
                        matrix[dillingerRow][dillingerCol] = "+";
                        dillingerRow++;
                    } else {
                        System.out.println("You cannot leave the town, there is police outside!");
                    }
                    break;
                case "left":
                    if (dillingerCol - 1 >= 0) {
                        matrix[dillingerRow][dillingerCol] = "+";
                        dillingerCol--;
                    } else {
                        System.out.println("You cannot leave the town, there is police outside!");
                    }
                    break;
                case "right":
                    if (dillingerCol + 1 < size) {
                        matrix[dillingerRow][dillingerCol] = "+";
                        dillingerCol++;
                    } else {
                        System.out.println("You cannot leave the town, there is police outside!");
                    }
                    break;
            }
            if (matrix[dillingerRow][dillingerCol].equals("$")) {
                money += dillingerRow * dillingerCol;
                System.out.printf("You successfully stole %d$.%n", dillingerRow * dillingerCol);
            }
            if (matrix[dillingerRow][dillingerCol].equals("P")) {
                matrix[dillingerRow][dillingerCol] = "#";
                System.out.printf("You got caught with %d$, and you are going to jail.%n", money);
                gotCough = true;
                break;
            }
            matrix[dillingerRow][dillingerCol] = "D";
        }
        if (!gotCough) {
            System.out.printf("Your last theft has finished successfully with %d$ in your pocket.%n", money);
        }
        for (int row = 0; row < size; row++) {
            System.out.println(String.join(" ", matrix[row]));
        }
    }
}
