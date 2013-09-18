package com.lishman.springdata;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.lishman.springdata.repository.CityRepositoryTest;
import com.lishman.springdata.repository.ContinentRepositoryTest;
import com.lishman.springdata.repository.CountryRepositoryTest;
import com.lishman.springdata.repository.MedalsRepositoryTest;
import com.lishman.springdata.repository.OceanRepositoryTest;
import com.lishman.springdata.repository.StateRepositoryTest;

@RunWith(Suite.class)
@Suite.SuiteClasses( {
    
    CityRepositoryTest.class,
    ContinentRepositoryTest.class,
    CountryRepositoryTest.class,
    MedalsRepositoryTest.class,
    OceanRepositoryTest.class,
    StateRepositoryTest.class,

})

public class TestSuite {}