package br.com.Alexandre.Concrete;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alexandre.concrete.ErrorKeys.ErrorKey;
import br.com.alexandre.concrete.domain.Usuario;
import br.com.alexandre.concrete.resource.UsuarioResource;
import br.com.alexandre.concrete.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcreteApplicationTests {

	private final Integer id_user = 1;
	
	Usuario user;

	@Mock
	UsuarioService userService;

	@InjectMocks
	UsuarioResource userResourse;

	@Before
	public void setUp() {
		user = new Usuario();
	}

	@Test
	public void deveRetornarUmusuarioPeloId() {
		when(userService.findById(id_user)).thenReturn(user);
		ResponseEntity<Usuario> userResponse = userResourse.find(id_user);
		assertNotNull(userResponse);
	}
	@Test
	public void deveDeletarUmUsuario() {
		ResponseEntity<Void> userResponse = userResourse.delete(id_user);
		assertNotNull(userResponse);
	}
	
	@Test
	public void deveAtualizarUmUsurio() {
		when(userService.findById(id_user)).thenReturn(user);
		ResponseEntity<Usuario> userResponse = userResourse.find(id_user);
		assertNotNull(userResponse);
	}

	@Test
	public void deveValidarExceptionEmailExistente() throws JSONException {
		ResponseEntity<ErrorKey> erro = userResourse.handleEmailExistException();
		assertNotNull(erro);
	}

	@Test
	public void deveValidarExceptionUsuarioNaoEncontrado() throws JSONException {
		ResponseEntity<ErrorKey> erro = userResourse.handleUserNotFoundException();
		assertNotNull(erro);
	}

}
