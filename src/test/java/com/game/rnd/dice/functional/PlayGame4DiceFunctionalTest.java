package com.game.rnd.dice.functional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static com.game.rnd.dice.type.Result.WIN;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.OK;

public class PlayGame4DiceFunctionalTest extends AbstractFunctionalTest {

    @MockBean
    private Random random;

    @Test
    @Sql(CREATE_PLAYERS)
    void shouldWinPlayGame4Dice() throws Exception {
        int[] gameResult = {3, 1, 4, 6};
        Mockito.when(random.ints(4, 1, 7)).thenReturn(IntStream.of(gameResult));

        given()
                .basePath("v1/game/game4dice")
                .pathParam("id", "114b75c0-2b7f-4750-b40b-1637ab09a907")
                .when()
                .get("/{id}")
                .then()
                .statusCode(OK.value())
                .assertThat()
                .body("result", equalTo(WIN.asString().toLowerCase()))
                .body("payload", equalTo(Arrays.toString(gameResult)));
    }

    @ParameterizedTest
    @CsvSource(value = {"6:win", "4:lose"}, delimiter = ':')
    @Sql(CREATE_PLAYERS)
    void shouldLosePlayGame4Dice(int diceValue, String expectedResult) throws Exception {
        int[] gameResult = {3, 4, 4, diceValue};
        Mockito.when(random.ints(4, 1, 7)).thenReturn(IntStream.of(gameResult));

        given()
                .basePath("v1/game/game4dice")
                .pathParam("id", "114b75c0-2b7f-4750-b40b-1637ab09a907")
                .when()
                .get("/{id}")
                .then()
                .statusCode(OK.value())
                .assertThat()
                .body("result", equalTo(expectedResult))
                .body("payload", equalTo(Arrays.toString(gameResult)));
    }
}
