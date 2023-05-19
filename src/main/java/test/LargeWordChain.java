package test;

public class LargeWordChain extends BaseChain{

    @Override
    public String handle(String arg) {
        return arg.substring(0, arg.length()/2);
    }

    @Override
    public boolean isApplicable(String arg) {
        return arg.length()>10;
    }
    
}
