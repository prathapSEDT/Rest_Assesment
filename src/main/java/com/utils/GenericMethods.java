package com.utils;

import com.constants.AppConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class GenericMethods {

    private static RequestSpecification requestSpecification;

    public void intiateRequest(){
        if(requestSpecification==null) {
            RestAssured.baseURI = AppConstants.BASE_URL.getValue();
            requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        }
    }

    public  Response stimulateGetRequest(String endpoint) {
        Response response = requestSpecification
                .when()
                .get(endpoint);
        return response;
    }

    public  Response stimulatePostRequest(String endpoint, String requestBody) {
        Response response = requestSpecification
                .body(requestBody) // Set the request body
                .when()
                .post(endpoint);
        return response;
    }

    public  JSONObject getJsonFromResponse(String response) throws Exception {
        JSONParser jsonParser=new JSONParser();
        return (JSONObject)jsonParser.parse(response.toString());
    }

    public  JSONObject readFileData(String type,String fileName) throws Exception {
        String filePath=null;
        if(fileName.contains(".json")) {
            fileName=fileName.replace(".json","");
        }
        switch (type.toLowerCase()){
            case "input":
               filePath= AppConstants.INPUT_FILE_PATH.getValue()+fileName+".json";
                break;
            case "output":
                filePath= AppConstants.EXPECTED_OUTPUT.getValue()+fileName+".json";
                break;
        }
        try(FileReader fileReader=new FileReader(filePath)){
            JSONParser jsonParser=new JSONParser();
            return (JSONObject)jsonParser.parse(fileReader);
        }
    }
    public boolean compareTwoJsonObject(JSONObject obj1,JSONObject obj2){
        return obj1.toJSONString().equals(obj2.toJSONString());
    }

    public  boolean compareJSONStructures(JSONObject obj1, JSONObject obj2) {
        // Remove data from the JSON objects
        stripData(obj1);
        stripData(obj2);

        // Compare the stripped JSON objects
        return obj1.toString().equals(obj2.toString());
    }
    public static void stripData(JSONObject jsonObject) {
        for (Object key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                stripData((JSONObject) value); // Recursive call for nested objects
            } else if (value instanceof JSONArray) {
                JSONArray array = (JSONArray) value;
                for (int i = 0; i < array.length(); i++) {
                    Object arrayItem = array.get(i);
                    if (arrayItem instanceof JSONObject) {
                        stripData((JSONObject) arrayItem); // Recursive call for nested objects in arrays
                    }
                }
            } else {
                jsonObject.put(key, null); // Replace data with null
            }
        }
    }

}
