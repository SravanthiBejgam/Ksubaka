
# Project Title

Ksubaka : Movie Information

## Project Description
This application uses WebClient(Reactive Programming) to fetch data from 2 different public websites.
1) Get Movie db ids
2) Getting director using movie ids.

### Prerequisites
Java 8
Maven

### Build and Run

mvn clean
mvn install
mvn spring-boot:run

## Running the tests
mvn test

## Tests with movie name

http://localhost:8080/movies/omdbInfo?name=indiana jones
http://localhost:8080/movies/tmdbInfo?name=indiana jones

curl -X GET --header 'Accept: application/json' 'http://localhost:8080/movies/tmdbInfo?name=indiana jones'
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/movies/omdbInfo?name=indiana jones'

## Built With

* [Spring Initializer](https://start.spring.io/)
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

0.0.1-SNAPSHOT

##Git Url

https://github.com/SravanthiBejgam/Ksubaka

## Authors

* **Sravanthi Bejgam**


## License

NA

## References

http://omdbapi.com
https://api.themoviedb.org/3