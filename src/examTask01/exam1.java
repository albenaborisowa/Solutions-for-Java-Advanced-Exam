package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class exam1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> caffeineStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .forEach(caffeineStack::push);

        Deque<Integer> energyQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .forEach(energyQueue::offer);

        int totalCaffeineDrunk = 0;

        while (!caffeineStack.isEmpty() && !energyQueue.isEmpty()) {

            int currentCaffeine = caffeineStack.pop();
            int currentEnergy = energyQueue.peek();
            int caffeineInDrink = currentCaffeine * currentEnergy;

            if (totalCaffeineDrunk + caffeineInDrink <= 300) {
                energyQueue.poll();
                totalCaffeineDrunk += caffeineInDrink;
            } else {
                totalCaffeineDrunk -= 30;
                if (energyQueue.size() > 1) {
                    energyQueue.poll();
                    energyQueue.offer(currentEnergy);
                }

                if (totalCaffeineDrunk < 0) {
                    totalCaffeineDrunk = 0;
                }
            }
        }
        if (energyQueue.isEmpty()) {
            System.out.println("At least Stamat wasn't exceeding the maximum caffeine.");
        } else {
            String energyLeft = energyQueue
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            System.out.println("Drinks left: " + energyLeft);
        }
        System.out.printf("Stamat is going to sleep with %d mg caffeine.", totalCaffeineDrunk);
    }
}
