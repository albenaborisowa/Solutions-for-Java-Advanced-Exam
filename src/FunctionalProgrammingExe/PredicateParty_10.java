package FunctionalProgrammingExe;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateParty_10 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<String> guests = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());
        String input = scanner.nextLine();

        while (!"Party!".equals(input)) {
            String[] data = input.split("\\s+");
            guests = getUpdated(guests, data);
            input = scanner.nextLine();
        }

        printResults(guests);
    }

    private static List<String> getUpdated(List<String> guests, String[] data) {
        String command = data[0];
        String condition = data[1];
        String value = data[2];

        switch (command) {
            case "Double":
                ArrayList<String> updatedList = new ArrayList<>(guests);
                updatedList.addAll(guests.stream()
                        .filter(getStringPredicate(condition, value))
                        .collect(Collectors.toList()));
                return updatedList;

            case "Remove":
                return guests.stream()
                        .filter(x -> !getStringPredicate(condition, value).test(x))
                        .collect(Collectors.toList());

            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }

    private static Predicate<String> getStringPredicate(String condition, String value) {

        switch (condition) {
            case "StartsWith":
                return s -> s.startsWith(value);
            case "EndsWith":
                return s -> s.endsWith(value);
            case "Length":
                return s -> s.length() == Integer.parseInt(value);
            default:
                throw new IllegalStateException("Unexpected value: " + condition);
        }
    }

    private static void printResults(List<String> guests) {

        if ((guests.isEmpty())) {
            System.out.println("Nobody is going to the party!");
        } else {
            System.out.printf("%s are going to the party!%n", guests.stream()
                    .sorted()
                    .collect(Collectors.joining(", ")));
        }
    }
}
