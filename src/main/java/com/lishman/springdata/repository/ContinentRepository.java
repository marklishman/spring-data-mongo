package com.lishman.springdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lishman.springdata.domain.Continent;

public interface ContinentRepository extends CrudRepository<Continent, Long> {
    
    // TODO repository
    /* Repository abstraction
     * Implements DAO
     *  - CRUD
     *  - Queries
     *  
     *      Repository
     *          |
     *      CrudRepository
     *          |
     *      PagingAndSortingRepository
     *      
     *  @Repository
     *  @NoRepositoryBean (doesn't create a bean instance)
     *  
     *  
     *  
     */
    
    public Continent findByName(String name);

    public List<Continent> findByNameStartingWithIgnoreCase(String start);
    
}