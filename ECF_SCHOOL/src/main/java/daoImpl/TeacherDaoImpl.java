package daoImpl;

import dao.Repository;
import entity.Teacher;

public class TeacherDaoImpl extends Repository<Teacher> {
    public TeacherDaoImpl() {
    }

    @Override
    public boolean create(Teacher o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public void endSession() {
        sessionFactory.close();
    }
}
