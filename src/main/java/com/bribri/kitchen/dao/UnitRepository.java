package com.bribri.kitchen.dao;

import com.bribri.kitchen.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface UnitRepository extends JpaRepository<Unit, Integer> {

}
