package daoImpl;

import dao.Repository;
import entity.Department;


public class DepartmentDaoImpl extends Repository<Department> {

    public DepartmentDaoImpl() {

    }

    @Override
    public boolean create(Department department) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(department);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Department getById(int departmentId) {
        session = sessionFactory.openSession();
        Department department = session.get(Department.class, departmentId);
        session.close();
        return department;
    }

    public void deleteDepartment(Department department) {
        session = sessionFactory.openSession();
         session.beginTransaction();
        session.delete(department);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void endSession() {
        sessionFactory.close();
    }


}
