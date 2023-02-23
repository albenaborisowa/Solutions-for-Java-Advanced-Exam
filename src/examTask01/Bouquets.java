package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class Bouquets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // tulips Stack
        // daffodils Queue

        Deque<Integer> tulipsStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .forEach(tulipsStack::push);

        Deque<Integer> daffodilsQueue = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        List<Integer> storedBouquets = new ArrayList<>();
        int bouquetsCount = 0;

        while (!tulipsStack.isEmpty() && !daffodilsQueue.isEmpty()) {

            int currentTulips = tulipsStack.peek();
            int currentDaffodils = daffodilsQueue.peek();
            int currentBouquet = currentTulips + currentDaffodils;

            if (currentBouquet == 15) {
                tulipsStack.pop();
                daffodilsQueue.poll();
                bouquetsCount++;

            } else if (currentBouquet > 15) {
                while (currentBouquet > 15) {
                    currentTulips -= 2;
                    currentBouquet -= 2;
                    if (currentBouquet == 15) {
                        tulipsStack.pop();
                        daffodilsQueue.poll();
                        bouquetsCount++;
                    } else if (currentBouquet < 15) {
                        tulipsStack.pop();
                        daffodilsQueue.poll();
                        storedBouquets.add(currentBouquet);
                    }
                }

            } else {
                tulipsStack.pop();
                daffodilsQueue.poll();
                storedBouquets.add(currentBouquet);
            }
        }

        if (!storedBouquets.isEmpty()) {
            int totalFlowers = storedBouquets.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            bouquetsCount += totalFlowers / 15;
        }

        if (bouquetsCount >= 5) {
            System.out.printf("You made it! You go to the competition with %d bouquets!", bouquetsCount);
        } else {
            System.out.printf("You failed... You need more %d bouquets.", 5 - bouquetsCount);
        }
    }
}
