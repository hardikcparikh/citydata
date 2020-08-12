package com.citydata.distance;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DistanceApplicationTests {

	@Autowired
	DistanceController distanceController;

	@Test
	void contextLoads() {
		Assertions.assertThat(distanceController).isNotNull();
	}

}
