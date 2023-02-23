package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class ItsChocolateTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // first Milk + last Cacao = chocolate type (milk - Queue, cacao - Stack)
        // milk chocolate   - 30% cacao
        // dark chocolate   - 50% cacao
        // baking chocolate -100% cacao
        // % cacao = cacao / (milk + cacao)
        // ako отговаря на 30, 50 или 100 правим шоколад и махаме млякото и какаото
        // ако не  - махаме какаото, мляко +10, махаме го от 1ва позиция и го добавяме на края
        // спираме да правим шоколад ако свърши млякото или какаото
        // задачата е успешна ако сме направили поне 3 шоколада, по 1 от всеки вид

        Deque<Double> milkQueue = Arrays.stream(scanner.nextLine().split(" "))
                .map(Double::parseDouble)
                .collect(Collectors.toCollection(ArrayDeque::new));

        Deque<Double> cacaoStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .mapToDouble(Double::parseDouble)
                .forEach(cacaoStack::push);

        int bakingChocolate = 0;
        int darkChocolate = 0;
        int milkChocolate = 0;

        while (!milkQueue.isEmpty() && !cacaoStack.isEmpty()) {

            double currentMilk = milkQueue.peek();
            double currentCacao = cacaoStack.peek();
            double cacaoPercent = currentCacao / (currentMilk + currentCacao) * 100;

            if (cacaoPercent == 100) {
                milkQueue.poll();
                cacaoStack.pop();
                bakingChocolate++;

            } else if (cacaoPercent == 50) {
                milkQueue.poll();
                cacaoStack.pop();
                darkChocolate++;

            } else if (cacaoPercent == 30) {
                milkQueue.poll();
                cacaoStack.pop();
                milkChocolate++;

            } else {
                cacaoStack.pop();
                currentMilk += 10;
                milkQueue.poll();
                milkQueue.offer(currentMilk);
            }
        }
        Map<String, Integer> chocolateMap = new LinkedHashMap<>();
        chocolateMap.put("Baking Chocolate", bakingChocolate);
        chocolateMap.put("Dark Chocolate", darkChocolate);
        chocolateMap.put("Milk Chocolate", milkChocolate);

        boolean chocolateMapIsEmpty = false;

        for (Map.Entry<String, Integer> entry : chocolateMap.entrySet()) {
            if (entry.getValue() == 0) {
                System.out.println("Sorry, but you didn't succeed to prepare all types of chocolates.");
                chocolateMapIsEmpty = true;
                break;
            }
        }
        if (!chocolateMapIsEmpty) {
            System.out.println("It’s a Chocolate Time. All chocolate types are prepared.");
        }
        chocolateMap.forEach((key, value) -> {
            if (value > 0) {
                System.out.printf("# %s --> %d%n", key, value);
            }
        });
    }
}
