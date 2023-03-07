package com.example.tobyspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

class TobySpringApplicationTest {


    /**
     * 단점 RestTemplate 테스트 코드는 항상 서버가 켜져있어야 한다.
     * 자동화 테스트를 진행하기 어렵다.
     *
     * 항상 Response 값은 상태코드, content-type, Body 의식하기
     * 요청은 Request, Header, Method 생각하기
     */
    @Test
    public void restTemplate() {
        //given
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> result = testRestTemplate.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

        //when
            Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
            Assertions.assertThat(result.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
            Assertions.assertThat(result.getBody()).isEqualTo("Hello Spring");
        //then
    }
}
