# Api Rest

 1. Cadastrar o usuário na API utilizando o método POST
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

 2. Via o método POST logar com o usuário que acabou de criar
 https://alexandrereis.herokuapp.com/login
 Exemplo:

> { "email": "alexandre.jj.reis@gmail.com", "password": "teste123" }

obs.: irá retornar nos headers o token no atributo Authorization que no meu caso será "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4YW5kcmUuamoucmVpc0BnbWFpbC5jb20iLCJleHAiOjE1MjczODg0MzF9.zTjJmXCnkQSnwGTEGnhgPEMJUhiFXQJEyKV0K1AssuY1nIt_RCJYSRtQ_1cUYu_Z7E8vcyUR4xibDKXYIowtQA"

 3. Via Post  deveremos configurar no nosso header o token que retornou na requisição anterior, que no caso do Postman fica no Hearders devendo setar o atributo Authorization aonde está escrito new key  e em value o token gerado "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4YW5kcmUuamoucmVpc0BnbWFpbC5jb20iLCJleHAiOjE1MjczODg0MzF9.zTjJmXCnkQSnwGTEGnhgPEMJUhiFXQJEyKV0K1AssuY1nIt_RCJYSRtQ_1cUYu_Z7E8vcyUR4xibDKXYIowtQA"

	https://alexandrereis.herokuapp.com/home

 4.Se tudo ocorrer da maneira que esperamos deverar apacerecer uma mensagem.
'Se voce conseguiu acessar, logo então está autenticado e ocorreu tudo certo! 'Que a força esteja conosco!'


Observação Geral: Lembrando que também possuímos os métodos de editar, listar por id e remover  todos estão mapeados, caso passe de 30 minutos de utilização do mesmo token, ele irá expirar assim tendo que gerar outro, pois não irar permitir acessar a pagina home.
