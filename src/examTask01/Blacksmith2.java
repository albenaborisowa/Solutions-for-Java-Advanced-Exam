package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class BlacksmithOtSite {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> steelQueue = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        Deque<Integer> carbonStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .forEach(carbonStack::push);

        Map<String, Integer> swords = new TreeMap<>();
        swords.put("Sabre", 0);
        swords.put("Katana", 0);
        swords.put("Shamshir", 0);
        swords.put("Gladius", 0);

        while (!steelQueue.isEmpty() && !carbonStack.isEmpty()) {
            int firstSt = steelQueue.poll();
            int firstCarb = carbonStack.pop();

            if (firstCarb + firstSt == 70) {
                swords.put("Gladius", swords.get("Gladius") + 1);

            } else if (firstCarb + firstSt == 80) {
                swords.put("Shamshir", swords.get("Shamshir") + 1);

            } else if (firstCarb + firstSt == 90) {
                swords.put("Katana", swords.get("Katana") + 1);

            } else if (firstCarb + firstSt == 110) {
                swords.put("Sabre", swords.get("Sabre") + 1);

            } else {
                carbonStack.push(firstCarb + 5);

            }
        }
        int sum = swords.values().stream().mapToInt(Integer::intValue).sum();

        if (sum != 0) {
            System.out.printf("You have forged %d swords.%n", sum);
        } else {
            System.out.println("You did not have enough resources to forge a sword.");
        }

        if (steelQueue.isEmpty()) {
            System.out.println("Steel left: none");
        } else {
            String collect = steelQueue.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            System.out.println("Steel left: " + collect);
        }

        if (carbonStack.isEmpty()) {
            System.out.println("Carbon left: none");
        } else {
            List<String> collect = carbonStack.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            System.out.println("Carbon left: " + String.join(", ", collect));
        }

        swords.entrySet().stream()
                .filter(e -> e.getValue() != 0)
                .forEach(el -> System.out.println(el.getKey() + ": " + el.getValue()));
    }
}

