package Controller;

import Model.Course;
import Model.Student;
import Model.Teacher;
import Repository.CourseRepository;
import Repository.StudentRepository;
import Repository.TeacherRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Controller {
    private CourseRepository courseRepo;
    private StudentRepository studentRepo;
    private TeacherRepository teacherRepo;

    public Controller(String coursesFile, String studentsFile, String teacherFile){
        studentRepo = new StudentRepository(studentsFile);
        teacherRepo = new TeacherRepository(teacherFile);
        courseRepo = new CourseRepository(coursesFile);
    }


    public void register(long courseId, long studentId) {
        int studentIndex = -1;
        for (int idx = 0; idx < studentRepo.getAll().size(); idx++){
            if (studentRepo.getAll().get(idx).getStudentId() == studentId) {
                studentIndex = idx;
                break;
            }
        }

        int courseIndex = -1;
        for (int idx = 0; idx < courseRepo.getAll().size(); idx++){
            if (courseRepo.getAll().get(idx).getCourseId() == courseId) {
                courseIndex = idx;
                break;
            }
        }

        Course course = courseRepo.getAll().get(courseIndex);
        Student student = studentRepo.getAll().get(studentIndex);

        course.addStudent(studentId);
        student.addCourse(courseId);

        courseRepo.update(course);
        studentRepo.update(student);
    }


    public List<Course> retrieveCoursesWithFreePlaces(){
        List<Course> freePlacesCourses = new LinkedList<>();
        for (Course course : courseRepo.getAll()){
            if (course.getMaxEnrollment() > course.getNumberOfStudents()){
                freePlacesCourses.add(course);
            }
        }
        return freePlacesCourses;
    }


    public List<Student> retrieveStudentsEnrolledForACourse(long courseId){
        List<Student> studentsEnrolledForTheCourse = new LinkedList<>();
        for (Student student : studentRepo.getAll()){
            if (student.getEnrolledCourses().contains(courseId)){
                studentsEnrolledForTheCourse.add(student);
            }
        }
        return studentsEnrolledForTheCourse;
    }


    public List<Course> getAllCourses() {
        return courseRepo.getAll();
    }


    public void deleteTeacherCourse(long courseId, long teacherId) {
        int teacherIndex = -1;
        for (int idx = 0; idx < teacherRepo.getAll().size(); idx++){
            if (teacherRepo.getAll().get(idx).getTeacherId() == teacherId) {
                teacherIndex = idx;
                break;
            }
        }

        int courseIndex = -1;
        for (int idx = 0; idx < courseRepo.getAll().size(); idx++){
            if (courseRepo.getAll().get(idx).getCourseId() == courseId) {
                courseIndex = idx;
                break;
            }
        }


        Course course = courseRepo.getAll().get(courseIndex);
        Teacher teacher = teacherRepo.getAll().get(teacherIndex);

        for (long studentId : course.getStudentsEnrolled()){
            for (Student student : studentRepo.getAll()){
                if (student.getStudentId() == studentId) {
                    student.deleteCourse(courseId);
                    studentRepo.update(student);
                }
            }
        }

        teacher.deleteCourse(courseId);
        teacherRepo.update(teacher);

        courseRepo.delete(course);

    }


    public void addTeacher(String firstName, String lastName, long teacherId) {
        boolean exist=false;
        for (Teacher teacher : teacherRepo.getAll()){
            if (teacher.getTeacherId() == teacherId){
            exist=true;
            }
        }
        if(exist==false)
        teacherRepo.create(new Teacher(firstName, lastName, new LinkedList<>(), teacherId));
    }

    public void addStudent(String firstName, String lastName, long studentId) {
        boolean exist=false;
        for (Student student : studentRepo.getAll()){
            if (student.getStudentId() == studentId){
            exist=true;
            }
        }
        if(exist==false)
            studentRepo.create(new Student(firstName, lastName, new LinkedList<>(), studentId));
    }

    public void addCourse(String name, long teacherId, int maxEnrollment, int credits, long courseId) {
        boolean corse=false,idt=false;
        for (Course course : courseRepo.getAll()) {
            if (course.getCourseId() == courseId) {
                corse=true;
            }
        }

        int teacherIndex = -1;
        for (int idx = 0; idx < teacherRepo.getAll().size(); idx++){
            if (teacherRepo.getAll().get(idx).getTeacherId() == teacherId) {
                teacherIndex = idx;
                idt=true;
            }
        }

        Teacher teacher = teacherRepo.getAll().get(teacherIndex);
        teacher.addCourse(courseId);
        teacherRepo.update(teacher);

        if(idt==false&&corse==false)
        courseRepo.create(new Course(name, teacherId, maxEnrollment, credits, courseId, new LinkedList<>()));

    }

    public int calculateStudentCredits(Student student){
        int nrCredits = 0;
        for (long courseId : student.getEnrolledCourses()){
            for (Course course : courseRepo.getAll()) {
                if (course.getCourseId() == courseId) {
                    nrCredits += course.getCredits();
                }
            }
        }
        return nrCredits;
    }

    public List<Student> retrieveAllStudents(){
        return studentRepo.getAll();
    }

    public List<Teacher> retrieveAllTeachers(){
        return teacherRepo.getAll();
    }

    public List<Student> sortStudentsById(){
        List<Student> students = studentRepo.getAll();
        Comparator<Student> studentComparator = Comparator.comparing(Student::getStudentId);
        return students.stream().sorted(studentComparator).toList();
    }

    public List<Course> sortCoursesByName(){
        List<Course> courses = courseRepo.getAll();
        Comparator<Course> courseComparator = Comparator.comparing(Course::getName);
        return courses.stream().sorted(courseComparator).toList();
    }

    public List<Student> filterStudentsEnrolled(){
        List<Student> students = studentRepo.getAll();
        return students.stream().filter(stud -> stud.getNumberOfCourses() > 0).toList();
    }

    public List<Course> filterCoursesWithStudents(){
        List<Course> courses = courseRepo.getAll();
        return courses.stream().filter(course -> course.getNumberOfStudents() > 0).toList();
    }


// se folosesc metodele default
    public void Read_Data(){
        try {
            studentRepo.readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            teacherRepo.readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            courseRepo.readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void Write_Data(){
        try {
            courseRepo.writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            studentRepo.writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            teacherRepo.writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}