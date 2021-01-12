package StudentsStuff;

import java.util.List;

public interface StudentsController {
    List<Student> getStudents();
    Long addStudent(Student student);
    void deleteStudent(Long id);
    Student getStudent(Long id);
    void updateStudent(Student student, Long id);
}
