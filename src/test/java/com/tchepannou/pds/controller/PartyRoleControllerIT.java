package com.tchepannou.pds.controller;

import com.jayway.restassured.RestAssured;
import com.tchepannou.pds.Starter;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.hasItems;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Starter.class)
@WebIntegrationTest
@Sql({
        "/db/data/clean.sql",
        "/db/data/party_role.sql"
})
public class PartyRoleControllerIT {
    @Value("${server.port}")
    int port;

    @Before
    public void setUp (){
        RestAssured.port = port;
    }


    @Test
    public void test_types () {
        // @formatter:off
        when()
            .get("/api/party-roles/types")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log()
                .all()
            .body("types.id", hasItems(100, 110, 200))
            .body("types.name", hasItems("team", "club", "member"))
        ;
        // @formatter:on
    }
}