package com.constants;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;

public enum AppConstants {
    BASE_URL("https://reqres.in/"),
    GET_USERS_BY_ID("/api/users/2"),
    CREATE_USERS("/api/users"),
    EXPECTED_OUTPUT("src/main/resources/expectedoutput/"),
    INPUT_FILE_PATH("src/main/resources/inputpayloads/");
    private String value;
    public String getValue() {
        return value;
    }
    AppConstants(String value){
        this.value = value;
    }
}
