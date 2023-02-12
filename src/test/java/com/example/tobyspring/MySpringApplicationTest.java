package com.example.tobyspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class MySpringApplicationTest {

    @Test
    public void restTest() {
        //given
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        //when
        ResponseEntity<String> result = testRestTemplate.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");
        //then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(result.getBody()).isEqualTo("*Hello Spring*");
    }

    @Test
    public void failedRestTest() {
        //given
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        //when
        ResponseEntity<String> result = testRestTemplate.getForEntity("http://localhost:8080/hello?name=", String.class);
        //then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
