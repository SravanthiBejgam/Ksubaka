package com.cinema.sravs;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SravsApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Before
	public void setUp(){
		webTestClient = webTestClient
				.mutate()
				.responseTimeout(Duration.ofMillis(3600))
				.build();
	}

	@Test
	public void test2GetOMDBMovieInfo() {
		webTestClient.get().uri("/movies/omdbInfo?name={name}", "indiana jones")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		        .expectBody().json("[{\"Title\":\"The Adventures of Young Indiana Jones: Attack of the Hawkmen\",\"Year\":\"1995\",\"Type\":\"movie\",\"imdbID\":\"tt0154004\",\"Director\":\"Ben Burtt\"},{\"Title\":\"Indiana Jones and the Last Crusade\",\"Year\":\"1989\",\"Type\":\"movie\",\"imdbID\":\"tt0097576\",\"Director\":\"Steven Spielberg\"},{\"Title\":\"Indiana Jones and the Temple of Doom\",\"Year\":\"1984\",\"Type\":\"movie\",\"imdbID\":\"tt0087469\",\"Director\":\"Steven Spielberg\"},{\"Title\":\"Indiana Jones and the Kingdom of the Crystal Skull\",\"Year\":\"2008\",\"Type\":\"movie\",\"imdbID\":\"tt0367882\",\"Director\":\"Steven Spielberg\"},{\"Title\":\"Indiana Jones and the Temple of the Forbidden Eye Ride\",\"Year\":\"1995\",\"Type\":\"movie\",\"imdbID\":\"tt0764648\",\"Director\":\"Gregory Marquette\"},{\"Title\":\"The Adventures of Young Indiana Jones: Treasure of the Peacock's Eye\",\"Year\":\"1995\",\"Type\":\"movie\",\"imdbID\":\"tt0115031\",\"Director\":\"Carl Schultz\"},{\"Title\":\"The Adventures of Young Indiana Jones: Travels with Father\",\"Year\":\"1996\",\"Type\":\"movie\",\"imdbID\":\"tt0154003\",\"Director\":\"Deepa Mehta, Michael Schultz\"},{\"Title\":\"The Adventures of Young Indiana Jones: Daredevils of the Desert\",\"Year\":\"1999\",\"Type\":\"movie\",\"imdbID\":\"tt0275186\",\"Director\":\"Simon Wincer\"},{\"Title\":\"The Adventures of Young Indiana Jones: Adventures in the Secret Service\",\"Year\":\"1999\",\"Type\":\"movie\",\"imdbID\":\"tt0250196\",\"Director\":\"Vic Armstrong, Simon Wincer\"},{\"Title\":\"The Adventures of Young Indiana Jones: Trenches of Hell\",\"Year\":\"1999\",\"Type\":\"movie\",\"imdbID\":\"tt0250199\",\"Director\":\"Simon Wincer\"}]\n");
	}

    @Test
    public void test2GetOMDBMovieInfoBadRequest() {
        webTestClient.get().uri("/movies/omdbInfo?name1={name}", "indiana jones")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void test2GetOMDBMovieInfoPreConditionFailed() {
        webTestClient.get().uri("/movies/omdbInfo?name={name}", "")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().is4xxClientError();
    }


	@Test
	public void test2GetTMDBMovieInfo() {
		webTestClient.get().uri("/movies/tmdbInfo?name={name}", "indiana jones")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBody().json("[{\"id\":\"89\",\"title\":\"Indiana Jones and the Last Crusade\",\"release_date\":\"1989-05-24\"},{\"id\":\"87\",\"title\":\"Indiana Jones and the Temple of Doom\",\"release_date\":\"1984-05-23\"},{\"id\":\"217\",\"title\":\"Indiana Jones and the Kingdom of the Crystal Skull\",\"release_date\":\"2008-05-21\"},{\"id\":\"335977\",\"title\":\"Indiana Jones 5\",\"release_date\":\"\"},{\"id\":\"319600\",\"title\":\"Indiana Jones: Making the Trilogy\",\"release_date\":\"2003-10-21\"},{\"id\":\"222039\",\"title\":\"The Adventures of Young Indiana Jones: Passion for Life\",\"release_date\":\"2000-09-18\"},{\"id\":\"590514\",\"title\":\"The Stunts of 'Indiana Jones'\",\"release_date\":\"2003-01-01\"},{\"id\":\"222040\",\"title\":\"The Adventures of Young Indiana Jones: The Perils of Cupid\",\"release_date\":\"2000-09-19\"},{\"id\":\"222050\",\"title\":\"The Adventures of Young Indiana Jones: Trenches of Hell\",\"release_date\":\"1999-10-26\"},{\"id\":\"222065\",\"title\":\"The Adventures of Young Indiana Jones: Scandal of 1920\",\"release_date\":\"2008-04-29\"},{\"id\":\"184086\",\"title\":\"Indiana Jones and the Ultimate Quest\",\"release_date\":\"2008-05-18\"},{\"id\":\"222037\",\"title\":\"The Adventures of Young Indiana Jones: My First Adventure\",\"release_date\":\"2000-07-10\"},{\"id\":\"222051\",\"title\":\"The Adventures of Young Indiana Jones: Demons of Deception\",\"release_date\":\"1999-10-31\"},{\"id\":\"590516\",\"title\":\"The Music of 'Indiana Jones'\",\"release_date\":\"2003-01-01\"},{\"id\":\"222042\",\"title\":\"The Adventures of Young Indiana Jones: Journey of Radiance\",\"release_date\":\"2007-10-23\"},{\"id\":\"222046\",\"title\":\"The Adventures of Young Indiana Jones: Spring Break Adventure\",\"release_date\":\"1999-10-17\"},{\"id\":\"222053\",\"title\":\"The Adventures of Young Indiana Jones: Oganga, the Giver and Taker of Life\",\"release_date\":\"1999-11-14\"},{\"id\":\"222058\",\"title\":\"The Adventures of Young Indiana Jones: Daredevils of the Desert\",\"release_date\":\"1999-11-21\"},{\"id\":\"222066\",\"title\":\"The Adventures of Young Indiana Jones: Hollywood Follies\",\"release_date\":\"1994-10-15\"},{\"id\":\"590510\",\"title\":\"The Sound of 'Indiana Jones'\",\"release_date\":\"2003-01-01\"}]");
	}

	@Test
	public void test2GetTMDBMovieInfoBadRequest() {
		webTestClient.get().uri("/movies/tmdbInfo?name1={name}", "indiana jones")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	public void test2GetTMDBMovieInfoPreConditionFailed() {
		webTestClient.get().uri("/movies/tmdbInfo?name={name}", "")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.expectStatus().is4xxClientError();
	}

}
