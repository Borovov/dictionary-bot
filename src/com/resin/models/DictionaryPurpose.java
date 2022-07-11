package com.resin.models;

public enum DictionaryPurpose {

    DONOR("donor"),
    RECIPIENT("recipient");

    DictionaryPurpose(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }
}
