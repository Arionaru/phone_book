package ru.ariona.demo_phone_book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ariona.demo_phone_book.domen.User;
import ru.ariona.demo_phone_book.repo.UserRepo;

import java.util.List;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public <S extends User> S save(S s) {
        return userRepo.save(s);
    }

    public List<User> findByNameContains(String name) {
        return userRepo.findByNameContainsIgnoreCase(name);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }
}
