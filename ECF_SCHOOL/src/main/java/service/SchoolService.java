package service;

import daoImpl.*;
import entity.*;
import exception.InvalidEmailFormatException;
import exception.InvalidNameException;
import exception.InvalidNoteValueException;
import exception.MinorTeacherException;

import java.util.List;

public class SchoolService {
    private ClassSchoolDaoImpl classSchoolDao;
    private DepartmentDaoImpl departmentDao;
    private NoteDaoImpl noteDao;
    private StudentDaoImpl studentDao;
    private SubjectDaoImpl subjectDao;
    private TeacherDaoImpl teacherDao;
    private TimeTableDaoImpl timeTableDao;

    public SchoolService() {
        classSchoolDao = new ClassSchoolDaoImpl();
        departmentDao = new DepartmentDaoImpl();
        noteDao = new NoteDaoImpl();
        studentDao = new StudentDaoImpl();
        subjectDao = new SubjectDaoImpl();
        teacherDao = new TeacherDaoImpl();
        timeTableDao = new TimeTableDaoImpl();
    }

    public void createDepartment(Department department) {
        try {
            departmentDao.create(department);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTeacher(Teacher teacher) {
        try {
            teacherDao.create(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createStudent(Student student) {
        try {
            studentDao.create(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createSubject(Subject subject) {
        try {
            subjectDao.create(subject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNote(Note note) {
        try {
            noteDao.create(note);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createClass(ClassSchool classSchool) {
        try {
            classSchoolDao.create(classSchool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTimeTable(TimeTable timeTable) {
        try {
            timeTableDao.create(timeTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Department getDepartmentById(int departmentId) {
        try {
            return departmentDao.getById(departmentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ClassSchool getClassById(int classId) {
        return classSchoolDao.getById(classId);
    }

    public Student getStudentById(int studentId) {
        try {
            return studentDao.getStudentById(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ClassSchool> getAllClasses() {
        try {
            return classSchoolDao.getAllClasses();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Subject> getAllSubjects() {
        try {
            return subjectDao.getAllSubjects();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public int getNumberOfSubjectsForStudent(int studentId){
        try {
            return studentDao.getNumberOfSubjectsForStudent(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Note> getAllNotesForStudent(int studentId) {
        try {
            return studentDao.getAllNotesForStudent(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public double calculateAverageForStudent(Student student) {
        List<Note> notes = getAllNotesForStudent(student.getId());
        double totalWeightedScore = 0;
        int totalCoefficient = 0;

        for (Note note : notes) {
            totalWeightedScore += note.getValue() * note.getSubject().getCoefficient();
            totalCoefficient += note.getSubject().getCoefficient();
        }

        return totalCoefficient > 0 ? totalWeightedScore / totalCoefficient : 0;
    }


    public int getNumberOfStudentsInDepartment(Department department) {
        try {
            return studentDao.getNumberOfStudentsInDepartment(department);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public List<String> getAllStudentNamesInLevel(String level) {
        try {
            return studentDao.getAllStudentNamesInLevel(level);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean deleteStudentById(int studentId) {
        Student student = studentDao.getStudentById(studentId);
        if (student != null) {
            studentDao.deleteStudent(student);
            return true;
        }
        return false;
    }

    public boolean deleteClassById(int classId) {
        ClassSchool classSchool = classSchoolDao.getById(classId);
        if (classSchool != null) {
            classSchoolDao.deleteClass(classSchool);
            return true;
        }
        return false;
    }

    public boolean deleteDepartmentById(int departmentId) {
        Department department = departmentDao.getById(departmentId);
        if (department != null) {
            departmentDao.deleteDepartment(department);
            return true;
        }
        return false;
    }

    public void validateName(String name) {
        if (name == null || name.length() < 3) {
            throw new InvalidNameException("Le nom doit être non nul et contenir au moins 3 caractères.");
        }
    }

    public void validateTeacherAge(int age) {
        if (age < 18) {
            throw new MinorTeacherException("Un enseignant doit être majeur.");
        }
    }

    public void validateStudentEmail(String email) {
        if (!email.toLowerCase().endsWith("@gmail.com")) {
            throw new InvalidEmailFormatException("L'adresse e-mail de l'étudiant doit se terminer par '@gmail.com'.");
        }
    }


    public void validateNoteValue(float value) {
        if (value < 0 || value > 20) {
            throw new InvalidNoteValueException("La valeur de la note doit être entre 0.0 et 20.0.");
        }
    }

    public void endSession(){
        classSchoolDao.endSession();
        departmentDao.endSession();
        noteDao.endSession();
        studentDao.endSession();
        subjectDao.endSession();
        teacherDao.endSession();
        timeTableDao.endSession();
    }


}
