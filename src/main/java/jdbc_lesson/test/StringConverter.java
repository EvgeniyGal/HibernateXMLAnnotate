package jdbc_lesson.test;

import java.util.function.Function;

public interface StringConverter<T> extends Function<String, T> {

    @Override
    public T apply(String t);
    
    //template method
    default void display(String value){
        System.out.println(this.apply(value));
    }

}
