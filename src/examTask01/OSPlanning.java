package examTask01;

import java.util.*;

public class OSPlanning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] tasks = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] threads = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int taskToKill = Integer.parseInt(scanner.nextLine());

        Deque<Integer> tasksStack = new ArrayDeque<>();
        Deque<Integer> threadsQueue = new ArrayDeque<>();

        for (int i = 0; i < tasks.length; i++) {
            tasksStack.push(tasks[i]);
        }
        for (int i = 0; i < threads.length; i++) {
            threadsQueue.offer(threads[i]);
        }
        int lastTask = tasksStack.peek();
        int killerThread = 0;

        while (taskToKill != lastTask) {

            int currentTask = tasksStack.peek();
            int currentThread = threadsQueue.peek();
            lastTask = currentTask;

            if (currentTask == taskToKill) {
                killerThread = threadsQueue.peek();
                tasksStack.pop();
                break;
            }
            if (currentThread >= currentTask) {
                tasksStack.pop();
                threadsQueue.poll();

            } else {
                threadsQueue.poll();
            }
        }

        System.out.printf("Thread with value %d killed task %d%n", killerThread, taskToKill);
        while (!threadsQueue.isEmpty()){
            System.out.print(threadsQueue.poll()+" ");
        }
    }
}
