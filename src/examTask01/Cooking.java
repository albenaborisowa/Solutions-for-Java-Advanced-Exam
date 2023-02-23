package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class Cooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // liquidsQueue
        // ingredientsStack

        Deque<Integer> liquidsQueue = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        Deque<Integer> ingredientsStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .forEach(ingredientsStack::push);

        int bread = 0;
        int cake = 0;
        int pastry = 0;
        int fruitPie = 0;

        while (!liquidsQueue.isEmpty() && !ingredientsStack.isEmpty()) {

            int currentLiquid = liquidsQueue.poll();
            int currentIngredient = ingredientsStack.pop();
            int mixedSum = currentLiquid + currentIngredient;

            if (mixedSum == 25) {
                bread++;

            } else if (mixedSum == 50) {
                cake++;

            } else if (mixedSum == 75) {
                pastry++;

            } else if (mixedSum == 100) {
                fruitPie++;

            } else {
                currentIngredient += 3;
                ingredientsStack.push(currentIngredient);
            }
        }
        Map<String, Integer> sweetsMadeMap = new LinkedHashMap<>();
        sweetsMadeMap.put("Bread", bread);
        sweetsMadeMap.put("Cake", cake);
        sweetsMadeMap.put("Fruit Pie", fruitPie);
        sweetsMadeMap.put("Pastry", pastry);

        if (bread > 0 && cake > 0 && fruitPie > 0 && pastry > 0) {
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        } else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to cook everything.");
        }

        if (liquidsQueue.isEmpty()) {
            System.out.println("Liquids left: none");
        } else {
            String collectLiquids = liquidsQueue
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            System.out.println("Liquids left: " + collectLiquids);
        }
        if (ingredientsStack.isEmpty()) {
            System.out.println("Ingredients left: none");
        } else {
            List<String> collectIngredientsList = ingredientsStack
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            System.out.println("Ingredients left: " + String.join(", ", collectIngredientsList));
        }
        sweetsMadeMap.forEach((k, v) -> System.out.printf("%s: %d%n", k, v));
    }
}
