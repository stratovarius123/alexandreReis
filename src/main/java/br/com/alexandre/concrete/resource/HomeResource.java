package br.com.alexandre.concrete.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	   
	@RequestMapping("/home")
	public String hello() {
	        return "Se voce conseguiu acessar, Logo então está autenticado e ocorreu tudo certo! 'Que a força esteja conosco!'";
	}
}