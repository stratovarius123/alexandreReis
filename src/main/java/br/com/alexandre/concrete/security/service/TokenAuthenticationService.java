package br.com.alexandre.concrete.security.service;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


public class TokenAuthenticationService {

    static final long EXPIRATION_TIME =  1800000;
    static final String SECRET = "MySecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse response, String username) throws UnsupportedEncodingException {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes("UTF-8"))
                .compact();

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
    	String token = request.getHeader(HEADER_STRING);
    	try{
        if (token != null) {
            String user = Jwts.parser().setSigningKey(SECRET.getBytes("UTF-8")).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }
    	}catch(SignatureException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | UnsupportedEncodingException e){
    		return null;
    	}
    	return null;
    }
}
