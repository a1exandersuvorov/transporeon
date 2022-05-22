package com.github.a1exandersuvorov.transporeon;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.a1exandersuvorov.transporeon.service.JobExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransporeonApplicationTests {

	@Autowired
	private JobExecutor executor;

	@Test
	void contextLoads() {
		assertNotNull(executor);
	}
}
