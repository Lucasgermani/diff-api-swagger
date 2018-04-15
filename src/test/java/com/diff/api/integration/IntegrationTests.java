package com.diff.api.integration;

import com.diff.api.Starter;
import com.diff.api.resource.Diff;
import com.diff.api.resource.DiffOutput;
import com.diff.api.resource.enums.Result;
import com.diff.api.resource.utils.CollectionUtil;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class IntegrationTests extends RestAssuredBasicCfg {

    Server server;
    private RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    /**
     * Start the service before running the tests
     * @throws Exception
     */
    @Before
    public void testSetup() throws Exception {
        server = Starter.startService();
    }

    /**
     * Shutdown the service after running the tests
     */
    @After
    public void testTeardown() throws Exception {
        server.stop();
        server.destroy();
    }

    @Test
    public void leftEndPointTest() throws IOException {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("value", "d2Flcw==");

        given()
            .contentType("application/json")
            .body(jsonAsMap)
            .when().post("/455/left")
            .then().statusCode(201);
    }

    @Test
    public void rightEndPointTest() throws IOException {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("value", "d2Flcw==");

        given().contentType("application/json")
            .body(jsonAsMap)
            .when().post("/455/right")
            .then().statusCode(201);
    }

    @Test
    public void missingIDTest() throws IOException {
        given().when().get("/123").then()
                .body("status", equalTo(404))
                .body("message",containsString("Entry not found for id"));
    }

    @Test
    public void equalValuesTest() throws IOException {
        final int ID = 777;

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("value", "d2Flcw==");

        given()
            .contentType("application/json")
            .body(jsonAsMap)
            .when().post("/"+ID+"/left")
            .then().statusCode(201);

        given()
            .contentType("application/json")
            .body(jsonAsMap)
            .when().post("/"+ID+"/right")
            .then().statusCode(201);

        given()
            .contentType("application/json").when().get("/"+ID).then()
            .statusCode(200)
            .body("id", equalTo(ID))
            .body("result", equalTo(Result.EQUAL.toString()));
    }

    @Test
    public void differentValuesTest() throws IOException {
        final int ID = 777;

        Map<String, Object> jsonAsMapLeft = new HashMap<>();
        jsonAsMapLeft.put("value", "d2VhcmV3YWVz");

        given()
                .contentType("application/json")
                .body(jsonAsMapLeft)
                .when().post("/"+ID+"/left")
                .then().statusCode(201);

        Map<String, Object> jsonAsMapRight = new HashMap<>();
        jsonAsMapRight.put("value", "dzNhcjN3YTNz");

        given()
                .contentType("application/json")
                .body(jsonAsMapRight)
                .when().post("/"+ID+"/right")
                .then().statusCode(201);

        //Diff[] listDiffExpected = {new Diff(1,2), new Diff(5,2), new Diff(9,2)};

        List<Diff> listDiffExpected = new ArrayList<Diff>();
        listDiffExpected.add(new Diff(1,2));
        listDiffExpected.add(new Diff(5,2));
        listDiffExpected.add(new Diff(9,2));

        DiffOutput diffOutput =
                given().contentType("application/json").when().get("/"+ID).as(DiffOutput.class);

        assertEquals("Diff list size", 3, diffOutput.getDiffList().size());
        assertEquals("Diff Result", Result.DIFFERENT, diffOutput.getResult());

        //comparing the lists
        boolean listsAreEqual = CollectionUtil.sameElements(listDiffExpected, diffOutput.getDiffList());
        assertTrue("diffLists are not equal", listsAreEqual);
    }
}
