package com.bribri.kitchen.dao;

import com.bribri.kitchen.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository extends JpaRepository<Step, Integer> {
}
