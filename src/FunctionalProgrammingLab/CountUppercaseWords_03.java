package FunctionalProgrammingLab;

import java.util.*;
import java.util.function.Predicate;

public class CountUppercaseWords_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] words = scanner.nextLine().split("\\s+");

        Predicate<String> isStartingWithUppercase = w -> Character.isUpperCase(w.charAt(0));

        Deque<String> wordsQueue = new ArrayDeque<>();

        Arrays.stream(words)
                .filter(isStartingWithUppercase)
                .forEach(wordsQueue::offer);

        System.out.println(wordsQueue.size());

        while (!wordsQueue.isEmpty()) {
            System.out.println(wordsQueue.poll());
        }
    }
}
