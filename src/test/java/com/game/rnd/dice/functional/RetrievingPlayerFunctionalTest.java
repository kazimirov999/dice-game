package com.game.rnd.dice.functional;

import com.game.rnd.dice.type.Status;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


class RetrievingPlayerFunctionalTest extends AbstractFunctionalTest {

    public static final String LOGIN_NAME = "loginName";

    @Test
    @Sql(scripts = CREATE_PLAYERS)
    void shouldReturnAllPlayers() {
        given()
                .basePath(BASE_USER_URI)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(OK.value())
                .contentType(JSON)
                .assertThat()
                .body("$", hasSize(3))
                .body("[0].loginName", notNullValue())
                .body("[0].firstName", notNullValue())
                .body("[0].lastName", notNullValue())
                .body("[0].gameHistory", empty())
                .body("[0].status", notNullValue());
    }

    @Test
    @Sql(scripts = REMOVE_ALL_PLAYERS)
    void shouldReturnEmptyContentsWhenPlayersAreAbsent() {
        given()
                .basePath(BASE_USER_URI)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(OK.value())
                .contentType(JSON)
                .assertThat()
                .body("$", hasSize(0));
    }

    @Test
    @Sql(scripts = CREATE_PLAYERS)
    void shouldReturnPlayer() {

        final String playerId = "114b75c0-2b7f-4750-b40b-1637ab09a907";

        given()
                .basePath(BASE_USER_URI)
                .pathParam("id", playerId)
                .contentType(JSON)
                .when()
                .get("/{id}")
                .then()
                .log().all()
                .statusCode(OK.value())
                .contentType(JSON)
                .assertThat()
                .body("loginName", equalTo("abevz"))
                .body("firstName", equalTo("Alex"))
                .body("lastName", equalTo("Bevz"))
                .body("gameHistory", empty())
                .body("status", equalTo(Status.ACTIVE.asString()));
    }

    @Test
    @Sql(CREATE_PLAYERS)
    void shouldReturnNotFoundWhenPlayerIsAbsent() {
        given()
                .basePath(BASE_USER_URI)
                .pathParam("id", UUID.randomUUID())
                .contentType(JSON)
                .when()
                .get("/{id}")
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value())
                .assertThat();
    }

    @Test
    @Sql(scripts = CREATE_PLAYERS)
    void shouldReturnPlayerLoginNameAlreadyExistsOkStatus() {
        given()
                .basePath(BASE_USER_URI)
                .queryParam(LOGIN_NAME, "abevz")
                .when()
                .head()
                .then()
                .log().all()
                .statusCode(OK.value());
    }

    @Test
    @Sql(scripts = CREATE_PLAYERS)
    void shouldReturnPlayerLoginNameIsUniqueNotFound() {
        given()
                .basePath(BASE_USER_URI)
                .queryParam(LOGIN_NAME, "john")
                .when()
                .head()
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value());
    }
}
