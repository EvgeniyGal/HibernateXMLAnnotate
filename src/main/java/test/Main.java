package test;

public class Main {
    
    public static void main(String[] args) {
        
        
        
        //oiue
        String text = "qwertyuiopqwertyuiop";
        
        String process = BaseChain.process("");
        System.out.println(process);//qwertyuiop
        
        Handler handler1 = new ReverseHandler(new VowelsHandler(null));
        
        System.out.println(handler1.apply(text));
        
        
        Handler handler2 = new ReverseHandler(null);
        
        
        System.out.println(handler2.apply(text));
        
        
        Handler handler3 = new ReverseHandler(new VowelsHandler(new HashHandler(null)));
        System.out.println(handler3.apply(text));
        /*
pytrwq
poiuytrewq
909008293
-948666093
        */
        Handler handler4 = new VowelsHandler(new HashHandler(new ReverseHandler(null)));
        System.out.println(handler4.apply(text));
    }
    
}
