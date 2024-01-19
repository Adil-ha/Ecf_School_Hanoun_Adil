package daoImpl;

import dao.Repository;
import entity.Subject;
import java.util.List;

public class SubjectDaoImpl extends Repository<Subject> {
    public SubjectDaoImpl() {
    }

    @Override
    public boolean create(Subject o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Subject getById(int id) {
        session = sessionFactory.openSession();
        Subject subject = session.get(Subject.class, id);
        session.close();
        return new Subject();
    }

    public List<Subject> getAllSubjects() {
        session = sessionFactory.openSession();
        List<Subject> subjects = session.createQuery("FROM Subject", Subject.class).list();
        session.close();
        return subjects;
    }

    @Override
    public void endSession() {
        sessionFactory.close();
    }
}
