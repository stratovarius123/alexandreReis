package br.com.alexandre.concrete.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alexandre.concrete.domain.Usuario;
import br.com.alexandre.concrete.service.UsuarioService;

@RestController
@RequestMapping(value="/user")
public class UsuarioResource {
	
	@Autowired
	UsuarioService userService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Usuario user){
		user = userService.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id){
		Usuario user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}

	@RequestMapping(value="/{id}" , method=RequestMethod.PUT)
	public ResponseEntity<Usuario> update(@RequestBody Usuario user, @PathVariable Integer id){
		user.setId(id);
		user = userService.update(user);
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		userService.delete(id);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
}
