package jdbc_lesson.test;

public class IntegerToStringConverter implements StringConverter <Integer>{

    @Override
    public Integer apply(String value) {
        return Integer.valueOf(value);
    }
    
}
