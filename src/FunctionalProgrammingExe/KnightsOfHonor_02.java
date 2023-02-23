package FunctionalProgrammingExe;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class KnightsOfHonor_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> names = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        Consumer<String> consumer = name -> System.out.printf("Sir %s%n", name);

        names.forEach(consumer);
    }
}
