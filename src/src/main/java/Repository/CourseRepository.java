package Repository;

import Model.Course;
import Model.Student;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CourseRepository extends InMemoryRepository<Course> implements FileRepository<Course> {
    private ObjectMapper objMap;
    private String fileName;

    @Override
    public void writeData() throws IOException {
        ObjectWriter writer = objMap.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(fileName), repoList);
    }


    public CourseRepository(String fileName){
        super();
        repoList = new LinkedList<>();
        objMap = new ObjectMapper();
        this.fileName = fileName;
    }



    @Override
    public Course update(Course obj) {
        Course courseToUpdate = this.repoList.stream()
                .filter(course -> course.getCourseId() == obj.getCourseId())
                .findFirst()
                .orElseThrow();

        courseToUpdate.setName(obj.getName());
        courseToUpdate.setMaxEnrollment(obj.getMaxEnrollment());
        courseToUpdate.setCredits(obj.getCredits());
        courseToUpdate.setTeacher(obj.getTeacher());
        courseToUpdate.setStudentsEnrolled(obj.getStudentsEnrolled());
        return courseToUpdate;
    }
}