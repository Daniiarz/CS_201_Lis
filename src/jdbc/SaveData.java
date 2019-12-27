package jdbc;

import Entities.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SaveData {


    public static void main(String[] args){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            Book book = new Book(1234, "aaa1", "agsdfg", "Available", 1, 3);

            session.beginTransaction();

            session.save(book);

            session.getTransaction().commit();

            System.out.println("Saved book");
            System.out.println(book);

            session = factory.getCurrentSession();
            session.beginTransaction();

            Book myBook = session.get(Book.class, 1);

            System.out.println("Get complete " + myBook);

            session.getTransaction().commit();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
