package Entities;
import jdbc.GeneralDBMethods;
import org.hibernate.Session;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements SaveAndDelete {

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "User_Book",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "book_id") }
    )
    Set<Book> books = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int user_id;

    @Column(nullable = false, unique = true)
    private String email;

    private boolean is_active;

    private String name;

    private String phone_num;

    private String type;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String email, String name, String phone_num, String type) {
        this.email = email;
        this.is_active = true;
        this.name = name;
        this.phone_num = phone_num;
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        this.password = pbkdf2PasswordEncoder.encode(password);
    }


    public void delete(){
        Session session = GeneralDBMethods.get_session();

        try {
            session.beginTransaction();

            session.delete(this);

            session.getTransaction().commit();
        }
        finally {
            System.out.println("User deleted");
        }
    }


    public void save(){
        Session session = GeneralDBMethods.get_session();

        try {
            session.beginTransaction();

            session.save(this);
        }
        finally {
            System.out.println("User saved");
        }
    }
}
