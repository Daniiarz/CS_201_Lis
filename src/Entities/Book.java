package Entities;

import jdbc.GeneralDBMethods;
import org.hibernate.Session;

import javax.persistence.*;

@Entity
@Table(name="books")
public class Book implements SaveAndDelete{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int book_id;

    @ManyToOne
    @JoinColumn(name="genre_id", nullable=false)
    private Genre genre;

    @Column(name = "isbn")
    private int ISBN;

    @Column(name = "author")
    private String author;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "edition")
    private int edition;

    @Column(name = "booksAmount")
    private int BooksAmount;

    public Book(){

    }

    public Book(int ISBN, String author, String name, String status, int edition, int booksAmount) {
        this.ISBN = ISBN;
        this.author = author;
        this.name = name;
        this.status = status;
        this.edition = edition;
        BooksAmount = booksAmount;
    }

    public int getBooksAmount() {
        return BooksAmount;
    }

    public void setBooksAmount(int booksAmount) {
        BooksAmount = booksAmount;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }


    @Override
    public String toString() {
        return String.format("Book %s, %s", getISBN(), getName());
    }

    public int getId() {
        return book_id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void delete(){
        Session session = GeneralDBMethods.get_session();

        try {
            session.beginTransaction();

            session.delete(this);

            session.getTransaction().commit();
        }
        finally {
            System.out.println("Book Deleted");
        }
    }


    public void save(){
        Session session = GeneralDBMethods.get_session();

        try {
            session.beginTransaction();

            session.save(this);
        }
        finally {
            System.out.println("Book saved");
        }
    }

}
