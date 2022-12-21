package br.com.banco.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.banco.entities.Count;

public interface CountRepository extends CrudRepository<Count, Integer> {

}
