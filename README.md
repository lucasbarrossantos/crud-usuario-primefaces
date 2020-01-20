# Sistema para Cadastro de Usuários

## Módulos
- [x] Usuario
- [x] Telefone

## IDE
- Eclipse

## Tecnologias
* Maven
* Java 8
* JSF 2.2
* Primefaces 7.0
* Hibernate 5

## Servidor
* Tomcat 7

## Banco de Dados
* H2 - `Em disco`

## Atores

* Usuário `Admin`

# Estórias de Usuários
#### Como usuário `Admin` eu quero entrar no sistema e incluir um novo usuário
* Escopo <br/>
  - Usuário faz login e acessa a tela `Novo Usuário` no menu `Início -> Cadastros -> Usuário`

#### Como usuário `Admin` eu quero entrar no sistema e editar um novo usuário
* Escopo <br/>
  - Usuário faz login e acessa a tela `Pesquisa de Usuários` no menu `Início -> Pesauisar -> Usuários` faz a pesquisa, seleciona o usuário e edita os dados na tela de `Cadastro de Usuário`

#### Como usuário `Admin` eu quero entrar no sistema e pesquisar os usuários
* Escopo <br/>
  - Usuário faz login e acessa a tela `Pesquisa de Usuários` no menu `Início -> Pesauisar -> Usuários` e faz a pesquisa por `Nome e/ou E-mail`

#### Como usuário `Admin` eu quero entrar no sistema e excluir um usuário
* Escopo <br/>
  - Usuário faz login e acessa a tela `Pesquisa de Usuários` no menu `Início -> Pesauisar -> Usuários` e faz a pesquisa por `Nome e/ou E-mail`, após a pesquisa o usuário clica no botão de excluir onde vai aparecer um popup para a confirmação da ação.


#### Como usuário `Admin` eu quero entrar no sistema e incluir um novo telefone para um usuário
* Escopo <br/>
  - Usuário faz login e acessa a tela `Pesquisa de Usuários` no menu `Início -> Pesauisar -> Usuários` e faz a pesquisa por `Nome e/ou E-mail`, após a pesquisa o usuário clica no botão de editar e é redirecionado para a tela de `Cadastro de Usuário` onde vai aparecer os dados do usuário selecionado, o usuário clica no botão `Adicionar Telefone` e aparece um modal para ele por os dados do telefone do usuário e clica em Incluir.

#### Como usuário `Admin` eu quero entrar no sistema e editar um telefone do usuário
* Escopo <br/>
  - Usuário faz login e acessa a tela `Pesquisa de Usuários` no menu `Início -> Pesauisar -> Usuários` e faz a pesquisa por `Nome e/ou E-mail`, após a pesquisa o usuário clica no botão de editar e é redirecionado para a tela de `Cadastro de Usuário` onde vai aparecer os dados do usuário selecionado, se o usuário tiver telefones, então os telefones são listados na tabela de Telefones, o usuário clica em `Editar`, aparece um modal e o usuário consegue atualizar os dados do telefone.
  
 
## Start
Após clonar este repositório execute o clean e install em sua ide para que as dependências do projeto sejam baixadas. No meu caso como utilizo o Exclipse, é só ir em <br/>
* `Run As -> Maven clean` [Exemplo](https://raw.githubusercontent.com/lucasbarrossantos/crud-usuario-primefaces/master/WebContent/resources/images/mavenClean.png)  <br/> 
* `Run As -> Maven install`

Em seguida vá em: 

* ` Properties -> Deployment Assembly ` e certifique-se de que a pasta `WebContent` está lá e `Maven Dependecies` [Exemplo](https://raw.githubusercontent.com/lucasbarrossantos/crud-usuario-primefaces/master/WebContent/resources/images/webDeploymentAssembly.png)

# Imagens do Sistema
* [Login](https://raw.githubusercontent.com/lucasbarrossantos/crud-usuario-primefaces/master/WebContent/resources/images/login.png)  <br/> 
* [Listagem de Usuários](https://raw.githubusercontent.com/lucasbarrossantos/crud-usuario-primefaces/master/WebContent/resources/images/pesquisa-de-usuarios.png)  <br/> 
* [Editar Usuário](https://raw.githubusercontent.com/lucasbarrossantos/crud-usuario-primefaces/master/WebContent/resources/images/edit-usuario.png)  <br/> 

## URL
Após executar a aplicação é só acessar o [Login](http://localhost:8080/CrudLogin/login.xhtml)
