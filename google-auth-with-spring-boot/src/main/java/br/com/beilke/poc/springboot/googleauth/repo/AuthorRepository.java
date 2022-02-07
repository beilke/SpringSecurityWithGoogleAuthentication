package br.com.beilke.poc.springboot.googleauth.repo;

import org.springframework.data.repository.CrudRepository;

import br.com.beilke.poc.springboot.googleauth.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
