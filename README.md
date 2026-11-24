**AppRH**

O AppRH é um sistema que desenvolvi na materia de Desenvolvimento de Sistemas Corporativos, para o cadastro e a listagem de funcionários, com login e controle de acesso.
Utilizei o projeto para implementar testes automatizados, integração contínua e deploy automático.

**Tecnologias utilizadas**

Java 25, Spring Boot, Maven, MariaDB, HTML, CSS, JavaScript, JUnit, Mockito, Node.js, Git, GitHub, GitHub Actions, Docker, Render e Enzonic Databases.

**Como executar o projeto**

É necessário ter instalado o Java 25, Maven, MariaDB, Git e Node.js 24.

Abra o CMD do Windows e execute os comandos abaixo para baixar o projeto e acessar sua pasta:

*git clone https://github.com/RobertaTargino/AppRH-CI-CD.git
cd AppRH-CI-CD*

**Configuração do banco de dados**

Abra o MariaDB e execute o comando para criar o banco:


*CREATE DATABASE apprh;*

Utilizei a porta 3307 do MariaDB. Caso esteja utilizando outra porta, altere essa informação no arquivo `src/main/resources/application.properties`.

Volte ao CMD e configure o usuário e a senha do seu MariaDB:

*set DB_USERNAME=root
set DB_PASSWORD=sua_senha*

Substitua os valores pelas seus dados. 
**Iniciando o sistema**

No mesmo CMD, execute:

*mvn spring-boot:run*

As tabelas serão criadas automaticamente pelo Hibernate. Após a inicialização, mantenha o CMD aberto e acesse pelo navegador:

*http://localhost:8080/login.html*

Para realizar o primeiro login, é necessário cadastrar um usuário no banco com senha criptografada utilizando BCrypt.

**Testes do backend**

Utilizei JUnit e Mockito para verificar o login com senha correta e incorreta.

Para executar os testes, abra outro CMD na pasta do projeto e utilize:

*mvn -Dtest=AuthServiceTest test*

**Testes do frontend**

Utilizei o Node.js para testar o redirecionamento após o login e o comportamento do formulário de cadastro de funcionários, simulando a interface e as respostas do backend.

Para executar os testes:

*node --test frontend-testes/login.test.js frontend-testes/cadastro.test.js*

Os resultados dos testes podem ser visualizados no próprio terminal.

**Integração contínua (CI)**

Configurei o GitHub Actions por meio do arquivo *.github/workflows/ci.yml*, que executa automaticamente os testes do backend e do frontend sempre que uma alteração é enviada para a branch main.

**Deploy automático (CD)**

Utilizei o Render para hospedar a aplicação, o Docker para preparar o ambiente de execução e o Enzonic para hospedar o banco de dados.

Configurei o deploy automático para acontecer somente após a aprovação dos testes do GitHub Actions.
