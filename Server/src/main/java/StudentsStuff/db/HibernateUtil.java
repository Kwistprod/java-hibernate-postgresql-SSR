package StudentsStuff.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSession();

    private HibernateUtil(){ };
    private static SessionFactory buildSession(){
        SessionFactory sess = null;
        try {
            sess = new Configuration().configure().buildSessionFactory();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return sess;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
