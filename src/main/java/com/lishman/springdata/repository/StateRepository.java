package com.lishman.springdata.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lishman.springdata.domain.State;

public interface StateRepository extends PagingAndSortingRepository<State, BigInteger> {

    // TODO Pagination and Sorting (page 18)
    /*
     *  - Pageable
     *  - PageRequest
     *  - Page
     *  
     *  MetaData (totals page count) triggers second request
     */
    
    
    public List<State> findByCapitalSinceGreaterThan(int since, Pageable pageable);

    public List<State> findByDateOfStatehoodGreaterThan(int since, Sort sort);
    
}
