package StudentsStuff;

import java.util.ArrayList;
import java.util.List;

public class StudentManager implements StudentsController {
    private static final StudentManager INSTANCE = new StudentManager();

    private List<Student> students = new ArrayList<>();

    private StudentManager(){
        students.add(new Student("Ivanov Ivan Ivanovich", 20));
        students.add(new Student("Ivanov Ivan", 22));
        students.add(new Student("Petrov Petr", 21));
        students.add(new Student("Sidorov Pavel", 23));
    };

    @Override
    public List<Student> getStudents() {
        return students;
    }
    @Override
    public Long addStudent(Student student){
        students.add(student);
        return null;
    }
    @Override
    public void deleteStudent(Long id){
        students.remove(id.intValue());
    }
    @Override
    public Student getStudent(Long id){
        return students.get(id.intValue());
    }

    @Override
    public void updateStudent(Student student, Long id){
        Student st = students.get(id.intValue());
        st.setAge(student.getAge());
        st.setFIO(student.getFIO());
    }

    public static StudentManager getInstance(){
        return INSTANCE;
    }
}
