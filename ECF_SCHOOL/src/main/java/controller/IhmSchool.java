package controller;

import entity.*;
import enumSchool.SchoolDayOfWeek;
import service.SchoolService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IhmSchool {

    private Scanner scanner;

    private String choice;

    private SchoolService schoolService;

    public IhmSchool() {
        scanner = new Scanner(System.in);
        schoolService = new SchoolService();
    }

    public void start(){
        do{
            menu();
            choice = scanner.nextLine();
            switch (choice){
                case "1":
                    createDepartment();
                    break;
                case "2":
                    createTeacher();
                    break;
                case "3":
                    createStudent();
                    break;
                case "4":
                    createSubject();
                    break;
                case "5":
                    createNote();
                    break;
                case "6":
                    createClass();
                    break;
                case "7":
                    createTimeTable();
                    break;
                case "8":
                    displayAllClasses();
                    break;
                case "9":
                    displayNumberOfSubjectsForStudent();
                    break;
                case "10":
                    displayAllNotesForStudent();
                    break;
                case "11":
                    displayAverageForStudent();
                    break;
                case "12":
                    displayNumberOfStudentsInDepartment();
                    break;
                case "13":
                    displayAllStudentNamesInLevel();
                    break;
                case "14":
                    deleteStudent();
                    break;
                case "15":
                    deleteClass();
                    break;
                case "16":
                    deleteDepartment();
                    break;

                case "0":
                    System.out.println("Bye");
                    schoolService.endSession();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }while (!choice.equals("0"));
    }

    private void menu() {
        System.out.println("########## Menu ##########");
        System.out.println("1- Créer un département");
        System.out.println("2- Créer un enseignant");
        System.out.println("3- Créer un étudiant");
        System.out.println("4- Créer une matière");
        System.out.println("5- Créer une note");
        System.out.println("6- Créer une classe");
        System.out.println("7- Créer un emploi du temps");
        System.out.println("8- Afficher la liste des classes ");
        System.out.println("9- Afficher le nombre de matières d'un élève");
        System.out.println("10- Afficher la liste des notes d'un élève");
        System.out.println("11- Afficher la moyenne d'un élève");
        System.out.println("12- Afficher le nombre d'élèves d'un département");
        System.out.println("13- Afficher tous les noms des élèves d'un niveau");
        System.out.println("14- Supprimer un élève ");
        System.out.println("15- Supprimer une classe ");
        System.out.println("16- Supprimer un département ");
        System.out.println("0- Quitter");
        System.out.print("Votre choix : ");
    }

    private void createDepartment() {
        System.out.println("##### Création d'un département #####");

        System.out.print("Nom du département : ");
        String departmentName = scanner.nextLine();

        Department department = new Department();
        department.setName(departmentName);

        schoolService.createDepartment(department);

        System.out.println("Département créé avec succès !");
    }

    private void createTeacher() {
        System.out.println("##### Création d'un enseignant #####");

        System.out.print("Nom de l'enseignant : ");
        String lastName = scanner.nextLine();
        schoolService.validateName(lastName);

        System.out.print("Prénom de l'enseignant : ");
        String firstName = scanner.nextLine();
        schoolService.validateName(firstName);

        System.out.print("Âge de l'enseignant : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        schoolService.validateTeacherAge(age);

        System.out.print("Grade de l'enseignant : ");
        String grade = scanner.nextLine();

        System.out.print("Est-ce que l'enseignant est chef de departement ? (true/false) : ");
        boolean headTeacher = scanner.nextBoolean();
        scanner.nextLine();


        System.out.print("ID du département de l'enseignant : ");
        int departmentId = scanner.nextInt();
        scanner.nextLine();

        Department department = schoolService.getDepartmentById(departmentId);

        Teacher teacher = new Teacher();
        teacher.setLastName(lastName);
        teacher.setFirstName(firstName);
        teacher.setAge(age);
        teacher.setGrade(grade);
        teacher.setHeadTeacher(headTeacher);
        teacher.setDepartment(department);

        displayAllClasses();

        System.out.print("Entrez les IDs des classes associées à l'enseignant (séparés par des virgules) : ");
        String classIdsInput = scanner.nextLine();
        String[] classIds = classIdsInput.split(",");

        for (String classId : classIds) {
            int id = Integer.parseInt(classId.trim());
            ClassSchool classSchool = schoolService.getClassById(id);
            teacher.getClassSchools().add(classSchool);
        }

        displayAllSubjects();

        System.out.print("ID de la matière pour l'enseignant (-1 pour terminer) : ");
        int subjectIds;
        List<Subject> subjects = new ArrayList<>();

        while((subjectIds = scanner.nextInt())!= -1){
            Subject subject = new Subject();
            subject.setId(subjectIds);

            subjects.add(subject);

            System.out.print("ID de la matière pour l'enseignant (-1 pour terminer) : ");
        }

        teacher.setSubjects(subjects);
        schoolService.createTeacher(teacher);

        System.out.println("Enseignant créé avec succès !");
    }


    private void createStudent() {
        System.out.println("##### Création d'un étudiant #####");

        System.out.print("Nom de l'étudiant : ");
        String lastName = scanner.nextLine();
        schoolService.validateName(lastName);

        System.out.print("Prénom de l'étudiant : ");
        String firstName = scanner.nextLine();
        schoolService.validateName(firstName);

        System.out.print("Adresse e-mail de l'étudiant : ");
        String email = scanner.nextLine();
        schoolService.validateStudentEmail(email);

        System.out.print("Date de naissance de l'étudiant (YYYY-MM-DD) : ");
        String dateString = scanner.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(dateString);

        System.out.print("Sélectionnez une classe pour l'étudiant (entrez l'ID de la classe) : ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        Student student = new Student();
        student.setLastName(lastName);
        student.setFirstName(firstName);
        student.setEmail(email);
        student.setDateOfBirth(dateOfBirth);

        ClassSchool selectedClass = schoolService.getClassById(classId);
        student.setAClassSchool(selectedClass);

        schoolService.createStudent(student);

        System.out.println("Étudiant créé avec succès !");
    }


    private void createSubject() {
        System.out.println("##### Création d'une matière #####");

        System.out.print("Titre de la matière : ");
        String title = scanner.nextLine();

        System.out.print("Description de la matière : ");
        String description = scanner.nextLine();

        System.out.print("Durée en minutes de la matière : ");
        int durationMinutes = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Coefficient de la matière : ");
        int coefficient = scanner.nextInt();
        scanner.nextLine();

        Subject subject = new Subject();
        subject.setTitle(title);
        subject.setDescription(description);
        subject.setDurationMinutes(durationMinutes);
        subject.setCoefficient(coefficient);

        schoolService.createSubject(subject);

        System.out.println("Matière créée avec succès !");
    }



    private void createNote() {
        System.out.println("##### Création d'une note #####");

        System.out.print("Valeur de la note (entre 0.0 et 20.0) : ");
        float value = scanner.nextFloat();
        scanner.nextLine();
        schoolService.validateNoteValue(value);

        System.out.print("Commentaire : ");
        String comment = scanner.nextLine();

        System.out.print("ID de l'élève pour la note : ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID de la matière pour la note : ");
        int subjectId = scanner.nextInt();
        scanner.nextLine();

        Note note = new Note();
        note.setValue(value);
        note.setComment(comment);

        Student student = new Student();
        student.setId(studentId);

        Subject subject = new Subject();
        subject.setId(subjectId);

        note.setStudent(student);
        note.setSubject(subject);

        schoolService.createNote(note);

        System.out.println("Note créée avec succès !");
    }

    private void createClass() {
        System.out.println("##### Création d'une classe #####");

        System.out.print("Nom de la classe : ");
        String className = scanner.nextLine();

        System.out.print("Niveau de la classe : ");
        String classLevel = scanner.nextLine();

        System.out.print("ID du département pour la classe : ");
        int departmentId = scanner.nextInt();
        scanner.nextLine();

        ClassSchool classSchool = new ClassSchool();
        classSchool.setName(className);
        classSchool.setLevel(classLevel);

        Department department = new Department();
        department.setId(departmentId);

        classSchool.setDepartment(department);

        schoolService.createClass(classSchool);

        System.out.println("Classe créée avec succès !");
    }


    private void createTimeTable() {
        System.out.println("##### Création d'un emploi du temps #####");

        System.out.print("Jour de la semaine (LUNDI, MARDI, etc.) : ");
        String dayOfWeekString = scanner.nextLine();
        SchoolDayOfWeek dayOfWeek = SchoolDayOfWeek.valueOf(dayOfWeekString.toUpperCase());

        System.out.print("Heure de début (HH:mm) : ");
        String startTimeString = scanner.nextLine();
        LocalTime startTime = LocalTime.parse(startTimeString);

        displayAllClasses();

        System.out.print("ID de la classe pour l'emploi du temps : ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        displayAllSubjects();

        System.out.print("ID de la matière pour l'emploi du temps (-1 pour terminer) : ");
        int subjectId;
        List<Subject> subjects = new ArrayList<>();

        while ((subjectId = scanner.nextInt()) != -1) {
            Subject subject = new Subject();
            subject.setId(subjectId);

            subjects.add(subject);

            System.out.print("ID de la matière pour l'emploi du temps (-1 pour terminer) : ");
        }

        TimeTable timeTable = new TimeTable();
        timeTable.setDayOfWeek(dayOfWeek);
        timeTable.setStartTime(startTime);

        ClassSchool classSchool = new ClassSchool();
        classSchool.setId(classId);

        timeTable.setAClassSchool(classSchool);

        timeTable.setSubjects(subjects);

        schoolService.createTimeTable(timeTable);

        System.out.println("Emploi du temps créé avec succès !");
    }


    private void displayAllClasses() {
        List<ClassSchool> classes = schoolService.getAllClasses();

        System.out.println("Liste des classes disponibles :");
        for (ClassSchool aClass : classes) {
            System.out.println(aClass.getId() + ": " + aClass.getName());
        }
    }


    private void displayAllSubjects() {
        System.out.println("##### Liste des matières disponibles #####");

        List<Subject> subjects = schoolService.getAllSubjects();
        for (Subject subject : subjects) {
            System.out.println(subject.getId() + ". " + subject.getTitle());
        }

    }



    private void displayNumberOfSubjectsForStudent() {
        System.out.println("##### Affichage du nombre de matières d'un élève #####");

        System.out.print("Entrez l'ID de l'élève : ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        System.out.println(schoolService.getNumberOfSubjectsForStudent(studentId));

    }

    public void displayAllNotesForStudent() {
        System.out.println("##### Affichage de toutes les notes d'un élève #####");

        System.out.print("Entrez l'ID de l'élève : ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        List<Note> notes = schoolService.getAllNotesForStudent(studentId);

        if (notes.isEmpty()) {
            System.out.println("Aucune note n'a été trouvée pour l'élève avec l'ID : " + studentId);
        } else {
            for (Note note : notes) {
                System.out.println(note);
            }
        }
    }

    public void displayAverageForStudent() {
        System.out.println("##### Affichage de la moyenne d'un élève #####");

        System.out.print("Entrez l'ID de l'élève : ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Student student = schoolService.getStudentById(studentId);

        if (student == null) {
            System.out.println("Aucun élève trouvé avec l'ID : " + studentId);
            return;
        }
        double average = schoolService.calculateAverageForStudent(student);
        System.out.println("La moyenne de l'élève " + student.getFirstName() + " " + student.getLastName() + " est : " + average);
    }


    public void displayNumberOfStudentsInDepartment() {
        System.out.println("##### Affichage du nombre d'élèves d'un département #####");

        System.out.print("Entrez l'ID du département : ");
        int departmentId = scanner.nextInt();
        scanner.nextLine();
        Department department = schoolService.getDepartmentById(departmentId);
        if (department == null) {
            System.out.println("Aucun département trouvé avec l'ID : " + departmentId);
            return;
        }
        int numberOfStudents = schoolService.getNumberOfStudentsInDepartment(department);
        System.out.println("Le nombre d'élèves dans le département " + department.getName() + " est : " + numberOfStudents);
    }

    public void displayAllStudentNamesInLevel() {
        System.out.println("##### Affichage de tous les noms des élèves d'un niveau #####");

        System.out.print("Entrez le niveau (par exemple, 'Première') : ");
        String level = scanner.nextLine();

        List<String> studentNames = schoolService.getAllStudentNamesInLevel(level);

        if (studentNames.isEmpty()) {
            System.out.println("Aucun élève trouvé pour le niveau : " + level);
            return;
        }

        System.out.println("Noms des élèves dans le niveau " + level + " :");
        for (String studentName : studentNames) {
            System.out.println(studentName);
        }
    }


    private void deleteStudent() {
        System.out.println("##### Suppression d'un élève #####");

        System.out.print("Entrez l'ID de l'élève à supprimer : ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        boolean success = schoolService.deleteStudentById(studentId);

        if (success) {
            System.out.println("Élève supprimé avec succès.");
        } else {
            System.out.println("Aucun élève trouvé avec l'ID spécifié.");
        }
    }

    private void deleteClass() {
        System.out.println("##### Suppression d'une classe #####");

        System.out.print("Entrez l'ID de la classe à supprimer : ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        boolean success = schoolService.deleteClassById(classId);

        if (success) {
            System.out.println("Classe supprimée avec succès.");
        } else {
            System.out.println("Aucune classe trouvée avec l'ID spécifié.");
        }
    }

    private void deleteDepartment() {
        System.out.println("##### Suppression d'un département #####");

        System.out.print("Entrez l'ID du département à supprimer : ");
        int departmentId = scanner.nextInt();
        scanner.nextLine();

        boolean success = schoolService.deleteDepartmentById(departmentId);

        if (success) {
            System.out.println("Département supprimé avec succès.");
        } else {
            System.out.println("Aucun département trouvé avec l'ID spécifié.");
        }
    }

}
