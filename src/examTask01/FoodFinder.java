package examTask01;

import java.util.*;
import java.util.stream.Collectors;

public class FoodFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //vowels Queue
        //consonants Stack

        ArrayDeque<Character> vowelsQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .forEach(e -> vowelsQueue.offer(e.charAt(0)));

        ArrayDeque<Character> consonantsStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .forEach(e -> consonantsStack.push(e.charAt(0)));

        String[] givenWords = new String[]{"pear", "flour", "pork", "olive"};
        String[] foundWords = new String[]{"****", "*****", "****", "*****"};

        while (!consonantsStack.isEmpty()) {
            char vowel = vowelsQueue.poll();
            char consonant = consonantsStack.pop();

            for (int i = 0; i < givenWords.length; i++) {
                String word = givenWords[i];
                int indexVowel = word.indexOf(vowel);
                int indexConsonant = word.indexOf(consonant);
                if (indexVowel >= 0) {
                    foundWords[i] = foundWords[i].substring(0, indexVowel)
                            + vowel
                            + foundWords[i].substring(indexVowel + 1);
                }
                if (indexConsonant >= 0) {
                    foundWords[i] = foundWords[i].substring(0, indexConsonant)
                            + consonant
                            + foundWords[i].substring(indexConsonant + 1);
                }
            }
            vowelsQueue.offer(vowel);
        }

        List<String> output = Arrays.stream(foundWords)
                .filter(word -> !word.contains("*"))
                .collect(Collectors.toList());

        System.out.printf("Words found: %d%n", output.size());
        output.forEach(System.out::println);
    }
}
