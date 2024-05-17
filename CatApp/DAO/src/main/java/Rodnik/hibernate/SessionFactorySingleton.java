package Rodnik.hibernate;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactorySingleton {
    @Getter
    private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public static SessionFactory getInstance() {
        return sessionFactory;
    }
    public static void close() {
        sessionFactory.close();
    }
}

