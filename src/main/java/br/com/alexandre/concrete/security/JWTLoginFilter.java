package br.com.alexandre.concrete.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.alexandre.concrete.domain.Usuario;
import br.com.alexandre.concrete.resource.exceptions.AuthenticationError;
import br.com.alexandre.concrete.security.service.TokenAuthenticationService;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{

	private static final String APPLICATION_JSON = "application/json";
	
	protected JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		Usuario credentials = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
	    return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(), Collections.emptyList()));
	}
	
	@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication auth) throws IOException, ServletException {
        TokenAuthenticationService.addAuthentication(response, auth.getName());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        this.onAuthenticationFailure(response, exception);
    }

		 
    public void onAuthenticationFailure(HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    	response.setStatus(HttpStatus.UNAUTHORIZED.value());
    	response.setContentType(APPLICATION_JSON); 
    	response.getWriter().append(json());
    }

    private String json() {
        Gson gson = new Gson();
        AuthenticationError error = new AuthenticationError(HttpStatus.UNAUTHORIZED.value(),"Não Autorizado","Usuario e/ou Senha Inválidos","/login");
        String json = gson.toJson(error);
        return json;
    }
}
