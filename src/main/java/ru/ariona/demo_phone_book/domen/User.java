package ru.ariona.demo_phone_book.domen;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "usr")
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @JsonIgnore
    @ElementCollection(targetClass = Node.class, fetch = FetchType.EAGER)
    private List<Node> nodes;

    public User() {
    }

    public User(String name) {
        this.name = name;
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

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
