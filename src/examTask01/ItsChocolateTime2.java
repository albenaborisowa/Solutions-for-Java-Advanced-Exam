package examTask01;

import java.util.*;

public class ItsChocolateTimeOtSitePrerabotenoMalko {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double[] milk = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();
        double[] cacao = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();
        Map<String, Integer> chocolateMap = new LinkedHashMap<>();
        chocolateMap.put("Baking Chocolate", 0);
        chocolateMap.put("Dark Chocolate", 0);
        chocolateMap.put("Milk Chocolate", 0);

        Deque<Double> milkQueue = new ArrayDeque<>();
        for (double v : milk) {
            milkQueue.offer(v);
        }
        Deque<Double> cacaoStack = new ArrayDeque<>();
        for (double v : cacao) {
            cacaoStack.push(v);
        }
        while (!milkQueue.isEmpty() && !cacaoStack.isEmpty()) {
            double currentMilk = milkQueue.peek();
            double currentCacao = cacaoStack.peek();
            double cacaoPercentage = currentCacao / (currentMilk + currentCacao) * 100;

            if (cacaoPercentage == 30) {
                int currentNumOfMilkChocolates = chocolateMap.get("Milk Chocolate");
                chocolateMap.put("Milk Chocolate", currentNumOfMilkChocolates + 1);
                milkQueue.poll();
                cacaoStack.pop();

            } else if (cacaoPercentage == 50) {
                int currentNumOfDarkChocolates = chocolateMap.get("Dark Chocolate");
                chocolateMap.put("Dark Chocolate", currentNumOfDarkChocolates + 1);
                milkQueue.poll();
                cacaoStack.pop();

            } else if (cacaoPercentage == 100) {
                int currentNumOfBakingChocolates = chocolateMap.get("Baking Chocolate");
                chocolateMap.put("Baking Chocolate", currentNumOfBakingChocolates + 1);
                milkQueue.poll();
                cacaoStack.pop();

            } else {
                cacaoStack.pop();
                currentMilk += 10;
                milkQueue.poll();
                milkQueue.offer(currentMilk);

            }
        }
        boolean isChocolateEmpty = false;
        for (Map.Entry<String, Integer> entry : chocolateMap.entrySet()) {
            if (entry.getValue() == 0) {
                System.out.println("Sorry, but you didn't succeed to prepare all types of chocolates.");
                isChocolateEmpty = true;
                break;
            }
        }
        if (!isChocolateEmpty) {
            System.out.println("It’s a Chocolate Time. All chocolate types are prepared.");
        }

        chocolateMap.forEach((key, value) -> {
            if (value > 0) {
                System.out.printf("# %s --> %d%n", key, value);
            }
        });
    }
}
