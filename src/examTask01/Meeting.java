package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class Meeting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // males Stack
        // females Queue

        Deque<Integer> malesStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .forEach(malesStack::push);

        Deque<Integer> femalesQueue = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int matches = 0;

        while (!malesStack.isEmpty() && !femalesQueue.isEmpty()) {

            int currentFemale = femalesQueue.peek();
            int currentMale = malesStack.peek();

            if (currentFemale <= 0) {
                femalesQueue.poll();
                continue;
            } else if (currentMale <= 0) {
                malesStack.pop();
                continue;
            }

            if (currentFemale % 25 == 0) {
                femalesQueue.poll();
                femalesQueue.poll();
                continue;
            } else if (currentMale % 25 == 0) {
                malesStack.pop();
                malesStack.pop();
                continue;
            }

            if (currentFemale == currentMale) {
                matches++;
                femalesQueue.poll();
                malesStack.pop();
            } else {
                femalesQueue.poll();
                malesStack.push(malesStack.pop() - 2);
            }
        }

        System.out.println("Matches: " + matches);
        System.out.println("Males left: " + formatArrayDeck(malesStack));
        System.out.println("Females left: " + formatArrayDeck(femalesQueue));

    }

    private static String formatArrayDeck(Deque<Integer> deque) {
        if (deque.isEmpty()) {
            return "none";
        }
        return deque
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

    }
}
