package com.tchepannou.pds.controller;

import com.jayway.restassured.RestAssured;
import com.tchepannou.pds.Starter;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
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
import static org.hamcrest.core.Is.is;

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

    @Test
    public void test_statusCodes () {
        // @formatter:off
        when()
            .get("/api/party-roles/status-codes")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log()
                .all()
            .body("statusCodes.id", hasItems(1, 2, 3))
            .body("statusCodes.name", hasItems("new", "active", "suspended"))
            .body("statusCodes.active", hasItems(false, true, false))
        ;
        // @formatter:on
    }

    @Test
    public void test_findById () {
        // @formatter:off
        when()
            .get("/api/party-roles/100")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log()
                .all()
            .body("id", is(100))
            .body("fromDate", is("1973-12-27 10:30:45 -0500"))

            .body("type.id", is(200))
            .body("type.name", is("member"))

            .body("status.id", is(100))
            .body("status.statusCode", is(2))
            .body("status.statusText", is("active"))
            .body("status.comment", is("Initial"))
            .body("status.date", is("1973-12-27 10:30:45 -0500"))

            .body("id", is(100))
            .body("party.name", CoreMatchers.is("Ray Sponsible"))
            .body("party.firstName", CoreMatchers.is("Ray"))
            .body("party.lastName", CoreMatchers.is("Sponsible"))
            .body("party.prefix", CoreMatchers.is("Mr"))
            .body("party.suffix", CoreMatchers.is("PHD"))
            .body("party.birthDate", CoreMatchers.is("1973-12-27 00:00:00"))
            .body("party.gender", CoreMatchers.is("MALE"))
            .body("party.kind", CoreMatchers.is("PERSON"))
        ;
        // @formatter:on
    }
}
