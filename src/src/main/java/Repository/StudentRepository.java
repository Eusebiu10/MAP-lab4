package Repository;


import Model.Student;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class StudentRepository extends InMemoryRepository<Student> implements FileRepository<Student>{
    private ObjectMapper ObjMap;
    private String fileName;

    public StudentRepository(String fileName){
        super();
        repoList = new LinkedList<>();
        ObjMap = new ObjectMapper();
        this.fileName = fileName;
    }

    @Override
    public void writeData() throws IOException {
        ObjectWriter writer = ObjMap.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(fileName), repoList);
    }


    @Override
    public Student update(Student obj) {
        Student studentToUpdate = this.repoList.stream()
                .filter(student -> student.getStudentId() == obj.getStudentId())
                .findFirst()
                .orElseThrow();

        studentToUpdate.setFirstName(obj.getFirstName());
        studentToUpdate.setLastName(obj.getLastName());
        studentToUpdate.setEnrolledCourses(obj.getEnrolledCourses());
        return studentToUpdate;
    }
}