package jdbc_lesson;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            registry = new StandardServiceRegistryBuilder().configure().build();
            MetadataSources source = new MetadataSources(registry);
            Metadata metadata = source.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        return sessionFactory;
    }
    
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            Configuration configuration = new Configuration();
//            configuration.configure("hibernate.cfg.xml");
//            configuration.addAnnotatedClass(People.class);
//                        
//            registry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties())
//                    .configure().build();
//            
//            sessionFactory = configuration.buildSessionFactory(registry);
//        }
//        return sessionFactory;
//    }
    
    public static void shutdown() {
        if (sessionFactory == null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    
}