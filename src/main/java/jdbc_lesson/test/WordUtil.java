package jdbc_lesson.test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordUtil {

    private final static Set <Character> VOWELS = Stream.of('a', 'e', 'i', 'o', 'u', 'y')
            .collect(Collectors.toSet());

    //TDD - test drive development    
    public int syllablesInWord(String word) {
        int counter = 0;
        if (word == null || word.isEmpty()) return counter;        
        int prevVowelIndex = Integer.MIN_VALUE;
        char[] chars = word.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (VOWELS.contains(chars[i])) {
                if (prevVowelIndex != i - 1) counter++;
                prevVowelIndex = i;
            }
        }
        if (chars[chars.length - 1] == 'e') counter--;
        return Math.max(1, counter);
    }
}
