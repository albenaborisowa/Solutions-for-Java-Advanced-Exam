package FunctionalProgrammingLab;

import java.util.*;
import java.util.function.Function;

public class SumNumbers_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Function<int[], String> count = elements -> "Count = " +
                Arrays.stream(elements).count();
        Function<int[], String> sum = elements -> "Sum = " +
                Arrays.stream(elements).sum();

        int[] numbers = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(count.apply(numbers));
        System.out.println(sum.apply(numbers));
    }
}
