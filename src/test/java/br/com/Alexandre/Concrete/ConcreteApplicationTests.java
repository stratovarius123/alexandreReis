package br.com.Alexandre.Concrete;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alexandre.concrete.domain.Usuario;
import br.com.alexandre.concrete.resource.UsuarioResource;
import br.com.alexandre.concrete.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcreteApplicationTests {

	private final int id = 1;

	Usuario user = new Usuario();

	@Mock
	UsuarioService userService;

	@InjectMocks
	UsuarioResource userResourse;

	@Test
	public void deveAtualizarUmUsurioValido() {
		when(userService.findById(id)).thenReturn(user);
		ResponseEntity<Usuario> userResponse = userResourse.find(id);
		assertNotNull(userResponse);
	}
	
	@Test
	public void deveRetornarUmusuario() {
		when(userService.findById(id)).thenReturn(user);
		ResponseEntity<Usuario> userResponse = userResourse.find(id);
		assertNotNull(userResponse);
	}
	
	@Test
	public void deveDeletar() {
		ResponseEntity<Void> userResponse = userResourse.delete(id);
		assertNotNull(userResponse);
	}

}
