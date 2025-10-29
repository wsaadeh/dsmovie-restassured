package com.saadeh.dsmovie.controllers;

import com.saadeh.dsmovie.tests.TokenUtil;
import io.restassured.http.ContentType;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ScoreControllerRA {

    private String clientUsername,clientPassword, adminUsername,adminPassword;
    private String clientToken,adminToken, invalidToken;
    private Long existingMovieId,nonExistingMovieId;
    private Map<String, Object> putScoreInstance;

    @BeforeEach
    void setUp(){
        baseURI = "http://localhost:8080";

        clientUsername = "alex@gmail.com";
        clientPassword = "123456";
        adminUsername = "maria@gmail.com";
        adminPassword = "123456";

        clientToken = TokenUtil.obtainAccessToken(clientUsername,clientPassword);
        adminToken = TokenUtil.obtainAccessToken(adminUsername,adminPassword);
        invalidToken = adminToken + "xpto";

        existingMovieId = 1L;
        nonExistingMovieId = 100L;

        putScoreInstance = new HashMap<>();
        putScoreInstance.put("movieId",1);
        putScoreInstance.put("score",4);


    }

    @Test
    public void saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist(){
        putScoreInstance.put("movieId", nonExistingMovieId.intValue());
        JSONObject score = new JSONObject(putScoreInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(score)
                .when()
                .put("/scores")
                .then()
                .statusCode(404);
    }

    @Test
    public void saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId(){
        putScoreInstance.put("movieId", "  ");
        JSONObject score = new JSONObject(putScoreInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(score)
                .when()
                .put("/scores")
                .then()
                .statusCode(422);
    }

    @Test
    public void saveScoreReturnUnprocessableEntityWhenScoreIsLessThanZero(){
        putScoreInstance.put("score", -1.0F);
        JSONObject score = new JSONObject(putScoreInstance);

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(score)
                .when()
                .put("/scores")
                .then()
                .statusCode(422);

    }


}
