package com.lishman.springdata.domain;



public class AbstractDocument {

    // TODO doesn't work with @Id or BigDecimal
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
