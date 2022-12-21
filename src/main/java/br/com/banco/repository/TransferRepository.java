package br.com.banco.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.banco.entities.Transfer;

public interface TransferRepository extends CrudRepository<Transfer, Integer> {

}
