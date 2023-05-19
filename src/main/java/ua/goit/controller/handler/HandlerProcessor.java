package ua.goit.controller.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static jdbc_lesson.PropertyReader.PROPERTIES;
import jdbc_lesson.entities.BaseEntity;
import jdbc_lesson.repository.BaseRepository;
import jdbc_lesson.repository.RepositoryFactory;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import static org.reflections.scanners.Scanners.SubTypes;

public class HandlerProcessor {
    
    private final static Map<String, BaseHandler> handlers = getHandlers();
    
    private final static Connection connection;
//    private final BaseRepository<People, BigInteger> repository =
//            RepositoryFactory.getInstance(connection).of(People.class);
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(PROPERTIES.getProperty("url"), PROPERTIES);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public static <ID, T extends BaseEntity<ID>> List<T> process(HttpServletRequest request, 
            HttpServletResponse response, Class<T> modelClass){
        BaseRepository<T, ID> repository = RepositoryFactory.getInstance(connection).of(modelClass);
        List<T> entities = repository.findAll();
        int commandCounter = 1;
        while(true){
            String parameter = request.getParameter("command"+commandCounter);
            if (parameter==null) break;
            BaseHandler baseHandler = handlers.get(parameter);
            entities = baseHandler.process(entities, request, response, modelClass);
            commandCounter++;
        }
        return entities;
    }
    
    @SneakyThrows
    private static Map<String, BaseHandler> getHandlers(){
        Set<Class<?>> subClasses = new Reflections(BaseHandler.class.getPackage().getName())
                .get(SubTypes.of(BaseHandler.class).asClass());
        Map<String, BaseHandler> handlers = new HashMap<>(subClasses.size());
        for (Class subClass : subClasses) {
            BaseHandler baseHandler = (BaseHandler)subClass.newInstance();
            handlers.put(baseHandler.getCommandName(), baseHandler);
        }
        return handlers;
    }
    
}
