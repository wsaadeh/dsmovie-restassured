package com.saadeh.dsmovie.controllers;

import com.saadeh.dsmovie.tests.TokenUtil;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class MovieControllerRA {

    private String clientUsername,clientPassword, adminUsername,adminPassword;
    private String clientToken,adminToken, invalidToken;
    private Long existingMovieId,nonExistingMovieId;
    private String movieTitle;
    private Map<String, Object> postMovieInstance;

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

        movieTitle = "Alice";
        existingMovieId = 1L;
        nonExistingMovieId = 100L;

        postMovieInstance = new HashMap<>();
        //postMovieInstance.put("id",1L);
        postMovieInstance.put("title", "The Witcher");
        postMovieInstance.put("score", 4.5F);
        postMovieInstance.put("count",2);
        postMovieInstance.put("image","https://www.themoviedb.org/t/p/w533_and_h300_bestv2/jBJWaqoSCiARWtfV0GlqHrcdidd.jpg");
    }

    @Test
    public void findAllShouldReturnOKWhenMovieNoArgumentsGiven(){
        given()
                .get("/movies")
                .then()
                .statusCode(200)
                .body("content.title",hasItems("The Witcher","Matrix Resurrections"));
    }

    @Test
    public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty(){
        given()
                .get("/movies?title={title}",movieTitle)
                .then()
                .statusCode(200)
                .body("content[0].id",is(21))
                .body("content[0].title",equalTo("Alice no País das Maravilhas"))
                .body("content[0].score", is(0.0F))
                .body("content[0].image",equalTo("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/qNdlZgz9yoSJ8f0YxQWfKGPoVV.jpg"));
    }

    @Test
    public void findByIdShouldReturnMovieWhenIdExists(){
        given()
                .get("/movies/{id}",existingMovieId)
                .then()
                .statusCode(200)
                .body("id",is(existingMovieId.intValue()))
                .body("title", equalTo("The Witcher"))
                .body("score",is(4.5F))
                .body("count",is(2))
                .body("image",equalTo("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/jBJWaqoSCiARWtfV0GlqHrcdidd.jpg"));
    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExist(){
        given()
                .get("/movies/{id}",nonExistingMovieId)
                .then()
                .statusCode(404);
    }

    @Test
    public void insertShouldReturnMovieWhenDataIsValid(){
        JSONObject newMovie = new JSONObject(postMovieInstance);

        given()
                .header("Content-type","application/json")
                .header("Authorization", "Bearer " + adminToken )
                .body(newMovie)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/movies")
                .then()
                .statusCode(201);
    }

    @Test
    public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() throws Exception{

    }

    @Test
    public void insertShouldReturnForbiddenWhenClientLogged() throws Exception{

    }

    @Test
    public void insertShouldReturnUnauthorizedWhenInvalidToken() throws Exception{

    }
}
