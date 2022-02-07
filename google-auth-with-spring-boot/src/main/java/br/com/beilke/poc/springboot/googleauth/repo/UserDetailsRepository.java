package br.com.beilke.poc.springboot.googleauth.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.beilke.poc.springboot.googleauth.model.UserDetails;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {

    Optional<UserDetails> findByEmail(String email);

}
