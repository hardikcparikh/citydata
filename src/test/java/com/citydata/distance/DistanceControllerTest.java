package com.citydata.distance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DistanceControllerTest {


    @Autowired
    private DistanceController distanceController;

    @Test
    public void isConnected() throws Exception {

        assertThat(distanceController.connected("Boston","New York").toString()).contains("Yes");
        assertThat(distanceController.connected("Philadelphia","Newark").toString()).contains("Yes");
        assertThat(distanceController.connected("Newark","Boston").toString()).contains("Yes");
        assertThat(distanceController.connected("Trenton","Albany").toString()).contains("Yes");

        assertThat(distanceController.connected("New York","Boston").toString()).contains("Yes");
        assertThat(distanceController.connected("Newark","Philadelphia").toString()).contains("Yes");
        assertThat(distanceController.connected("Boston","Newark").toString()).contains("Yes");
        assertThat(distanceController.connected("Albany","Trenton").toString()).contains("Yes");
    }

    @Test
    public void isNotConnected() throws Exception {

        assertThat(distanceController.connected("Boston","Philadelphia").toString()).contains("No");
        assertThat(distanceController.connected("Philadelphia","Albany").toString()).contains("No");
        assertThat(distanceController.connected("Newark","New York").toString()).contains("No");
        assertThat(distanceController.connected("Trenton","Boston").toString()).contains("No");
    }

}