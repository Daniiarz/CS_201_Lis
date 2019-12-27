package jdbc;

import Entities.Book;
import Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDriver {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<Book> books = session.createQuery("from Book").list();
            Set<Book> bookSet = new HashSet<>(books);

            User user = new User("daniyarflash.m01@gmail.com", "Daniiar Mukash uulu", "0777954456", "Student");
            user.setBooks(bookSet);
            user.setPassword("Cool Pass");
            session.persist(user);

            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
