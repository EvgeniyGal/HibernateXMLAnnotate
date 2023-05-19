package jdbc_lesson;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static jdbc_lesson.PropertyReader.PROPERTIES;

import jdbc_lesson.entities.People;
import jdbc_lesson.repository.BaseRepository;
import jdbc_lesson.repository.RepositoryFactory;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.Transaction;

//solid
//GRASP

// C    CREATE  INSERT  POST
// R    READ    SELECT  GET
// U    UPDATE  UPDATE  PUT
// D    DELETE  DELETE  DELETE


// alex -> 1+15+5+20 = 41
// yx -> 20+21 = 41


public class MySqlDbExample {

    public static void main(String[] args) throws Exception {

        People people = People.builder().age(30).birthday(LocalDate.of(1993, 1, 1)).name("Alex").build();

        Transaction transaction = null;

        Long peopleId = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            peopleId = (Long) session.save(people);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            People p = session.byId(People.class).load(1L);
            System.out.println(p);
            p.setName("Oleg");
//            session.update(p);
            System.out.println(p);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            people.setId(peopleId);
            session.save(people);
            People p = session.byId(People.class).load(1L);
            System.out.println(p);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }


//        try (Connection conn = DriverManager.getConnection(PROPERTIES.getProperty("url"), PROPERTIES); 
//                RepositoryFactory repositoryFactory = RepositoryFactory.getInstance(conn)) {
//            BaseRepository<People, Long> repository = repositoryFactory.of(People.class);
//            Optional<People> findById = repository.findById(1L);
//            System.out.println(findById);
//            List<People> findById1 = repository.findAll();
//            System.out.println(findById1);
//            People p1 = People.builder().age(10).name("Oleg").birthday("10.01.2003").build();
//            repository.save(p1);
//        }
    }

}
