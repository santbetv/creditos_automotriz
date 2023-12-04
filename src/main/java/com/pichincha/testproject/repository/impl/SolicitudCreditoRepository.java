package com.pichincha.testproject.repository.impl;

import com.pichincha.testproject.domain.model.SolicitudCreditoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SolicitudCreditoRepository extends JpaRepository<SolicitudCreditoEntity, Long> {

    @Query("SELECT COUNT(c) > 0 FROM SolicitudCreditoEntity c WHERE c.idCliente = :idCliente AND c.fechaElaboracion = :fechaElaboracion")
    boolean existsByIdClienteAndFechaElaboracion(@Param("idCliente") Long idCliente,@Param("fechaElaboracion") LocalDate fechaElaboracion);

    @Query("SELECT COUNT(c) > 0 FROM SolicitudCreditoEntity c WHERE c.idVehiculo = :idVehiculo AND c.estadoSolicitudCredito = 'R'")
    boolean existsByIdVehiculoAndEstadoSolicitud(@Param("idVehiculo") Long idVehiculo);
}
