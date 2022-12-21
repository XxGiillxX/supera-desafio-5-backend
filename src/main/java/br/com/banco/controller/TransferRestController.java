package br.com.banco.controller;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.entities.Count;
import br.com.banco.entities.RequestParamsTransfer;
import br.com.banco.entities.Transfer;
import br.com.banco.repository.CountRepository;
import br.com.banco.repository.TransferRepository;

@RestController
@RequestMapping("/transfers")
public class TransferRestController {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    CountRepository countRepository;

    @GetMapping
    public ResponseEntity<Iterable<Transfer>> findAll() {
        return ResponseEntity.ok(transferRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transfer>> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(transferRepository.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Transfer> create(@RequestBody RequestParamsTransfer paramsTransfer) {
        try {
            Transfer transfer = new Transfer();
            transfer.setTransferDate(LocalDateTime.now());
            transfer.setTransactionOperator(paramsTransfer.getTransactionOperator());
            transfer.setType(paramsTransfer.getType());
            transfer.setValue(paramsTransfer.getValue());
            Optional<Count> count = countRepository.findById(paramsTransfer.getIdCount());
            transfer.setCount(count.get());
            transferRepository.save(transfer);
            return ResponseEntity.ok(transfer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Transfer>> delete(@PathVariable Integer id) {
        try {
            transferRepository.deleteById(transferRepository.findById(id).get().getId());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
