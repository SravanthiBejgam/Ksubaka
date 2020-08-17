
# Project Title

Ksubaka : Movie Information

## Project Description
This application uses Spring Boot webflux to retrieve movie information from two different movie db api's (themoviedb and ombd).

### Prerequisites
Java 8
Maven

### Build and Run

* mvn clean
* mvn install
* mvn spring-boot:run

## Running the tests
mvn test

## Tests with movie name

* **Request
* http://localhost:8080/movies/omdbInfo?name=indiana jones
* http://localhost:8080/movies/tmdbInfo?name=indiana jones

* **Response
* {"Title":"Indiana Jones and the Kingdom of the Crystal Skull","Year": "2008","ImdbID": "tt0367882", "Director":"Steven Spielberg"}
* {"id": "89","title": "Indiana Jones and the Kingdom of the Crystal Skull","release_date": "2008-05-24", "Director" : "Steven Spielberg"}

* curl -X GET --header 'Accept: application/json' 'http://localhost:8080/movies/tmdbInfo?name=indiana jones'
* curl -X GET --header 'Accept: application/json' 'http://localhost:8080/movies/omdbInfo?name=indiana jones'

## Built With

* [Spring Initializer](https://start.spring.io/)
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

0.0.1-SNAPSHOT

## Git Url

https://github.com/SravanthiBejgam/Ksubaka

## Authors

* **Sravanthi Bejgam**


## License

NA

## References

* http://omdbapi.com
* https://api.themoviedb.org/3