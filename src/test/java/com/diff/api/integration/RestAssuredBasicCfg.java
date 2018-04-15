package com.diff.api.integration;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class RestAssuredBasicCfg {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.basePath = "/rest/api/v1/diff/";
        RestAssured.baseURI = "http://localhost";
    }
}
