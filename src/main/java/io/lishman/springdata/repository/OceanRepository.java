package io.lishman.springdata.repository;

import io.lishman.springdata.domain.Ocean;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface OceanRepository extends MongoRepository<Ocean, BigInteger> {
 
}
