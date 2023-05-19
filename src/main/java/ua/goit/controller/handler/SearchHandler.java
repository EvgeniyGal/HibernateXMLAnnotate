package ua.goit.controller.handler;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdbc_lesson.entities.BaseEntity;
import lombok.Getter;
import lombok.SneakyThrows;

public class SearchHandler extends BaseHandler{
    
    @Getter
    private final String commandName = "search";

    @SneakyThrows
    @Override
    public <T extends BaseEntity> List<T> process(List<T> models, 
            HttpServletRequest request, HttpServletResponse response, Class<T> modelClass) {
        Field[] declaredFields = modelClass.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            String parameter = request.getParameter(String.join("_", commandName, field.getName()));
            if (parameter == null || parameter.isEmpty())continue;
            Iterator<T> iterator = models.iterator();
            while(iterator.hasNext()){
                T model = iterator.next();
                Object fieldValue = field.get(model);
                if (!Objects.equals(fieldValue, parameter)) {
                    iterator.remove();
                }
            }
        }
        return models;
    }

}
