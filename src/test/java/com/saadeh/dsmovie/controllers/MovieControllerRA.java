package com.saadeh.dsmovie.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class MovieControllerRA {

    @BeforeEach
    void setUp(){

    }

    @Test
    public void findAllShouldReturnOKWhenMovieNoArgumentsGiven(){

    }

    @Test
    public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty(){

    }

    @Test
    public void findByIdShouldReturnMovieWhenIdExists(){

    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExist(){

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
