package com.rest.service.cachingservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *  this is class used to store log to h2 data base ONLY
 */

@Repository
public interface LogRepository extends CrudRepository<LogEntiry,Integer> {
}
