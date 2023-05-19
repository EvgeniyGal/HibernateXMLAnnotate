package test;

public class ReverseHandler extends Handler{

    public ReverseHandler(Handler handler) {
        super(handler);
    }

    @Override
    public String apply(String args) {
        return new StringBuilder(getHandler().apply(args)).reverse().toString();
    }
    
}
