package examTask01;

import java.util.*;

public class KAT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long[] licensePlates = Arrays.stream(scanner.nextLine().split(", "))
                .mapToLong(Long::parseLong)
                .toArray();
        long[] cars = Arrays.stream(scanner.nextLine().split(", "))
                .mapToLong(Long::parseLong)
                .toArray();
        Deque<Long> platesQueue = new ArrayDeque<>();
        Deque<Long> carsStack = new ArrayDeque<>();

        for (int i = 0; i < cars.length; i++) {
            carsStack.push(cars[i]);
        }
        for (int i = 0; i < licensePlates.length; i++) {
            platesQueue.offer(licensePlates[i]);
        }

        long registeredCars = 0;
        long days = 0;

        while (!carsStack.isEmpty() || !platesQueue.isEmpty()) {
            if (carsStack.isEmpty()) {
                break;
            } else if (platesQueue.isEmpty()) {
                break;
            }
            days++;
            long currentCars = carsStack.peek();
            long currentPlates = platesQueue.peek();
            long carsCanBeRegistered = currentPlates / 2;
            carsStack.pop();
            platesQueue.poll();

            if (carsCanBeRegistered > currentCars) {
                long remainingPlates = currentPlates - currentCars * 2;
                platesQueue.addLast(remainingPlates);
                registeredCars += currentCars;
            } else if (carsCanBeRegistered == currentCars) {
                registeredCars += currentCars;
            } else {
                carsStack.addLast((currentCars - carsCanBeRegistered));
                registeredCars += carsCanBeRegistered;
            }
        }

        System.out.printf("%d cars were registered for %d days!%n", registeredCars, days);
        if (carsStack.isEmpty() && platesQueue.isEmpty()) {
            System.out.println("Good job! There is no queue in front of the KAT!");
        }
        if (!platesQueue.isEmpty()) {
            long platesSum = 0;
            while (!platesQueue.isEmpty()) {
                platesSum += platesQueue.poll();
            }
            System.out.printf("%d license plates remain!", platesSum);
        } else if (!carsStack.isEmpty()) {
            long carsSum = 0;
            while (!carsStack.isEmpty()) {
                carsSum += carsStack.pop();
            }
            System.out.printf("%d cars remain without license plates!", carsSum);
        }
    }
}
