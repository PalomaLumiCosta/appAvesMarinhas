# App Aves Marinhas

Aplicação web desenvolvida para apoiar o registro e o gerenciamento de censos e leituras de aves marinhas realizadas durante levantamentos e projetos de monitoramento.

O sistema foi desenvolvido em Java com Spring Boot como projeto 

## Objetivo

O projeto busca fornecer uma estrutura para registrar e organizar informações relacionadas ao monitoramento de aves marinhas, incluindo:

* censos;
* leituras de aves;
* leituras instantâneas;
* espécies;
* observadores;
* projetos;
* usuários.

A aplicação possui uma interface web desenvolvida com Thymeleaf e também controladores responsáveis por disponibilizar operações sobre os dados da aplicação.

## Tecnologias utilizadas

* Java 17
* Spring Boot 3.5.7
* Spring Web
* Spring Data JPA
* Hibernate
* Thymeleaf
* Bean Validation
* PostgreSQL
* Maven
* Springdoc OpenAPI
* Swagger UI
* JasperReports

## Estrutura do projeto

```text
appAvesMarinhas/
├── src/
│   ├── main/
│   │   ├── java/br/edu/ifrs/osorio/tads/palomalumi/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── PalomalumiApplication.java
│   │   │
│   │   └── resources/
│   │       ├── relatorios/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   │
│   └── test/
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Camadas da aplicação

### Model

Contém as entidades utilizadas para representar os dados do sistema.

Entre as principais classes estão:

* `Censo`
* `Especies`
* `Leitura`
* `LeituraInstantanea`
* `Observador`
* `Projeto`
* `Usuario`
* `TipoUsuario`
* `ExObservador`

### Repository

Camada responsável pelo acesso ao banco de dados com Spring Data JPA.

O projeto contém repositórios para:

* censos;
* espécies;
* leituras;
* leituras instantâneas;
* observadores;
* projetos;
* usuários.

### Service

Camada destinada à implementação das regras de negócio e à comunicação entre os controladores e os repositórios.

### Controller

A aplicação possui controladores para operações relacionadas a:

* censos;
* espécies;
* leituras;
* leituras instantâneas;
* observadores;
* projetos;
* usuários;
* formulários e páginas web.

Entre os controladores existentes estão:

```text
CensoController
CensoWebController
EspeciesController
FormController
LeituraController
LeituraInstantaneaController
ObservadorController
ProjetoController
UsuarioController
```

## Interface web

A aplicação utiliza Thymeleaf para renderizar páginas HTML no servidor.

Os templates disponíveis atualmente incluem:

```text
censo-form.html
censo-lista.html
leitura-form.html
leitura-lista.html
usuario-form.html
```

Essas páginas permitem trabalhar com formulários e listagens dos registros da aplicação.

## Relatórios

O projeto contém modelos de relatórios desenvolvidos com JasperReports.

Entre os arquivos disponíveis estão:

```text
Censos.jasper
Censos.jrxml
Especies.jrxml
lista_censos_param.jasper
lista_censos_param.jrxml
```

Os arquivos `.jrxml` contêm a definição dos relatórios, enquanto os arquivos `.jasper` correspondem aos relatórios compilados.

## Pré-requisitos

Para executar o projeto, é necessário ter instalado:

* Java JDK 17 ou superior;
* PostgreSQL;
* Git, para clonar o repositório;
* Maven, ou utilizar o Maven Wrapper incluído no projeto.

## Configuração do banco de dados

Crie um banco de dados PostgreSQL chamado:

```sql
CREATE DATABASE aves;
```

No arquivo:

```text
src/main/resources/application.properties
```

configure a conexão com o PostgreSQL:

```properties
spring.application.name=Palomalumi

spring.datasource.url=jdbc:postgresql://localhost:5432/aves
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false

server.port=8083
```

Não armazene usuários, senhas ou credenciais reais no repositório público. Para ambientes de produção, utilize variáveis de ambiente.

Exemplo:

```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

## Como executar

Clone o repositório:

```bash
git clone https://github.com/PalomaLumiCosta/appAvesMarinhas.git
```

Entre na pasta do projeto:

```bash
cd appAvesMarinhas
```

### Windows

Execute utilizando o Maven Wrapper:

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux ou macOS

Dê permissão de execução ao Maven Wrapper:

```bash
chmod +x mvnw
```

Depois execute:

```bash
./mvnw spring-boot:run
```

Também é possível gerar o pacote da aplicação:

```bash
./mvnw clean package
```

No Windows:

```powershell
.\mvnw.cmd clean package
```

O arquivo compilado será criado dentro da pasta:

```text
target/
```

## Acesso à aplicação

Após iniciar o projeto, a aplicação ficará disponível, por padrão, em:

```text
http://localhost:8083
```

## Documentação da API

O projeto utiliza Springdoc OpenAPI para gerar a documentação dos controladores da aplicação.

Com o sistema em execução, a interface do Swagger UI pode ser acessada em:

```text
http://localhost:8083/swagger-ui/index.html
```

A descrição OpenAPI em formato JSON normalmente pode ser consultada em:

```text
http://localhost:8083/v3/api-docs
```

## Banco de dados e persistência

A persistência é realizada com:

* Spring Data JPA;
* Hibernate;
* PostgreSQL.

A propriedade:

```properties
spring.jpa.hibernate.ddl-auto=update
```

faz com que o Hibernate atualize automaticamente a estrutura das tabelas de acordo com as entidades da aplicação durante o desenvolvimento.

Para ambientes de produção, recomenda-se utilizar uma ferramenta de migração de banco de dados, como Flyway ou Liquibase.

## Funcionalidades representadas no código

A estrutura atual do projeto contempla:

* cadastro e consulta de censos;
* cadastro e consulta de leituras;
* registro de leituras instantâneas;
* gerenciamento de espécies;
* gerenciamento de observadores;
* gerenciamento de projetos;
* gerenciamento de usuários;
* formulários e listagens web;
* persistência em PostgreSQL;
* documentação dos serviços com Swagger;
* modelos de relatórios JasperReports.

## Possíveis melhorias

Algumas melhorias que podem ser implementadas futuramente:

* autenticação e autorização com Spring Security;
* armazenamento seguro das credenciais;
* criação de perfis de administrador e observador;
* validação mais completa dos formulários;
* tratamento centralizado de exceções;
* testes unitários e testes de integração;
* paginação e filtros nas listagens;
* exportação de censos e leituras;
* geração de relatórios diretamente pela interface;
* utilização de DTOs nas APIs;
* implantação com Docker;
* migrações de banco com Flyway ou Liquibase.

## Autora

**Paloma Lumi Costa**

Estudante de Tecnologia em Análise e Desenvolvimento de Sistemas — IFRS Campus Osório.

GitHub: [PalomaLumiCosta](https://github.com/PalomaLumiCosta)

## Situação do projeto

Projeto  em desenvolvimento, criado como protótipo de backend e aplicação web para registro de censos de aves marinhas em levantamentos realizados no ambiente marinho. 
Protótipo de Front já realizado e disponível em /figures e em [(https://www.figma.com/proto/G8pWMGArDtT48PEqSGw2Eu/PrototypePalomaLumiCosta--Copy-?node-id=2-3&t=hXNHHtWy5UOglP2L-1)] .
