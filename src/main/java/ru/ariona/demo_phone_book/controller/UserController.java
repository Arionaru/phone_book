package ru.ariona.demo_phone_book.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ariona.demo_phone_book.domen.Node;
import ru.ariona.demo_phone_book.domen.User;
import ru.ariona.demo_phone_book.service.NodeService;
import ru.ariona.demo_phone_book.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private NodeService nodeService;

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String name) {
        if (name == null) {
            return userService.findAll();
        }
        return userService.findByNameContains(name);
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable("id") User user) {
        return user;
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User edit(@PathVariable("id") User userFromDB, @RequestBody User user) {
        BeanUtils.copyProperties(user,userFromDB,"id");
        return userService.save(userFromDB);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") User user) {
        List<Node> nodes = nodeService.findByUser(user);
        nodeService.deleteAll(nodes);
        userService.delete(user);
    }

    @GetMapping("/nodes/{id}")
    public List<Node> userNodes(@PathVariable("id") User user) {
        return nodeService.findByUser(user);
    }
}
