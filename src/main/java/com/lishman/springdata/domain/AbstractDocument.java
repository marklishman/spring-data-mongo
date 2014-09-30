package com.lishman.springdata.domain;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class AbstractDocument {

    // TODO ObjectID - page 83
    @Id private BigInteger id;

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

}
