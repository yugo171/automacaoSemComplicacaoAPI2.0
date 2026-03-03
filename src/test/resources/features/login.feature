#language:pt

Funcionalidade: Realizar login
  Testes da API de login

  Cenario: Realizar login com Sucesso
    Dado que tanha um payload valido da API de login
    Quando envio uma requisicao do tipo Post de login
    Entao valido que recebo status 200 no response
    E armazeno o token que recebo do response de Login

  Cenario: Realizar login com usuario invalido
    Dado que tenha um payload da API de login com as seguintes informacoes
      | email              | inválido@email.com |
      | senha              | 123456             |
    Quando envio uma requisicao do tipo Post de login
    Entao valido que recebo status 400 no response