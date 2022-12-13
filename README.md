# movie-api

Aplicación tipo REST API para consultar películas desde el API de [The Movie database](https://www.themoviedb.org/)

El presente proyecto es un ejercicio para poner en práctica algunos conceptos propios del desarrollo de aplicaciones en Java bajo la arquitectura de
microservicios utilizando las siguientes tecnologías:

- Java 17
- Spring (Spring Boot, Spring Web, Spring Data)
- [Postman](https://www.postman.com/downloads/)

También se aplican varios principios y patrones de diseño (SOLID, DRY, KISS) algunos de los cuales son facilitados por Spring, como es el caso del
principio de inversión de dependencias (mediante inyección con `@Autowired`).

## 1. Requisitos

- [Maven](https://maven.apache.org/)
- [Java JDK 17](https://openjdk.org/projects/jdk/17/)
- [Obtener un API Key](https://www.themoviedb.org/documentation/api) para el acceso al API de The Movie database.
- Editor de código java con soporte de Maven (Recomendado [IntelliJ IDEA](https://www.jetbrains.com/idea))

## 2. Generalidades

La principal funcionalidad del proyecto es exponer un API REST para presentar una lista de películas que son consideradas favoritas. Para lo cual se
cuenta con diferentes endpoints para listar, añadir o quitar una película de la lista de favoritos. Esta lista de favoritos se almacena en una base de
datos local.

También se exponen algunos métodos del API de *The Movie Database* en donde la aplicación actúa como un proxy consultando los datos del API remota y
retornándolos al cliente sin ningún tipo de tratamiento adicional.

Se añade una colección de Postman en el directorio `docs/postman` donde se encuentran las diferentes peticiones que se pueden realizar al API.

### Nota sobre la implementación

Se considera el enfoque de lista local de películas favoritas debido a que los métodos del API de [The Movie Database](https://www.themoviedb.org/)
que permiten la actualización de datos requieren crear una sesión mediante un flujo de autenticación que está por fuera del alcance del presente
proyecto. Sin embargo los métodos de consulta sí están disponibles utilizando sólo la autenticación mediante un API Key.

Otra razón es que el API the *The Movie Database* [no permite configurar la paginación](https://www.themoviedb.org/talk/5efe1585a76ac50035fdbc97), y
este es uno de los conceptos que se desea aplicar al presente proyecto.

Para el almacenamiento de las películas en la base de datos local se utiliza [H2 Database](https://h2database.com) por simplicidad, pero la
implementación bien puede ser con PostgreSQL, MySQL o cualquier base de datos relacional sin afectar el diseño de la aplicación.

## 3. Ejecución

### 3.1. Obtener un API Key

Antes de ejecutar la aplicación, es necesario obtener y configurar un API Key para poder conectarse con el API remota de *The Movie Database*. Por
favor visite [la documentación del API](https://developers.themoviedb.org/3) y siga los pasos para obtener un API Key.

> Una vez se tiene el API Key, se debe registrar como variable de entorno con el nombre `MOVIEDB_API_KEY`

### 3.2. Ejecutar la aplicación

Una vez que la aplicación se configura y se ejecuta de forma correcta, es posible visualizar el siguiente mensaje en la consola de ejecución.

```
[main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
[main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.1.5.Final
[main] org.hibernate.orm.deprecation            : HHH90000021: Encountered deprecated setting [javax.persistence.sharedCache.mode], use [jakarta.persistence.sharedCache.mode] instead
[main] SQL dialect                              : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
[main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
[main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
[main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
[main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path '/api/v1'
[main] c.talataa.movieapi.MovieApiApplication   : Started MovieApiApplication in 4.611 seconds (process running for 5.64)
```

#### Ejecución con IntelliJ IDEA

La aplicación se puede ejecutar desde el IDE de IntelliJ. Por favor abra el archivo principal de la aplicación `MovieApiApplication.java` y junto al
método main encontrará un botón ▶️ que permitirá ejecutar la aplicación.

#### Ejecución con Maven

Ejecute el siguiente comando con Maven para iniciar la aplicación desde una consola

```shell
mvn spring-boot:run 
```

#### Ejecución como un archivo jar

Para ejecutar el API como una aplicación Java, es preciso generar el archivo .jar ejecutable con el siguiente comando

```shell
mvn clean package 
```

Luego se puede ejecutar con el siguiente comando

```shell
java -jar target/movieapi-1.0.0.jar
```

## 4. Uso del API

Una vez la aplicación se encuentra en ejecución, recibirá las peticiones en el puerto `HTTP 8080`. Se recomienda utilizar la colección de Postman para
realizar las diferentes peticiones al API.

Esta es la estructura de las peticiones disponibles en el API y configuradas en la colección de Postman:

```
MovieDB API
│
├── Favorites
│   ├── Initialize favorites DEMO
│   ├── Clear favorites list
│   ├── List
│   ├── Detailed list (data from MovieDB API)
│   ├── Get favorite movie
│   ├── Get detailed favorite movie (data from MovieDB)
│   ├── Set movie as favorite
│   └── Remove movie from favorites
│
└── Data from Remote API
    ├── Movies
    │   ├── Popular
    │   ├── Top Rated
    │   └── Get by ID
    │
    ├── Genre
    │   ├── Complete Genre list
    │   ├── Movie genre list
    │   └── TV genre list
    │
    └── Persons
        ├── Popular
        └── By ID
```

### API de películas favoritas

Estas son las peticiones que se encargan de gestionar la información de películas favoritas en

| Endpoint                                        | Request                                    | Descripción                                                       |
|-------------------------------------------------|--------------------------------------------|-------------------------------------------------------------------|
| Initialize favorites DEMO                       | `POST`   `/api/v1/favorites/initialize`    | Configura una lista predefinida de películas como favoritas       |
| Clear favorites list                            | `POST`   `/api/v1/favorites/clear`         | Elimina la lista de películas marcadas como favoritas             |
| List                                            | `GET`    `/api/v1/favorites`               | Retorna una lista paginada de las películas favoritas             |
| Detailed list (data from MovieDB API)           | `GET`    `/api/v1/favorites/detailed`      | Retorna una lista paginada y detallada de las películas favoritas |
| Get favorite movie                              | `GET`    `/api/v1/favorites/{id}`          | Obtiene la información de una película favorita                   |
| Get detailed favorite movie (data from MovieDB) | `GET`    `/api/v1/favorites/{id}/detailed` | Obtiene los detalles completos de una película favorita           |
| Set movie as favorite                           | `PUT`    `/api/v1/favorites/`              | Añade una película a la lista de películas favoritas              |
| Remove movie from favorites                     | `DELETE` `/api/v1/favorites/{id}`          | Elimina una película de la lista de películas favoritas           |

### Peticiones hacia el API de Movie Database

Estas peticiones obtienen información directamente desde el API de The Movie Database sin realizar ninguna trasnformación ni procesamiento adicional
de los datos que son retornados por el API remota. Los llamados al API remota lleva consigo el parámetro del `API Key`.

| Endpoint                   | Request                          | API remota                                                                          |
|----------------------------|----------------------------------|-------------------------------------------------------------------------------------|
| Movies: Popular            | `GET` `/api/v1/movies/popular`   | [Popular movies](https://developers.themoviedb.org/3/movies/get-popular-movies)     |
| Movies: Top Rated          | `GET` `/api/v1/movies/top`       | [Top rated movies](https://developers.themoviedb.org/3/movies/get-top-rated-movies) |
| Movies: Get by ID          | `GET` `/api/v1/movies/{id}`      | [Movie details](https://developers.themoviedb.org/3/movies/get-movie-details)       |
| Genre: Complete genre list | `GET` `/api/v1/genre/list`       | [Genre list](https://developers.themoviedb.org/3/genres/get-movie-list)             |
| Genre: Movie genre list    | `GET` `/api/v1/genre/movie/list` | [Genre Movie list](https://developers.themoviedb.org/3/genres/get-movie-list)       |
| Genre: TV genre list       | `GET` `/api/v1/genre/tv/list`    | [Genre TV list](https://developers.themoviedb.org/3/genres/get-tv-list)             |   
| Persons: Popular           | `GET` `/api/v1/persons/popular`  | [Popular people](https://developers.themoviedb.org/3/people/get-popular-people)     |
| Persons: By ID             | `GET` `/api/v1/persons/{id}`     | [Person details](https://developers.themoviedb.org/3/people/get-person-details)     |

