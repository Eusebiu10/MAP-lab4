package Model;

import java.util.List;

public class Course implements Comparable<Course>{
    private String name;
    private long teacherId;
    private int maxEnrollment;
    private List<Long> studentsEnrolled;
    private int credits;
    private long courseId;


    public Course(String name, long teacherId, int maxEnrollment, int credits, long courseId, List<Long> students){
        this.name = name;
        this.teacherId = teacherId;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = students;
        this.credits = credits;
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", teacherId=" + teacherId +
                ", maxEnrollment=" + maxEnrollment +
                ", enrolledStudents=" + studentsEnrolled +
                ", credits=" + credits +
                ", courseId=" + courseId +
                '}';
    }


    public void addStudent(long student) {
        studentsEnrolled.add(student);
    }


    public void deleteStudent(long student) {
        studentsEnrolled.remove(student);
    }

    public int getNumberOfStudents(){
        return studentsEnrolled.size();
    }



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public long getTeacher() {
        return teacherId;
    }


    public void setTeacher(long teacherId) {
        //deleteTeacherCourse(this.teacherId, courseId);
        this.teacherId = teacherId;
        //addTeacherCourse(teacherId, courseId);
    }



    public int getMaxEnrollment() {
        return maxEnrollment;
    }


    public void setMaxEnrollment(int maxEnrollment) {
        if (this.getNumberOfStudents() > maxEnrollment) {
            this.maxEnrollment = maxEnrollment;
        }
    }


    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }


    public List<Long> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Long> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public long getCourseId() {
        return courseId;
    }

    @Override
    public int compareTo(Course o) {
        if (courseId == o.getCourseId()) {
            return 1;
        }
        return 0;
    }
}