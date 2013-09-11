package com.lishman.springdata;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.lishman.springdata.repository.CityRepositoryTest;
import com.lishman.springdata.repository.CountryRepositoryTest;
import com.lishman.springdata.repository.MedalsRepositoryTest;
import com.lishman.springdata.repository.StateRepositoryTest;


@RunWith(Suite.class)
@Suite.SuiteClasses( {
    
    CityRepositoryTest.class,
    CountryRepositoryTest.class,
    MedalsRepositoryTest.class,
    StateRepositoryTest.class,

})

public class TestSuite {}