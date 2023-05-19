package jdbc_lesson.test;

import org.junit.Assert;
import org.junit.Test;

public class WordUtilTest {
    
    private WordUtil wordUtil = new WordUtil();
    
    @Test
    public void testSomeMethod() {
        test("the", 1);
        test("be", 1);
        test("syllable", 2);
        test("springbok", 2);
        test("nice", 1);
    }
    
    private void test(String args, int sylllableNumber) {
        int result = wordUtil.syllablesInWord(args);
        Assert.assertEquals(sylllableNumber, result);
    }
    
}
