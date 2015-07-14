package com.tchepannou.pds.controller;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.mapper.ObjectMapperType;
import com.tchepannou.pds.Starter;
import com.tchepannou.pds.dto.CreatePartyRoleRequest;
import com.tchepannou.pds.dto.PartyRoleStatusRequest;
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

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;
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
            .log()
                .all()
            .statusCode(HttpStatus.SC_OK)
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
            .log()
                .all()
            .statusCode(HttpStatus.SC_OK)
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

    @Test
    public void test_setStatus () {
        PartyRoleStatusRequest request = new PartyRoleStatusRequest();
        request.setStatusCode(2);
        request.setComment("Hello world");

        // @formatter:off
        given ()
                .contentType(ContentType.JSON)
                .content(request, ObjectMapperType.JACKSON_2)
        .when()
            .put("/api/party-roles/200/status")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .log()
                .all()
            .body("id", is(200))
            .body("fromDate", is("1973-12-27 10:30:45 -0500"))

            .body("type.id", is(200))
            .body("type.name", is("member"))

            .body("status.statusCode", is(2))
            .body("status.statusText", is("active"))
            .body("status.comment", is("Hello world"))
            .body("status.date", notNullValue())

            .body("id", is(200))
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

    @Test
    public void test_setStatus_badId () {
        PartyRoleStatusRequest request = new PartyRoleStatusRequest();
        request.setStatusCode(2);
        request.setComment("Hello world");

        // @formatter:off
        given ()
                .contentType(ContentType.JSON)
                .content(request, ObjectMapperType.JACKSON_2)
        .when()
            .put("/api/party-roles/99999/status")
        .then()
            .log()
                .all()
            .statusCode(HttpStatus.SC_NOT_FOUND)
        ;
        // @formatter:on
    }

    @Test
    public void test_setStatus_badCode () {
        PartyRoleStatusRequest request = new PartyRoleStatusRequest();
        request.setStatusCode(9999);
        request.setComment("Hello world");

        // @formatter:off
        given ()
                .contentType(ContentType.JSON)
                .content(request, ObjectMapperType.JACKSON_2)
        .when()
            .put("/api/party-roles/200/status")
        .then()
            .log()
                .all()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .body("message", is("statusCode"))
        ;
        // @formatter:on

    }

    @Test
    public void test_create_person (){
        CreatePartyRoleRequest request = new CreatePartyRoleRequest();
        request.setType("member");
        request.setKind("person");
        request.setFirstName("Ray");
        request.setLastName("Sponsible");

        // @formatter:off
        given ()
                .contentType(ContentType.JSON)
                .content(request, ObjectMapperType.JACKSON_2)
        .when()
            .post("/api/party-roles")
        .then()
            .log()
                .all()
            .statusCode(HttpStatus.SC_CREATED)
            .body("fromDate", notNullValue())

            .body("type.id", is(200))
            .body("type.name", is("member"))

            .body("status.statusCode", is(2))
            .body("status.statusText", is("active"))
            .body("status.comment", nullValue())
            .body("status.date", notNullValue())

            .body("party.name", CoreMatchers.is("Ray Sponsible"))
            .body("party.firstName", CoreMatchers.is("Ray"))
            .body("party.lastName", CoreMatchers.is("Sponsible"))
            .body("party.prefix", nullValue())
            .body("party.suffix", nullValue())
            .body("party.birthDate", nullValue())
            .body("party.gender", nullValue())
            .body("party.kind", CoreMatchers.is("PERSON"))
        ;
        // @formatter:on
    }

    @Test
    public void test_create_org (){
        CreatePartyRoleRequest request = new CreatePartyRoleRequest();
        request.setType("team");
        request.setKind("organization");
        request.setName("NYSC");

        // @formatter:off
        given ()
                .contentType(ContentType.JSON)
                .content(request, ObjectMapperType.JACKSON_2)
        .when()
            .post("/api/party-roles")
        .then()
            .log()
                .all()
            .statusCode(HttpStatus.SC_CREATED)
            .body("fromDate", notNullValue())

            .body("type.id", is(100))
            .body("type.name", is("team"))

            .body("status.statusCode", is(1))
            .body("status.statusText", is("new"))
            .body("status.comment", nullValue())
            .body("status.date", notNullValue())

            .body("party.name", is("NYSC"))
            .body("party.firstName", nullValue())
            .body("party.lastName", nullValue())
            .body("party.prefix", nullValue())
            .body("party.suffix", nullValue())
            .body("party.birthDate", nullValue())
            .body("party.gender", nullValue())
            .body("party.kind", CoreMatchers.is("ORGANIZATION"))
        ;
        // @formatter:on
    }

    @Test
    public void test_create_badType (){
        CreatePartyRoleRequest request = new CreatePartyRoleRequest();
        request.setType("????");
        request.setKind("organization");
        request.setName("NYSC");

        // @formatter:off
        given ()
                .contentType(ContentType.JSON)
                .content(request, ObjectMapperType.JACKSON_2)
        .when()
            .post("/api/party-roles")
        .then()
            .log()
                .all()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .body("message", is("type"))
        ;
        // @formatter:on
    }
}
