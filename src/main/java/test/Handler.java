package test;

public abstract class Handler {
    
    private final Handler handler;

    public Handler(Handler handler) {
        this.handler = handler;
    }
    
    public abstract String apply(String args);
    
    protected Handler getHandler(){
        if (handler == null) {
            return new Handler(handler) {
                @Override
                public String apply(String args) {
                    return args;
                }
            };
        }
        else return handler;
    }

}
