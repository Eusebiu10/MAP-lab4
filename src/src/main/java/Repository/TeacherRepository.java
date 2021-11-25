package Repository;

import Model.Teacher;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class TeacherRepository extends InMemoryRepository<Teacher> implements FileRepository<Teacher> {
    private ObjectMapper objectMapper;
    private String fileName;

    @Override
    public void writeData() throws IOException {
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(fileName), repoList);
    }


    public TeacherRepository(String fileName){
        super();
        repoList = new LinkedList<>();
        objectMapper = new ObjectMapper();
        this.fileName = fileName;
    }

    @Override
    public Teacher update(Teacher obj) {
        Teacher teacherToUpdate = this.repoList.stream()
                .filter(teacher -> teacher.getTeacherId() == obj.getTeacherId())
                .findFirst()
                .orElseThrow();

        teacherToUpdate.setFirstName(obj.getFirstName());
        teacherToUpdate.setLastName(obj.getLastName());
        teacherToUpdate.setCourses(obj.getCourses());
        return teacherToUpdate;
    }
}