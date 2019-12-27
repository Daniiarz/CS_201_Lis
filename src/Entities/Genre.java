package Entities;
import jdbc.GeneralDBMethods;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="librarians")
public class Genre implements SaveAndDelete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genre_id;

    @OneToMany(mappedBy="genre")
    private Set<Book> books;

    private String name;

    public Genre(){

    }

    public int getId() {
        return genre_id;
    }

    public Genre(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void delete(){
        Session session = GeneralDBMethods.get_session();

        try {
            session.beginTransaction();

            session.delete(this);

            session.getTransaction().commit();
        }
        finally {
            System.out.println("Genre deleted");
        }
    }


    public void save(){
        Session session = GeneralDBMethods.get_session();

        try {
            session.beginTransaction();

            session.save(this);
        }
        finally {
            System.out.println("Genre saved");
        }
    }
}
