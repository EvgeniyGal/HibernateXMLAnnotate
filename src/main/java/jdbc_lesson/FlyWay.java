package jdbc_lesson;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.Properties;
import org.flywaydb.core.Flyway;

public class FlyWay {

    public static void main(String[] args) {
        Properties props = PropertyReader.PROPERTIES;
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(props.getProperty("url"));
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));
        
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .baselineOnMigrate(true)
                .table("flyway_schema_history")
                //.locations("sql")//db.migration - by default
                .load();
        
        flyway.migrate();
    }

}
