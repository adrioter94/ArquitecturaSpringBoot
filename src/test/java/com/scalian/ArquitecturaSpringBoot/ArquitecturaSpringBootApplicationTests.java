package com.scalian.ArquitecturaSpringBoot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ArquitecturaSpringBootApplicationTests {

	@Test
	void contextLoads() {
	}

}
