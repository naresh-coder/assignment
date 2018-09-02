package com.rest.service.cachingservice;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CachingServiceApplicationTests {

	@LocalServerPort
	int port;

	@Before
	public void setUp() {
		RestAssured.port = port;

	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSave() {
		RequestSpecification spec = new RequestSpecBuilder().setAccept("application/json").build();


		RestAssured.given().
				spec(spec).
				body("{  \"key\":\"3\"," +"  \"value\":\"Three" +
						"}").
				when().
				post("/jsonBodyAcceptHeader").
				then().toString().contains("success");

	}

	@Test
	public void testGet() {
		RequestSpecification spec = new RequestSpecBuilder().setAccept("application/json").build();

		RestAssured.given().
				spec(spec).
				param("key",3).get().then().toString().contains("Three");


	}


	@Test
	public void testUpdate() {
		RequestSpecification spec = new RequestSpecBuilder().setAccept("application/json").build();


		RestAssured.given().
				spec(spec).
				body("{  \"key\":\"3\"," +"  \"value\":\" 3" +
						"}").
				when().
				put("/jsonBodyAcceptHeader").
				then().toString().contains("success");

	}

	@Test
	public void testRemove() {
		RequestSpecification spec = new RequestSpecBuilder().setAccept("application/json").build();

		RestAssured.given().
				spec(spec).
				param("key",3).delete().then().toString().contains("");


	}


}
