package jdbc_lesson;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;
import lombok.SneakyThrows;

public class PropertyReader {
    
    public static final Properties PROPERTIES;

    static {
        PROPERTIES = loadProperties("application.properties");
    }

    @SneakyThrows
    private static Properties loadProperties(final String propertiesFile) {
        try (final InputStream is = new BufferedInputStream(MySqlDbExample.class
                .getClassLoader().getResourceAsStream(propertiesFile))) {
            final Properties property = new Properties();
            property.load(is);
            return property;
        }
    }
    
}
