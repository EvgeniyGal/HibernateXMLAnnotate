package test;

public class SmallWorldChain extends BaseChain{

    @Override
    public String handle(String srg) {
        return srg+srg;
    }

    @Override
    public boolean isApplicable(String arg) {
        return !arg.isEmpty() && arg.length()<=10;
    }
    
}
