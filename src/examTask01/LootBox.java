package examTask01;

import java.util.*;

public class LootBox {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // firstBoxQueue
        // secondBoxStack

        Deque<Integer> firstBoxQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(firstBoxQueue::offer);

        Deque<Integer> secondBoxStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(secondBoxStack::push);

        int loot = 0;

        while (!firstBoxQueue.isEmpty() && !secondBoxStack.isEmpty()) {
            int firstElement = firstBoxQueue.peek();
            int secondElement = secondBoxStack.pop();

            int sum = firstElement + secondElement;

            if (sum % 2 == 0) {
                firstBoxQueue.poll();
                loot += sum;
            } else {
                firstBoxQueue.offer(secondElement);
            }
        }
        if (firstBoxQueue.isEmpty()) {
            System.out.println("First lootbox is empty");
        } else {
            System.out.println("Second lootbox is empty");
        }
        if (loot >= 100) {
            System.out.printf("Your loot was epic! Value: %d", loot);
        } else {
            System.out.printf("Your loot was poor... Value: %d", loot);
        }
    }
}
