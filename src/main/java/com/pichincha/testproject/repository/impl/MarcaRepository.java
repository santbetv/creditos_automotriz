package com.pichincha.testproject.repository.impl;

import com.pichincha.testproject.domain.model.MarcaEntity;
import com.pichincha.testproject.domain.model.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {

}
