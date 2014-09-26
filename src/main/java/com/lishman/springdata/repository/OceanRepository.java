package com.lishman.springdata.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lishman.springdata.domain.Ocean;

public interface OceanRepository extends MongoRepository<Ocean, BigInteger> {
 
}
