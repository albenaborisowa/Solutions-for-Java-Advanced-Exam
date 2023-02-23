package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class SantaPresentFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // materialsStack
        // magicQueue

        Deque<Integer> materialStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .forEach(materialStack::push);

        Deque<Integer> magicQueue = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int doll = 0;   //150
        int train = 0;  //250
        int bear = 0;    //300
        int bicycle = 0;  //400

        while (!magicQueue.isEmpty() && !materialStack.isEmpty()) {

            int currentMaterial = materialStack.peek();
            int currentMagic = magicQueue.peek();
            if (currentMaterial == 0 || currentMagic == 0) {
                if (currentMaterial == 0) {
                    materialStack.pop();
                }
                if (currentMagic == 0) {
                    magicQueue.poll();
                }
                continue;
            }

            int currentMultiplication = currentMaterial * currentMagic;
            materialStack.pop();
            magicQueue.poll();

            if (currentMultiplication < 0) {
                materialStack.push(currentMaterial + currentMagic);
            } else if (currentMultiplication == 150) {
                doll++;
            } else if (currentMultiplication == 250) {
                train++;
            } else if (currentMultiplication == 300) {
                bear++;
            } else if (currentMultiplication == 400) {
                bicycle++;
            } else if (currentMultiplication > 0) {
                materialStack.push(currentMaterial + 15);
            }
        }
        if ((doll > 0 && train > 0) || (bear > 0 && bicycle > 0)) {
            System.out.println("The presents are crafted! Merry Christmas!");
        } else {
            System.out.println("No presents this Christmas!");
        }
        if (!materialStack.isEmpty()) {
            List<String> collectList = materialStack
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            System.out.println("Materials left: " + String.join(", ", collectList));
        }
        if (!magicQueue.isEmpty()) {
            String collect = magicQueue
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            System.out.println("Magic left: " + collect);
        }
        Map<String, Integer> craftedPresentsMap = new TreeMap<>();
        craftedPresentsMap.put("Doll", doll);
        craftedPresentsMap.put("Wooden train", train);
        craftedPresentsMap.put("Teddy bear", bear);
        craftedPresentsMap.put("Bicycle", bicycle);

        craftedPresentsMap.forEach((k, v) -> {
            if (v > 0) {
                System.out.printf("%s: %d%n", k, v);
            }
        });

    }
}
