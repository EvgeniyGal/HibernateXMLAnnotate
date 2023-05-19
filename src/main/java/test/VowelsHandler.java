package test;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class VowelsHandler extends Handler{
    
    private final static Set<String> vowels = Sets.newHashSet("a", "e", "i", "o", "u");

    public VowelsHandler(Handler handler) {
        super(handler);
    }

    @Override
    public String apply(String args) {
        String result = getHandler().apply(args);
        return Arrays.stream(result.split(""))
                .filter(v->!vowels.contains(v))
                .collect(Collectors.joining());
    }
    
}
