package ua.goit.controller.handler;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdbc_lesson.entities.BaseEntity;

public abstract class BaseHandler {
    
    public abstract <T extends BaseEntity> List<T> process(List<T> models, 
            HttpServletRequest request, HttpServletResponse response, Class<T> modelClass);
    
    public abstract String getCommandName();
    
}
