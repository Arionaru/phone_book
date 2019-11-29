package ru.ariona.demo_phone_book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ariona.demo_phone_book.domen.Node;
import ru.ariona.demo_phone_book.domen.User;
import ru.ariona.demo_phone_book.repo.NodeRepo;

import java.util.List;

@Service
public class NodeService {

    @Autowired
    private NodeRepo nodeRepo;

    public <S extends Node> S save(S s) {
        return nodeRepo.save(s);
    }

    public Node findByNumber(String number) {
        return nodeRepo.findByNumber(number);
    }

    public List<Node> findByUser(User user) {
        return nodeRepo.findByUser(user);
    }

    public void delete(Node node) {
        nodeRepo.delete(node);
    }

    public void deleteAll(Iterable<? extends Node> iterable) {
        nodeRepo.deleteAll(iterable);
    }
}
