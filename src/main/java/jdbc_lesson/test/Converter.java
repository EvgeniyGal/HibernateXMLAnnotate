package jdbc_lesson.test;

import java.util.function.Function;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Converter <T> implements Function<String, T>{
    
    private final Function <String, T> converter;

    @Override
    public T apply(String t) {
        return converter.apply(t);
    }
    
    //template method
    public void display(String value){
        System.out.println(this.apply(value));
    }
    
}
