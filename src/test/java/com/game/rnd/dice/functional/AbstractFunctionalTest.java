package com.game.rnd.dice.functional;

import io.restassured.RestAssured;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Collection;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract class AbstractFunctionalTest {

    public static final String BASE_USER_URI = "/v1/players";
    static final String CREATE_PLAYERS = "classpath:/players.sql";
    static final String REMOVE_ALL_PLAYERS = "classpath:/clean-players.sql";

    @LocalServerPort
    protected int port;


    static String modifyJson(String json, String field, Object value) throws JSONException {
        Object fieldValue = value instanceof Collection ? new JSONArray(((Collection) value).toArray()) :
                value != null && value.getClass().isArray() ? new JSONArray(value) : value;
        return new JSONObject(json).put(field, fieldValue).toString();
    }

    @BeforeAll
    void setupBeforeAll() {
        RestAssured.port = port;
    }


}
