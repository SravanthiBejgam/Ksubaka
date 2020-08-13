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
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
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
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
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
