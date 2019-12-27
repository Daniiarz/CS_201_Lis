package Entities;

import javax.persistence.*;

@Entity
@Table(name="librarians")
public class Librarian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;

    private String phone_num;

    public Librarian() {
    }

    public Librarian(String name, String phone_num) {
        this.name = name;
        this.phone_num = phone_num;
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

    public int getId() {
        return id;
    }
}
