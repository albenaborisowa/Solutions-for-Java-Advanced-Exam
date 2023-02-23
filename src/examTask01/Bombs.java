package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class Bombs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // effectQueue
        // casingStack

        Deque<Integer> effectQueue = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        Deque<Integer> casingStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .forEach(casingStack::push);

        int datura = 0;
        int cherry = 0;
        int smoke = 0;
        boolean isPouchFilled = false;

        while (!effectQueue.isEmpty() && !casingStack.isEmpty()) {

            int currentEffect = effectQueue.peek();
            int currentCasing = casingStack.pop();
            int currentSum = currentCasing + currentEffect;

            if (currentSum == 40) {
                effectQueue.poll();
                datura++;
            } else if (currentSum == 60) {
                effectQueue.poll();
                cherry++;
            } else if (currentSum == 120) {
                effectQueue.poll();
                smoke++;
            } else {
                currentCasing -= 5;
                casingStack.push(currentCasing);
            }
            if (datura >= 3 && cherry >= 3 && smoke >= 3) {
                isPouchFilled = true;
                break;
            }
        }

        if (isPouchFilled) {
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        } else {
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        }

        if (effectQueue.isEmpty()) {
            System.out.println("Bomb Effects: empty");
        } else {
            String collect = effectQueue
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            System.out.println("Bomb Effects: " + collect);
        }

        if (casingStack.isEmpty()) {
            System.out.println("Bomb Casings: empty");
        } else {
            List<String> collectList = casingStack
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            System.out.println("Bomb Casings: " + String.join(", ", collectList));
        }

        Map<String, Integer> bombsMap = new TreeMap<>();
        bombsMap.put("Cherry Bombs", cherry);
        bombsMap.put("Datura Bombs", datura);
        bombsMap.put("Smoke Decoy Bombs", smoke);

        bombsMap.forEach((k, v) -> System.out.printf("%s: %d%n", k, v));
    }
}
