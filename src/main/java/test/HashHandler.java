package test;


public class HashHandler extends Handler{

    public HashHandler(Handler handler) {
        super(handler);
    }

    @Override
    public String apply(String args) {
        return String.valueOf(getHandler().apply(args).hashCode());
    }
    
}
