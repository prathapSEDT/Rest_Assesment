package com.testcases;

import com.constants.AppConstants;
import com.utils.GenericMethods;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class tc_01_get_users {
    @Test
    public void validateGetUsers() throws Exception {
        GenericMethods genericMethods = new GenericMethods();
        String endPoint = AppConstants.GET_USERS_BY_ID.getValue();
        Response response = genericMethods.stimulateGetRequest(endPoint);
        JSONObject responseObject = genericMethods.getJsonFromResponse(response.asString());

        JSONObject expectedReponseObject = genericMethods.readFileData("output","GetUserByID.json");
        //validate status code
        Assert.assertEquals(response.statusCode(), 200, "Failed due to invalid response code");
        //validate json response with expected json output
//        boolean status = genericMethods.compareTwoJsonObject(responseObject, expectedReponseObject);
//        Assert.assertEquals(status, true, "Json response is not matching with expected Json output");
        boolean status=genericMethods.compareJSONStructures(responseObject,expectedReponseObject);
        Assert.assertEquals(status, true, "Json response is not matching with expected Json output");
    }
}
