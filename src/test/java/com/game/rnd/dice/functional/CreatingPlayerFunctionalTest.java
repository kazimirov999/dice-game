package com.game.rnd.dice.functional;

import com.game.rnd.dice.ResourceUtil;
import com.game.rnd.dice.type.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.*;


class CreatingPlayerFunctionalTest extends AbstractFunctionalTest {

    private static final String NEW_PLAYER = ResourceUtil.getContentAsString("new-player.json");


    @Test
    @Sql(REMOVE_ALL_PLAYERS)
    void shouldCreateNewPlayer() throws Exception {
        given()
                .basePath(BASE_USER_URI)
                .contentType(JSON)
                .body(NEW_PLAYER)
                .when()
                .post()
                .then()
                .statusCode(CREATED.value())
                .assertThat()
                .body("id", notNullValue())
                .body("loginName", equalTo("obar"))
                .body("firstName", equalTo("Ofer"))
                .body("lastName", equalTo("Bar"))
                .body("gameHistory", empty())
                .body("status", equalTo(Status.ACTIVE.asString()));

    }

    @ParameterizedTest
    @ValueSource(strings = {"loginName", "firstName", "lastName"})
    @Sql(CREATE_PLAYERS)
    void shouldNotCreateNewPlayerWhenInvalidRequest(String fieldName) throws Exception {
        final String newPlayerWithInvalidParam = modifyJson(NEW_PLAYER, fieldName, "");

        given()
                .basePath(BASE_USER_URI)
                .contentType(JSON)
                .body(newPlayerWithInvalidParam)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(BAD_REQUEST.value());
    }

    @Test
    @Sql(CREATE_PLAYERS)
    void shouldNotCreateNewPlayerWhenLoginNameAlreadyExists() throws Exception {
        final String newPlayer = modifyJson(NEW_PLAYER, "loginName", "abevz");

        given()
                .basePath(BASE_USER_URI)
                .contentType(JSON)
                .body(newPlayer)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(CONFLICT.value());
    }

}
