package ru.ariona.demo_phone_book.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ariona.demo_phone_book.domen.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    List<User> findByNameContainsIgnoreCase(String name);

}
