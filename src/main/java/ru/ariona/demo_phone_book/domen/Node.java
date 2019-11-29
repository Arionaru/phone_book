package ru.ariona.demo_phone_book.domen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Node {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    private String name;

    private String number;

    public Node() {
    }

    public Node(User user, String name, String number) {
        this.user = user;
        this.name = name;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }
}
