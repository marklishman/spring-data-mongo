package com.lishman.springdata.domain;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class AbstractDocument {

    // TODO ObjectID - page 83
    @Id private BigInteger documentId;

    public void setId(BigInteger id) {
        this.documentId = id;
    }

    public BigInteger getId() {
        return documentId;
    }

}
