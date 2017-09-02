package io.lishman.springdata.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface SingleItemOnlyRepository<T, ID extends Serializable> 
            extends Repository<T, ID>{
    
    void delete(ID id);
    
    void delete(T entity);
    
    boolean exists(ID id);
    
    T findOne(ID id);

    <S extends T> S save(S entity);

}
