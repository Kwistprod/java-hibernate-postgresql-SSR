package StudentsStuff;

import javax.persistence.*;
import java.util.Objects;

@Entity(name="student")
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String FIO;

    @Column(nullable = false)
    private int age;
    public Student(){};
    public Student(String fio, int age){
        this.FIO = fio;
        this.age = age;
    };

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString(){
        return "fio - " + FIO + " | age - " + age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                id.equals(student.id) &&
                FIO.equals(student.FIO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, FIO, age);
    }
}
