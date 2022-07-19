# Desafio

Projeto API desafio CWI Software para utilização como exemplo base.
           
## Requisitos funcionais

- Manter informação de usuários (adição, alteração e remoção)
     - O campo 'Nome' deve possuir mais de uma palavra
     - O campo 'CEP' deve ser válidado
     - O campo 'E-mail' deve conter o caractere @
     - O campo 'Nascimento' não pode ser maior que a data atual
     - Devem ser obrigatórios os campos 'Nome', 'CEP' e 'E-mail'
- Permitir a visualização e pesquisa de usuários cadastrados
- Manter controle de acesso via tela de login
- Permitir a realização de logout
- Manter log de operaçõesde de login em banco de dados
- Permitir a visualização de logs do sistema
- Disponibilizar Swagger

## Requisitos não funcionais
- Utilizar criptografia nas senhas de usuários
- Disponibilizar API REST para todos os recursos 
- Disponibilizar camada frontend (web) para todos os recursos
     - Não aplicar estilo nas páginas
- A validação de CEP deve ser realizada o seguinte cliente externo REST https://viacep.com.br/ws
 
### Endereços

- **Documentação Swagger: ** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **Banco de dados H2: ** [http://localhost:8080/h2/](http://localhost:8080/h2/)
- **Página Web: ** [http://localhost:8080/login](http://localhost:8080/login)
  
### Inicialização
```shell
mvn clean install spring-boot: run
```
### Tecnologias utilizadas
- Java 11
- Spring boot
- Spring Security
- Spring Fox
- H2
- Lombok
- JUnit
- Mockito