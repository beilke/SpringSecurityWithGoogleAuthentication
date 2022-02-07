package br.com.beilke.poc.springboot.googleauth.config;

import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import br.com.beilke.poc.springboot.googleauth.model.UserDetails;
import br.com.beilke.poc.springboot.googleauth.repo.UserDetailsRepository;
import br.com.beilke.poc.springboot.googleauth.service.GoogleTokenVerifier;

public class GoogleAuthenticationManager implements AuthenticationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleAuthenticationManager.class);

    private final UserDetailsRepository userDetailsRepository;

    public GoogleAuthenticationManager(UserDetailsRepository userDetailsRepository) {
	this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) {
	LOGGER.info("start authentication...");

	Payload payload = new GoogleTokenVerifier().verifyToken((String) authentication.getCredentials());

	String email = payload.getEmail();

	Optional<UserDetails> optUserDetails = userDetailsRepository.findByEmail(email);

	if (optUserDetails.isEmpty()) {
		LOGGER.warn("User not registered: "+email);
	    throw new AuthenticationServiceException("User not registered");
	}

	UserDetails userDetails = optUserDetails.get();

	return new UsernamePasswordAuthenticationToken(userDetails, "DontBotherBro",
		Arrays.asList(new SimpleGrantedAuthority(userDetails.getRole().name())));
    }

}
