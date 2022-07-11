package com.resin.models;

public class Dictionary {

    private String keyword;
    private String region;

    public Dictionary(String keyword, String region) {
        this.keyword = keyword;
        this.region = region;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getRegion() {
        return region;
    }

}
