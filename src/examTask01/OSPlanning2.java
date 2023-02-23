package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class OSPlanning_paunov {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // taskStack
        // threadQueue

        Deque<Integer> taskStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(taskStack::push);

        Deque<Integer> threadsQueue = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int taskToKill = Integer.parseInt(scanner.nextLine());

        int currentTask = taskStack.peek();
        int currentThread = threadsQueue.peek();

        while (taskToKill != currentTask) {

            if (currentThread >= currentTask) {
                taskStack.pop();
            }
            threadsQueue.poll();

            currentTask = taskStack.peek();
            currentThread = threadsQueue.peek();

        }

        System.out.printf("Thread with value %d killed task %d%n", threadsQueue.peek(), taskToKill);

        String leftThreads = threadsQueue.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(leftThreads);
    }
}

