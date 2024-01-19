package daoImpl;

import dao.Repository;
import entity.ClassSchool;
import entity.Department;
import entity.Note;
import entity.Student;

import javax.persistence.Query;
import java.util.List;

public class StudentDaoImpl extends Repository<Student> {
    public StudentDaoImpl() {
    }

    @Override
    public boolean create(Student o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    public Student getStudentById(int studentId) {
        session = sessionFactory.openSession();
        Student student = session.get(Student.class, studentId);
        session.close();
        return student;
    }

    public int getNumberOfSubjectsForStudent(int studentId) {
        session = sessionFactory.openSession();
        Query query = session.createQuery(
                "SELECT COUNT(DISTINCT note.subject.id) " +
                        "FROM Note note " +
                        "WHERE note.student.id = :studentId");

        query.setParameter("studentId", studentId);
        Long result = ((org.hibernate.query.Query<Long>) query).uniqueResult();
        session.close();

        return result != null ? result.intValue() : 0;
    }

    public List<Note> getAllNotesForStudent(int studentId) {
        session = sessionFactory.openSession();
        Query query = session.createQuery(
                "FROM Note note " +
                        "JOIN FETCH note.subject " +
                        "WHERE note.student.id = :studentId");
        query.setParameter("studentId", studentId);
        List<Note> notes = query.getResultList();
        session.close();
        return notes;
    }

    public int getNumberOfStudentsInDepartment(Department department) {
        session = sessionFactory.openSession();
        String hql = "SELECT COUNT(s) FROM Student s WHERE s.aClassSchool.department = :department";
        Long count = session.createQuery(hql, Long.class)
                .setParameter("department", department)
                .uniqueResult();
        session.close();

        return count != null ? count.intValue() : 0;
    }

    public List<String> getAllStudentNamesInLevel(String level) {
        session = sessionFactory.openSession();
        String hql = "SELECT CONCAT(s.firstName, ' ', s.lastName) FROM Student s WHERE s.aClassSchool.level = :level";
        List<String> studentNames = session.createQuery(hql, String.class)
                .setParameter("level", level)
                .list();
        session.close();
        return studentNames;
    }



    public void deleteStudent(Student student) {
      session = sessionFactory.openSession();
       session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void endSession() {
        sessionFactory.close();
    }
}
