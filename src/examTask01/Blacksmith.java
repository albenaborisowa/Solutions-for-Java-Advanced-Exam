package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class Blacksmith {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // steel Queue
        // carbon Stack

        Deque<Integer> steelQueue = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        Deque<Integer> carbonStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .forEach(carbonStack::push);

        int gladius = 0;    //70
        int katana = 0;     //90
        int sabre = 0;      //110
        int shamshir = 0;   //80

        while (!steelQueue.isEmpty() && !carbonStack.isEmpty()) {

            int currentSteel = steelQueue.poll();
            int currentCarbon = carbonStack.pop();
            int mixedSum = currentSteel + currentCarbon;

            if (mixedSum == 70) {
                gladius++;

            } else if (mixedSum == 90) {
                katana++;

            } else if (mixedSum == 110) {
                sabre++;

            } else if (mixedSum == 80) {
                shamshir++;

            } else {
                currentCarbon += 5;
                carbonStack.push(currentCarbon);
            }
        }
        Map<String, Integer> weaponsMap = new LinkedHashMap<>();
        weaponsMap.put("Gladius", gladius);
        weaponsMap.put("Katana", katana);
        weaponsMap.put("Sabre", sabre);
        weaponsMap.put("Shamshir", shamshir);

        int totalWeapons = gladius + katana + sabre + shamshir;

        if (totalWeapons > 0) {
            System.out.printf("You have forged %d swords.%n", totalWeapons);
        } else {
            System.out.println("You did not have enough resources to forge a sword.");
        }
        if (steelQueue.isEmpty()) {
            System.out.println("Steel left: none");
        } else {
            String collect = steelQueue
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            System.out.println("Steel left: " + collect);
        }
        if (carbonStack.isEmpty()) {
            System.out.println("Carbon left: none");
        } else {
            List<String> collectList = carbonStack
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            System.out.println("Carbon left: " + String.join(", ", collectList));
        }
        if (totalWeapons > 0) {
            weaponsMap.forEach((k, v) -> {
                if (v > 0) {
                    System.out.printf("%s: %d%n", k, v);
                }
            });
        }
    }
}