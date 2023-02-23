package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class FlowerWreaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // lilies Stack
        // roses Queue

        Deque<Integer> liliesStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .forEach(liliesStack::push);

        Deque<Integer> rosesQueue = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        List<Integer> storedWreaths = new ArrayList<>();
        int wreathsCount = 0;

        while (!liliesStack.isEmpty() && !rosesQueue.isEmpty()) {

            int currentLilies = liliesStack.peek();
            int currentRoses = rosesQueue.peek();
            int currentWreath = currentLilies + currentRoses;

            if (currentWreath == 15) {
                liliesStack.pop();
                rosesQueue.poll();
                wreathsCount++;

            } else if (currentWreath > 15) {
                while (currentWreath > 15) {
                    currentLilies -= 2;
                    currentWreath -= 2;
                    if (currentWreath == 15) {
                        liliesStack.pop();
                        rosesQueue.poll();
                        wreathsCount++;
                    } else if (currentWreath < 15) {
                        liliesStack.pop();
                        rosesQueue.poll();
                        storedWreaths.add(currentWreath);
                    }
                }

            } else {
                liliesStack.pop();
                rosesQueue.poll();
                storedWreaths.add(currentWreath);
            }
        }

        if (!storedWreaths.isEmpty()) {
            int totalFlowers = storedWreaths.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            wreathsCount += totalFlowers / 15;
        }

        if (wreathsCount >= 5) {
            System.out.printf("You made it, you are going to the competition with %d wreaths!", wreathsCount);
        } else {
            System.out.printf("You didn't make it, you need %d wreaths more!", 5 - wreathsCount);
        }
    }
}
