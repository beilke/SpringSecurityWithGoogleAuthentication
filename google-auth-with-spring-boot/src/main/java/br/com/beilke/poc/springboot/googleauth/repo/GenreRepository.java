package br.com.beilke.poc.springboot.googleauth.repo;

import org.springframework.data.repository.CrudRepository;

import br.com.beilke.poc.springboot.googleauth.model.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {

}
