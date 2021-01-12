package StudentsStuff.db;

import StudentsStuff.Student;
import StudentsStuff.StudentsController;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DBManager implements StudentsController {
    private Session session;
    public DBManager(){
        if(getStudents().size() == 0) {
            addStudent(new Student("Ivanov Ivan Ivanovich", 20));
            addStudent(new Student("Ivanov Ivan", 22));
            addStudent(new Student("Petrov Petr", 21));
            addStudent(new Student("Sidorov Pavel", 23));
        }
    }


    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);
        CriteriaQuery<Student> resCQ= cq.select(root);
        TypedQuery<Student> studentQuery = session.createQuery(resCQ);
        students = studentQuery.getResultList();
        tr.commit();
        session.close();
        return students;
    }


    public Long addStudent(Student student) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.save(student);
        tr.commit();
        session.close();
        return student.getId();
    }


    public void deleteStudent(Long id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        Student st = session.get(Student.class, id);
        session.delete(st);
        tr.commit();
        session.close();
    }


    public Student getStudent(Long id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        Student st = session.get(Student.class, id);
        tr.commit();
        session.close();
        return st;
    }


    public void updateStudent(Student student, Long id) {
        student.setId(id);
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.update(student);
        tr.commit();
        session.close();
    }
}
