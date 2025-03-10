# Sobre o projeto  
Essa aplicação REST é um projeto de web services com Spring Boot e MongoDB, onde é colocada em prática a construção de uma API baseada em um banco de dados não relacional, no caso, o MongoDB, que é um banco de dados orientado a documentos.  

## Modelo conceitual  

![image](https://github.com/user-attachments/assets/47de915e-fe79-4ee5-8e43-97cd0393b15e)  

## Tecnologias utilizadas  
- MongoDB  
- Java  
- Spring Boot  
- Maven  

## Como executar o projeto  
**Pré-requisitos:** Java 21+ | MongoDB  
MongoDB Setup: [MongoDB Community Server](https://www.mongodb.com/try/download/community)  
```properties
# ".../application.properties"
# Uma vez que o servidor MongoDB estiver rodando,
# o app tentará se conectar ao DB na seguinte URL (ajuste se necessário):

spring.data.mongodb.uri=mongodb://localhost:27017/workshop_mongo
```  
**App setup:**  
```bash
# Clonar repositório
git clone https://github.com/gustavostolze/workshop-springboot3-jpa.git

# Entrar na pasta raiz do projeto

# Executar o projeto
./mvnw spring-boot:run
```  

## Como testar as requisições  
**Endpoints disponíveis:**  
- **GET**  
  - `/users` (retorna todos os usuários)  
  - `/users/{id}` (retorna usuário por ID)  
  - `/users/{id}/posts` (retorna os posts de um usuário)  
  - `/posts/{id}` (retorna um post pelo seu ID)  
  - `/posts/titlesearch` (retorna um post onde o título está presente no parâmetro de texto)  
    - **Exemplo de URL para pesquisar o título "bom dia"**:  
      ```
      /posts/titlesearch?text=bom%20dia
      ```
  - `/posts/fullsearch` (retorna posts com os parâmetros: `text` | `dateMin` | `dateMax`)  
    - `text` será pesquisado no título ou corpo dos posts  
    - Retornará posts que estejam entre `dateMin` e `dateMax`  
    - **Exemplo de URL para pesquisa**:  
      ```
      /posts/fullsearch?text=bom%20dia&dateMin=2025-02-01
      ```  
- **POST**  
  - `/users` (cria um usuário)  
    - **Exemplo de JSON no corpo da requisição**:  
      ```json
      {
        "name": "Jose",
        "email": "jose@gmail.com"
      }
      ```  
- **PUT**  
  - `/users/{id}` (atualiza um usuário por ID)  
    - **Exemplo de JSON no corpo da requisição**:  
      ```json
      {
        "name": "Jose Augusto",
        "email": "joseaugusto@gmail.com"
      }
      ```  
- **DELETE**  
  - `/users/{id}` (deleta um usuário por ID)  

## Spring MongoDB query methods (requisições)  
Abaixo podemos ver dois métodos que retornarão a mesma consulta. Um está especificado com a annotation `@Query`, e o outro segue o padrão de query method do Spring.  
```java
".../PostRepository.java"
@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
List<Post> searchTitle(String text);

List<Post> findByTitleContainingIgnoreCase(String text);
```  

## Banco de dados relacional x não relacional  
Um aprendizado nesse projeto foi ver na prática como o paradigma de orientação a objetos é traduzido para outros paradigmas, dependendo do banco de dados utilizado.  
Veja abaixo uma tabela que representaria uma entidade da minha aplicação traduzida para o paradigma relacional.  

![image](https://github.com/user-attachments/assets/ac041919-ce42-447c-8fb1-c30a1022be97)  

Agora temos uma representação em JSON, mostrando como seria traduzido para o paradigma orientado a documentos.  

![image](https://github.com/user-attachments/assets/36a29bac-d380-411d-9de7-14b8504fa396)  

Observa-se que o usuário possui referências a posts, porém isso poderia ser definido de diferentes formas, dependendo da estratégia de agregação do negócio.  
Por exemplo, poderíamos definir que as entidades sejam agregadas umas às outras. Sendo assim:  
- O usuário será uma coleção com referência a posts.  
- Os posts serão agregados a `author` e a comentários, que também serão agregados a `author`.  

![image](https://github.com/user-attachments/assets/af4e1460-9a19-4279-b1f9-636dfc302b77)  

Nos **DTOs (Data Transfer Objects)** da aplicação, foi criado um `AuthorDTO`, que é diferente do `UserDTO`, justamente para ser aplicado dessa forma.  

---  

**Direitos de imagens e créditos reservados a** [DevSuperior](https://devsuperior.com.br/) **- Prof. Dr. Nelio Alves - Curso Udemy**
