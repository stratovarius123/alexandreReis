# Api Rest

 1. Cadastrar o usu�rio na API utilizando o m�todo POST
  https://alexandrereis.herokuapp.com/user

Exemplo:
> {	 "name": "Alexandre Reis",
>      "email": "alexandre.jj.reis@gmail.com",
>      "password": "teste123",
>      "telefones": [
>       {
>        "number": "3596-7612",
>        "ddd": "13"
>        }
>      ] 
> }

 2. Via o m�todo POST logar com o usu�rio que acabou de criar
 https://alexandrereis.herokuapp.com/login
 Exemplo:

> { "email": "alexandre.jj.reis@gmail.com", "password": "teste123" }

obs.: ir� retornar nos headers o token no atributo Authorization que no meu caso ser� "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4YW5kcmUuamoucmVpc0BnbWFpbC5jb20iLCJleHAiOjE1MjczODg0MzF9.zTjJmXCnkQSnwGTEGnhgPEMJUhiFXQJEyKV0K1AssuY1nIt_RCJYSRtQ_1cUYu_Z7E8vcyUR4xibDKXYIowtQA"

 3. Via Post  deveremos configurar no nosso header o token que retornou na requisi��o anterior, que no caso do Postman fica no Hearders devendo setar o atributo Authorization aonde est� escrito new key  e em value o token gerado "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4YW5kcmUuamoucmVpc0BnbWFpbC5jb20iLCJleHAiOjE1MjczODg0MzF9.zTjJmXCnkQSnwGTEGnhgPEMJUhiFXQJEyKV0K1AssuY1nIt_RCJYSRtQ_1cUYu_Z7E8vcyUR4xibDKXYIowtQA"

	https://alexandrereis.herokuapp.com/home

 4.Se tudo ocorrer da maneira que esperamos deverar apacerecer uma mensagem.
'Se voce conseguiu acessar, logo ent�o est� autenticado e ocorreu tudo certo! 'Que a for�a esteja conosco!'


Observa��o Geral: Lembrando que tamb�m possu�mos os m�todos de editar, listar por id e remover  todos est�o mapeados, caso passe de 30 minutos de utiliza��o do mesmo token, ele ir� expirar assim tendo que gerar outro, pois n�o irar permitir acessar a pagina home.
