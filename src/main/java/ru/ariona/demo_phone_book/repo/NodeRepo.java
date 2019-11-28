package ru.ariona.demo_phone_book.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ariona.demo_phone_book.domen.Node;
import ru.ariona.demo_phone_book.domen.User;

import java.util.List;

public interface NodeRepo extends JpaRepository<Node,Long> {

    Node findByNumber(String number);

    List<Node> findByUser(User user);
}
