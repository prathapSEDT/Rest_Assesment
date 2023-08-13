package com.testcases;

import com.constants.AppConstants;
import com.utils.GenericMethods;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class tc_02_create_user {
    @Test
    public void validateGetUsers() throws Exception {
        GenericMethods genericMethods = new GenericMethods();
        String endPoint = AppConstants.CREATE_USERS.getValue();
        String payLoad=genericMethods.readFileData("input","createuser").toJSONString();
        Response response = genericMethods.stimulatePostRequest(endPoint,payLoad);
        //validate status code
        Assert.assertEquals(response.statusCode(), 201, "Failed due to invalid response code");
        JSONObject responseObject = genericMethods.getJsonFromResponse(response.asString());
        JSONObject expectedResponseObject = genericMethods.readFileData("output","CreateUser.json");
        boolean status=genericMethods.compareJSONStructures(responseObject,expectedResponseObject);
        //compare json structure
        Assert.assertEquals(status, true, "Json response is not matching with expected Json output");
    }
}
