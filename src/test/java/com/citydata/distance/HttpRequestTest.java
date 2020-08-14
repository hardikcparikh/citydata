package com.citydata.distance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void routeExist() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Boston&destination=New York",
                String.class)).contains("Yes");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Philadelphia&destination=Newark",
                String.class)).contains("Yes");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Newark&destination=Boston",
                String.class)).contains("Yes");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Trenton&destination=Albany",
                String.class)).contains("Yes");

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=New York&destination=Boston",
                String.class)).contains("Yes");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Newark&destination=Philadelphia",
                String.class)).contains("Yes");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Boston&destination=Newark",
                String.class)).contains("Yes");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Albany&destination=Trenton",
                String.class)).contains("Yes");
    }
    @Test
    public void routeNotExist() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Boston&destination=Boston",
                String.class)).contains("No");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Philadelphia&destination=Albany",
                String.class)).contains("No");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Newark&destination=New York",
                String.class)).contains("No");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin=Trenton&destination=Boston",
                String.class)).contains("No");
    }
}