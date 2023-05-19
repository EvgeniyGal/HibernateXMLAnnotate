package jdbc_lesson.test;

public class Client {
    
    /*
    GRASP:
        information expert -> SRP
        creator
        controller
        low coupling -> DIP
        high cohesion
        polymorphism -> ocp
        pure fabrication
        inderection -> DIP
        protected variations -> ocp
    
    SOLID
    SRP
    OCP
    LSP
    ISP
    DIP
    */
    
    public static void main(String[] args) {
                
        StringConverter <Double> converter1 = new DoubleToStringConverter();
        converter1.display("1");        
        StringConverter <Integer> converter2 = new IntegerToStringConverter();
        converter2.display("1");
        
        Converter converter3 = new Converter<>(s -> Double.valueOf(s));
        converter3.display("1");  
        Converter converter4 = new Converter<>(Integer::valueOf);
        converter4.display("1");  
        
    }

}
