package com.sm.notes.test;

import com.sm.notes.model.Note;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotesFunctionalTest {

    String notesURL = System.getProperty("notesURL", "http://localhost:8080/api/notes");

    @Test
    public void testCreateNote() throws Exception {
        Note note = new Note();
        note.setTitle("note1");
        note.setContent("content1");

        given().contentType("application/json").body(note).post(notesURL).then().statusCode(200);
    }

    @Test
    public void testGetNote() throws Exception {
        ValidatableResponse body = given().get(notesURL).then().statusCode(200);

        Response response = body.contentType(ContentType.JSON).extract().response();

        Assert.assertTrue(response.jsonPath().getString("content").contains("content1"));
        Assert.assertTrue(response.jsonPath().getString("title").contains("note1"));

    }
}
