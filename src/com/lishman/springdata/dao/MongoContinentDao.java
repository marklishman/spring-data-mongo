package com.lishman.springdata.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.lishman.springdata.domain.Continent;

@Repository
public class MongoContinentDao implements ContinentDao {
    
    @Autowired private MongoOperations operations;

    @Override
    public List<Continent> findAll() {
        return operations.findAll(Continent.class);
    }

    @Override
    public Continent findOne(Integer id) {
        return operations.findById(BigInteger.valueOf(id), Continent.class);
    }

    @Override
    public Continent findByName(String name) {
        return operations.findOne(query(where("name").is(name)), Continent.class);
    }

    @Override
    public Continent save(Continent continent) {
        operations.save(continent);
        return continent;
    }

    @Override
    public void delete(Continent continent) {
        operations.remove(continent);
    }

}