package br.com.banco.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.entities.Count;
import br.com.banco.repository.CountRepository;

@RestController
@RequestMapping("/counts")
public class CountRestController {

    @Autowired
    private CountRepository countRepository;

    @GetMapping
    public ResponseEntity<Iterable<Count>> findAll() {
        return ResponseEntity.ok(countRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Count>> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(countRepository.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{responsibleName}")
    public ResponseEntity<Count> create(@PathVariable String responsibleName) {
        try {
            Count count = new Count();
            count.setResponsibleName(responsibleName);
            countRepository.save(count);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Optional<Count>> delete(@RequestBody Count count) {
        try {
            countRepository.delete(count);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Count> update(@RequestBody Count count) {
        countRepository.save(count);
        return ResponseEntity.ok().build();
    }
}
