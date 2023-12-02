package com.pichincha.testproject.repository.impl;

import com.pichincha.testproject.domain.model.EjecutivoEntity;
import com.pichincha.testproject.domain.model.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjecutivoRepository extends JpaRepository<EjecutivoEntity, Long> {

}
