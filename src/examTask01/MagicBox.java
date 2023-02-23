package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class MagicBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // firstBoxQueue
        // secondBoxStack

        Deque<Integer> firstBoxQueue = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        Deque<Integer> secondBoxStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .forEach(secondBoxStack::push);

        List<Integer> collectedItemsList = new ArrayList<>();

        while (!firstBoxQueue.isEmpty() && !secondBoxStack.isEmpty()) {

            int currentItemFirstBox = firstBoxQueue.peek();
            int currentItemSecondBox = secondBoxStack.pop();
            int currentSum = currentItemFirstBox + currentItemSecondBox;

            if (currentSum % 2 == 0) {
                firstBoxQueue.poll();
                collectedItemsList.add(currentSum);
            } else {
                firstBoxQueue.offer(currentItemFirstBox);
            }
        }
        if (firstBoxQueue.isEmpty()) {
            System.out.println("First magic box is empty.");
        } else {
            System.out.println("Second magic box is empty.");
        }

        int totalSum = collectedItemsList
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        if (totalSum >= 90) {
            System.out.printf("Wow, your prey was epic! Value: %d", totalSum);
        } else {
            System.out.printf("Poor prey... Value: %d", totalSum);
        }
    }
}
