package jdbc_lesson.repository;

import jdbc_lesson.HibernateUtil;
import jdbc_lesson.entities.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateRepositoryImpl<E extends BaseEntity<ID>,
        ID extends Serializable> implements BaseRepository<E, ID> {
    private final Class<E> modelClass;
    private final SessionFactory sessionFactory;

    public HibernateRepositoryImpl(Class<E> modelClass, SessionFactory sessionFactory) {
        this.modelClass = modelClass;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<E> findById(ID id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            E e = session.byId(modelClass).load(id);
            transaction.commit();
            return Optional.ofNullable(e);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        throw new RuntimeException();
    }

    @Override
    public List<E> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT e FROM " + modelClass.getSimpleName() + " e ", modelClass).getResultList();
        }
    }

    @Override
    public E save(E entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (entity.getId() != null && findById(entity.getId()).isPresent()) {
                session.update(entity);
            } else {
                entity.setId((ID) session.save(entity));
            }
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        throw new RuntimeException();
    }

    @Override
    public List<E> saveAll(Iterable<E> itrb) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            List<E> entities = new ArrayList<>();
            transaction = session.beginTransaction();
            for (E entity : itrb) {
                if (entity.getId() != null && findById(entity.getId()).isPresent()) {
                    session.update(entity);
                    entities.add(entity);
                } else {
                    entity.setId((ID) session.save(entity));
                    entities.add(entity);
                }
            }
            transaction.commit();
            return entities;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        throw new RuntimeException();
    }

    @Override
    public void deleteById(ID id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Optional<E> entity = findById(id);
            entity.ifPresent(session::delete);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        throw new RuntimeException();
    }

    @Override
    public void close() throws IOException {
        HibernateUtil.shutdown();
    }
}
