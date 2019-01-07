package com.sm.notes.test;

import com.sm.notes.model.Note;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotesIntegrationTest {
    String notesURL = System.getProperty("notesURL", "http://localhost:8080/api/notes");

    @Test
    public void testCreateNote2() throws Exception {
        Note note = new Note();
        note.setTitle("note2");
        note.setContent("content2");

        given().contentType("application/json").body(note).post(notesURL).then().statusCode(200);
    }

    @Test
    public void testGetNote1() throws Exception {
        ValidatableResponse body = given().get(notesURL).then().statusCode(200);

        Response response = body.contentType(ContentType.JSON).extract().response();

        Assert.assertTrue(response.jsonPath().getString("content").contains("content1"));
        Assert.assertTrue(response.jsonPath().getString("title").contains("note1"));

    }

    public void testGetNote2() throws Exception {
        ValidatableResponse body = given().get(notesURL).then().statusCode(200);

        Response response = body.contentType(ContentType.JSON).extract().response();

        Assert.assertTrue(response.jsonPath().getString("content").contains("content2"));
        Assert.assertTrue(response.jsonPath().getString("title").contains("note2"));

    }

}
