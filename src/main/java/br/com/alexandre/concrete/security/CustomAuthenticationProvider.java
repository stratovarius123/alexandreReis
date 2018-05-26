package br.com.alexandre.concrete.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.alexandre.concrete.domain.Usuario;
import br.com.alexandre.concrete.service.UsuarioService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

 @Autowired
 UsuarioService userService;

 @Override
 public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	Usuario user = userService.findByEmail(authentication.getName());
		if (user != null) {

			String password = (String) authentication.getCredentials();
			if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
				throw new BadCredentialsException("Senha invalida!");
			}
			return new UsernamePasswordAuthenticationToken(user.getEmail(), password, new ArrayList<>());
		}
	else {
		throw new BadCredentialsException("Usuario e/ou senha invalidos");
	}
 }

 @Override
 public boolean supports(Class<?> authentication) {
	return authentication.equals(UsernamePasswordAuthenticationToken.class);
 }

}
