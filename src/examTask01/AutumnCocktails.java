package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class AutumnCocktails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ingredients Queue
        // freshness Stack

        Deque<Integer> ingredientsQueue = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        Deque<Integer> freshnessStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .forEach(freshnessStack::push);

        int appleHinny = 0;     //300
        int highFashion = 0;    //400
        int pearSour = 0;       //150
        int theHarvest = 0;     //250

        while (!ingredientsQueue.isEmpty() && !freshnessStack.isEmpty()) {

            int currentIngredient = ingredientsQueue.poll();
            if (currentIngredient == 0) {
                continue;
            }
            int currentFreshness = freshnessStack.pop();

            int totalFreshness = currentFreshness * currentIngredient;

            if (totalFreshness == 300) {
                appleHinny++;

            } else if (totalFreshness == 400) {
                highFashion++;

            } else if (totalFreshness == 150) {
                pearSour++;

            } else if (totalFreshness == 250) {
                theHarvest++;

            } else {
                currentIngredient += 5;
                ingredientsQueue.offer(currentIngredient);
            }
        }
        Map<String, Integer> cocktailsMap = new TreeMap<>();
        cocktailsMap.put("Pear Sour", pearSour);
        cocktailsMap.put("The Harvest", theHarvest);
        cocktailsMap.put("Apple Hinny", appleHinny);
        cocktailsMap.put("High Fashion", highFashion);

        boolean cocktailsAllDone = false;

        for (Map.Entry<String, Integer> entry : cocktailsMap.entrySet()) {
            if (entry.getValue() == 0) {
                System.out.println("What a pity! You didn't manage to prepare all cocktails.");
                cocktailsAllDone = true;
                break;
            }
        }
        if (!cocktailsAllDone) {
            System.out.println("It's party time! The cocktails are ready!");
        }

        int remainingIngredientsSum = ingredientsQueue.stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (remainingIngredientsSum > 0) {
            System.out.printf("Ingredients left: %d%n", remainingIngredientsSum);
        }
        cocktailsMap.forEach((key, value) -> {
            if (value > 0) {
                System.out.printf("# %s --> %d%n", key, value);
            }
        });
    }
}
