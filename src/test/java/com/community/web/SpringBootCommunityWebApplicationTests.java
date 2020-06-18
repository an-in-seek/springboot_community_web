package com.community.web;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootCommunityWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootCommunityWebApplicationTests {

	@Test
	void contextLoads() {
	}

}
