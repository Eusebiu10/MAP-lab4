package UI;

import Controller.Controller;
import Model.Course;
import Model.Student;
import Model.Teacher;

import java.util.Scanner;

public class Ui {
    private Controller Ctrl;
    private Scanner Scan;


    public void start() {
        Ctrl.Read_Data();
        int option = 0;
        while (option != 14) {
            this.showMenu();
            System.out.print("Alegeti optiunea : ");

            option = Scan.nextInt();
            if (option >= 14 || option < 1) {
                System.out.println("Indisponibil");
                option = 14;
            }

            if (option == 1) {
                this.addStudent();
            } else if (option == 2) {
                this.addTeacher();
            } else if (option == 3) {
                this.addCourse();
            } else if (option == 4) {
                this.register();
            } else if (option == 5) {
                this.retrieveFree();
            } else if (option == 6) {
                this.retrieveAll();
            } else if (option == 7) {
                this.teacherDeleteCourse();
            } else if (option == 8) {
                this.showAllTeachers();
            } else if (option == 9) {
                this.showAllStudents();
            } else if (option == 10) {
                this.showStudentsSortedById();
            } else if (option == 11) {
                this.showCoursesSortedByName();
            } else if (option == 12) {
                this.filterStudentsEnrolled();
            } else if (option == 13) {
                this.filterCoursesWithStudents();
            }
        }
        Ctrl.Write_Data();
    }

    public void showMenu(){
        System.out.print("""
                1. Adauga student\s
                2. Adauga Profesor\s
                3. Aauga Curs\s
                4. Inregistrare studen\s
                5. Lista cursurilor cu locuri disponibile\s
                6. Lista cursurilor disponibile\s
                7. Stergere curs\s
                8. Lista profesori\s
                9. Lista Studenti\s
                10. Lista de studenti dupa id\s
                11. Lista cursuri dupa nume\s
                12. Filter students enrolled for at least a course\s
                13. Filter courses with at least one student enrolled for\s
                14. Exit
                """);
    }

    public void addStudent() {
        Scan.nextLine();
        System.out.print("Prenume:");
        String firstName = Scan.nextLine();

        System.out.print("Nume:");
        String lastName = Scan.nextLine();

        System.out.print("Id: ");
        long id = Scan.nextLong();
    }

    public void addTeacher() {
        Scan.nextLine();

        System.out.print("Prenume : ");
        String firstName = Scan.nextLine();

        System.out.print("Nume: ");
        String lastName = Scan.nextLine();

        System.out.print("Id : ");
        long id = Scan.nextLong();

    }

    public void addCourse() {
        Scan.nextLine();

        System.out.print("Nume Curs : ");
        String name = Scan.nextLine();

        System.out.print("ProfesorID: ");
        long teacherId = Scan.nextLong();

        System.out.print("Capacitate maxima: ");
        int maxEnrollment = Scan.nextInt();

        System.out.print("Nr credite : ");
        int credits = Scan.nextInt();

        System.out.print("CursId : ");
        long courseId = Scan.nextLong();

    }

    public void register() {
        Scan.nextLine();

        System.out.print("CursId : ");
        long courseId = Scan.nextLong();

        System.out.print("StudentId : ");
        long studentId = Scan.nextLong();

    }

    public void retrieveFree() {
        for (Course course : registrationSystem.retrieveCoursesWithFreePlaces()) {
            System.out.println(course);
        }
    }

    public void retrieveAll() {
        for (Course course : registrationSystem.getAllCourses()) {
            System.out.println(course);
        }
    }

    public void teacherDeleteCourse() {
        Scan.nextLine();

        System.out.print("ProfesorId : ");
        long teacherId = Scan.nextLong();

        System.out.print("CursId : ");
        long courseId = Scan.nextLong();

    }

    public void showAllTeachers() {
        for (Teacher teacher : Ctrl.retrieveAllTeachers()) {
            System.out.println(teacher);
        }
    }

    public void showAllStudents() {
        for (Student student : Ctrl.retrieveAllStudents()) {
            System.out.println(student);
        }
    }

    public void showStudentsSortedById() {
        for (Student student : Ctrl.sortStudentsById()) {
            System.out.println(student);
        }
    }

    public void showCoursesSortedByName() {
        for (Course course : Ctrl.sortCoursesByName()) {
            System.out.println(course);
        }
    }

    public void filterStudentsEnrolled() {
        for (Student student : Ctrl.filterStudentsEnrolled()) {
            System.out.println(student);
        }
    }

    public void filterCoursesWithStudents() {
        for (Course course : Ctrl.filterCoursesWithStudents()) {
            System.out.println(course);
        }
    }
}