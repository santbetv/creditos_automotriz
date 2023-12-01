package com.pichincha.testproject.repository.impl;

import com.pichincha.testproject.domain.model.ClienteEntity;
import com.pichincha.testproject.domain.model.PatioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatioRepository extends JpaRepository<PatioEntity, Long> {

}
