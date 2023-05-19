package test;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseChain {
    
    private static List<BaseChain> chains = new ArrayList<>();    
    static{
        chains.add(new SmallWorldChain());
        chains.add(new LargeWordChain());
    }
    
    public static String process(String arg) {
        
        for (BaseChain baseChain : chains) {
            if (baseChain.isApplicable(arg)) {
                return baseChain.handle(arg);
            }
        }
        throw new RuntimeException();
    }
    
    public abstract String handle(String arg);
    
    public abstract boolean isApplicable(String arg);
    
}
