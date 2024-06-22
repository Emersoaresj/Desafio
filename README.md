# Projeto Desafio
Este repositório contém um projeto desenvolvido para gerenciar informações de vendedores e filiais em uma aplicação Java utilizando Spring Boot e REST API.

# Estrutura do Projeto
O projeto está estruturado da seguinte forma:

- src/main/java/com/example/desafio: Contém o código-fonte da aplicação.
- api: Pacote com o MOCK da Filial.
- controller: Controlador REST que define endpoints para operações CRUD.
- dto: Data Transfer Objects (DTOs) usados para receber dados via API.
- model: Modelos de entidades JPA que representam as tabelas do banco de dados.
- repositories: Interfaces de repositório que lidam com operações de banco de dados.
- service: Classes de serviço que implementam a lógica de negócio da aplicação.
- src/test/java/com/example/desafio: Contém os testes unitários e de integração da aplicação.

# Tecnologias Utilizadas

- Spring Boot: Framework para desenvolvimento de aplicações Java baseadas em Spring.
- PostgreSQL: Banco de dados em memória para desenvolvimento e testes.
- JUnit e Mockito: Ferramentas para testes unitários e mock de dependências.
- Maven: Gerenciador de dependências e build do projeto.

# Funcionalidades
O projeto oferece as seguintes funcionalidades:

- Cadastro de Vendedores: Permite criar novos vendedores associados a uma filial.
- Atualização de Vendedores: Permite atualizar informações de vendedores existentes.
- Listagem de Vendedores: Retorna a lista de todos os vendedores cadastrados.
- Exclusão de Vendedores: Remove vendedores do sistema.
- Validações: Utiliza validações de entrada usando anotações como @Valid e validações customizadas.

# Execução do Projeto
- Para executar o projeto localmente: git clone https://github.com/Emersoaresj/desafio.git

# Considerações Finais
Este projeto foi desenvolvido como parte de um desafio para aprimorar habilidades em desenvolvimento Java com Spring Boot, incluindo boas práticas de programação, testes automatizados e integração com banco de dados. Qualquer dúvida ou sugestão, sinta-se à vontade para entrar em contato.
