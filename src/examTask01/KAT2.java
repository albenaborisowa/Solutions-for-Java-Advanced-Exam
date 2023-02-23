package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class KATotSite {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Long> platesQueue = Arrays.stream(scanner.nextLine().split(", "))
                .map(Long::parseLong)
                .collect(Collectors.toCollection(ArrayDeque::new));
        Deque<Long> carsStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Long::parseLong)
                .forEach(carsStack::push);

        long registeredCars = 0;
        long days = 0;
        while (!platesQueue.isEmpty() && !carsStack.isEmpty()) {
            days++;
            long currentPlates = 0;
            for (int i = 0; i < 1; i++) {
                currentPlates = platesQueue.poll();
            }

            long currentCars = 0;
            for (int i = 0; i < 1; i++) {
                currentCars = carsStack.pop();
            }

            if (currentPlates > (currentCars * 2)) {
                platesQueue.addLast(currentPlates - (currentCars * 2));
                registeredCars += currentCars;
            } else if (currentPlates < (currentCars * 2)) {
                long leftCars = currentCars - (currentPlates / 2);
                carsStack.addLast(leftCars);

                registeredCars += (currentPlates / 2);
            } else {
                registeredCars += currentCars;
            }
        }

        System.out.printf("%d cars were registered for %d days!%n" ,registeredCars ,days) ;
        if (!platesQueue.isEmpty()) {
            long sumPlates = 0;
            while (!platesQueue.isEmpty()){
                sumPlates += platesQueue.poll();
            }
            System.out.printf("%d license plates remain!" ,sumPlates);
        } else if (!carsStack.isEmpty()){
            long sumCars = 0;
            while (!carsStack.isEmpty()) {
                sumCars += carsStack.pop();
            }
            System.out.printf("%d cars remain without license plates!" ,sumCars);
        } else {
            System.out.println("Good job! There is no queue in front of the KAT!");
        }
    }
}
