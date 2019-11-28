package ru.ariona.demo_phone_book.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ariona.demo_phone_book.domen.Node;
import ru.ariona.demo_phone_book.service.NodeService;

@RestController
@RequestMapping("api/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @GetMapping
    public Node findByNumber(@RequestParam(required = false) String number) {
        return nodeService.findByNumber(number);
    }

    @GetMapping("/{id}")
    public Node getOne(@PathVariable("id") Node node) {
        return node;
    }

    @PostMapping
    public Node save(@RequestBody Node node) {
        return nodeService.save(node);
    }

    @PutMapping("/{id}")
    public Node edit(@PathVariable("id") Node nodeFromDB, @RequestBody Node node) {
        BeanUtils.copyProperties(node,nodeFromDB,"id");
        return nodeService.save(nodeFromDB);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Node node) {
        nodeService.delete(node);
    }
}
