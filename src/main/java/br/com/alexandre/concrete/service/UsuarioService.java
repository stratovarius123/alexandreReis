package br.com.alexandre.concrete.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alexandre.concrete.domain.Usuario;
import br.com.alexandre.concrete.exception.EmailExistException;
import br.com.alexandre.concrete.exception.UserNotFoundException;
import br.com.alexandre.concrete.repositories.TelefoneRepository;
import br.com.alexandre.concrete.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository userRepository;
	
	@Autowired
	TelefoneRepository phoneRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Usuario insert(Usuario user){
		if(!userRepository.isEmailUserExist(user.getEmail())) {
			user.setCreationDate(LocalDateTime.now());
			user.setLastLogin(LocalDateTime.now());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user = userRepository.save(user);
		}
		else {
			throw new EmailExistException();
		}
		return user;
	}
	
	public Usuario update(Usuario user){
		
		Optional<Usuario> userById = userRepository.findById(user.getId());
		
		user.setCreationDate(userById.get().getCreationDate());
		user.setLastLogin(userById.get().getLastLogin());
		user.setAlterationDate(LocalDateTime.now());
		
		if(user.getPassword() != null ) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		if(user.getTelefones().isEmpty()) {
			user.setTelefones(userById.get().getTelefones());
		}else {
			user.setTelefones(user.getTelefones());
		}
		
		return userRepository.save(user);
	}
	
	public void delete(Integer id){
		userRepository.deleteById(id);
	}
	
	public Usuario findById(Integer id) {
		Optional<Usuario> user = userRepository.findById(id);
		return user.orElseThrow(() -> new UserNotFoundException());
	}

	public Usuario findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
