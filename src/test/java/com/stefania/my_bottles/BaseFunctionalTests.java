package com.stefania.my_bottles;


import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyBottlesApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseFunctionalTests {

    @LocalServerPort
    private int serverPort;

    @Before
    public void setup() {
        RestAssured.port = serverPort;
    }
}
