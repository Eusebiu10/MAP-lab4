package Model;

import java.util.List;

public class Teacher extends Person implements Comparable<Teacher>{
    private long teacherId;
    private List<Long> courses;

     public Teacher(String firstName, String lastName, List<Long> courses, long teacherId){
        super(firstName, lastName);
        this.teacherId = teacherId;
        this.courses = courses;
    }



    @Override
    public String toString() {
        return "Teacher{" +
                "firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", teacherId=" + teacherId +
                ", courses=" + getCourses() +
                '}';
    }


     public void addCourse(long courseId){
        courses.add(courseId);
    }



    public void deleteCourse(long courseId) {
        courses.remove(courseId);
    }


    public int getNrOfCourses() {
        return courses.size();
    }


    public long getTeacherId() {
        return teacherId;
    }


    public List<Long> getCourses() {
        return courses;
    }

    public void setCourses(List<Long> courses) {
        this.courses = courses;
    }

    @Override
    public int compareTo(Teacher o) {
        if (teacherId == o.getTeacherId()) {
            return 1;
        }
        return 0;
    }
}