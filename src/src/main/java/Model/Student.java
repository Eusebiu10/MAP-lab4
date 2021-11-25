package Model;

import java.util.List;

public class Student extends Person implements Comparable<Student>{
    private long studentId;
    private List<Long> enrolledCourses;


    public Student(String firstname, String lastName, List<Long> enrolledCourses, long studentId) {
        super(firstname, lastName);
        this.enrolledCourses = enrolledCourses;
        this.studentId = studentId;
    }



    @Override
    public String toString() {
        return "Student{" +
                "firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", studentId=" + studentId +
                ", nrEnrolledCourses=" + getEnrolledCourses() +
                '}';
    }



    public void addCourse(long courseId){
        this.enrolledCourses.add(courseId);
    }


    public void deleteCourse(long courseId){
        this.enrolledCourses.remove(courseId);
    }


     public long getStudentId() {
        return studentId;
    }


    public List<Long> getEnrolledCourses() {
        return enrolledCourses;
    }


    public void setEnrolledCourses(List<Long> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }


    public int getNumberOfCourses(){
        return enrolledCourses.size();
    }

    @Override
    public int compareTo(Student o) {
        if (studentId == o.getStudentId()) {
            return 1;
        }
        return 0;
    }
}