package daoImpl;

import dao.Repository;
import entity.TimeTable;

public class TimeTableDaoImpl extends Repository<TimeTable> {
    public TimeTableDaoImpl() {
    }

    @Override
    public boolean create(TimeTable o) {
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
