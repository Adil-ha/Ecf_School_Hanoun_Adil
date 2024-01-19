package daoImpl;

import dao.Repository;
import entity.ClassSchool;
import org.hibernate.SessionFactory;

import java.util.List;

public class ClassSchoolDaoImpl extends Repository<ClassSchool> {

    public ClassSchoolDaoImpl() {

    }

    @Override
    public boolean create(ClassSchool o) {
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

    public ClassSchool getById(int id) {
        session = sessionFactory.openSession();
        ClassSchool classSchool = session.get(ClassSchool.class, id);
        session.close();
        return classSchool;
    }

    public List<ClassSchool> getAllClasses() {
        session = sessionFactory.openSession();
        List<ClassSchool> classes = session.createQuery("FROM ClassSchool", ClassSchool.class).getResultList();
        session.close();
        return classes;
    }

    public void deleteClass(ClassSchool classSchool) {
         session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(classSchool);
        session.getTransaction().commit();
        session.close();
    }



}
