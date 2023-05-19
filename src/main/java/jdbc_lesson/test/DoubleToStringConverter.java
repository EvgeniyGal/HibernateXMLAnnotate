package jdbc_lesson.test;

public class DoubleToStringConverter implements StringConverter <Double>{

    @Override
    public Double apply(String value) {
        return Double.valueOf(value);
    }
    
}
