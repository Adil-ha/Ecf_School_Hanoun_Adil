package daoImpl;

import dao.Repository;
import entity.Note;

public class NoteDaoImpl extends Repository<Note> {
    public NoteDaoImpl() {
    }

    @Override
    public boolean create(Note o) {
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
