package com.saadeh.dsmovie.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ScoreControllerRA {

    @BeforeEach
    void setUp(){

    }

    @Test
    public void saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist(){

    }

    @Test
    public void saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId(){

    }

    @Test
    public void saveScoreReturnUnprocessableEntityWhenScoreIsLessThanZero(){

    }


}
