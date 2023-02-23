package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class AutumnCocktailsNewThings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> ingredientsQueue = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> freshnessStack = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        Map<String, Integer> cocktailsMap = Map.of(
                "Pear Sour", 150,
                "The Harvest", 250,
                "Apple Hinny", 300,
                "High Fashion", 400
        );

        Map<String, Integer> cocktailsQuantityMap = new TreeMap<>();

        while (!ingredientsQueue.isEmpty() && !freshnessStack.isEmpty()) {
            int ingredient = ingredientsQueue.removeFirst();
            if (ingredient == 0) {
                continue;
            }

            int freshness = freshnessStack.removeLast();
            int totalFreshness = ingredient * freshness;

            Optional<String> cocktailOpt = cocktailsMap.entrySet().stream()
                    .filter(kvp -> kvp.getValue() == totalFreshness)
                    .map(Map.Entry::getKey)
                    .findFirst();

            if (cocktailOpt.isPresent()) {
                cocktailsQuantityMap.merge(cocktailOpt.get(), 1, (k, ignored) -> k + 1);
            } else {
                ingredient = ingredient + 5;
                ingredientsQueue.addLast(ingredient);
            }
        }

        if (cocktailsQuantityMap.size() == 4) {
            System.out.println("It's party time! The cocktails are ready!");
        } else {
            System.out.println("What a pity! You didn't manage to prepare all cocktails.");
        }


        int remainingIngredientsSum = ingredientsQueue.stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (remainingIngredientsSum > 0) {
            System.out.printf("Ingredients left: %d%n", remainingIngredientsSum);
        }

        cocktailsQuantityMap.entrySet().stream()
                .map(kvp -> String.format(" # %s --> %d", kvp.getKey(), kvp.getValue()))
                .forEach(System.out::println);
    }
}
