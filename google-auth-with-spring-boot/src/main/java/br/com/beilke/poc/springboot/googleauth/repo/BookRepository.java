package br.com.beilke.poc.springboot.googleauth.repo;

import org.springframework.data.repository.CrudRepository;

import br.com.beilke.poc.springboot.googleauth.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
