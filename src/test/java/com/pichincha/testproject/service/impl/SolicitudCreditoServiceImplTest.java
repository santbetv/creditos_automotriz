package com.pichincha.testproject.service.impl;

import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.services.server.models.PatiosDto;
import com.pichincha.services.server.models.VehiculoDto;
import com.pichincha.testproject.domain.model.SolicitudCreditoEntity;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.repository.impl.SolicitudCreditoRepository;
import com.pichincha.testproject.service.dto.EjecutivoDto;
import com.pichincha.testproject.service.dto.SolicitudCreditoDto;
import com.pichincha.testproject.service.mapper.SolicitudCreditoResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SolicitudCreditoServiceImplTest {

    @Mock
    private SolicitudCreditoRepository solicitudCreditoRepository;

    @Mock
    private SolicitudCreditoResponseMapper solicitudCreditoResponseMapper;

    @InjectMocks
    private SolicitudCreditoServiceImpl solicitudCreditoService;

    private SolicitudCreditoDto solicitudCreditoDto;
    private SolicitudCreditoEntity solicitudCreditoEntity;
    @BeforeEach
    void setUp() {
        // Configurar datos de prueba
        solicitudCreditoDto = new SolicitudCreditoDto()
                .builder()
                .clienteDto(new ClienteDto().idCliente(1L))
                .patiosDto(new PatiosDto().idPatio(1L))
                .vehiculoDto(new VehiculoDto().idVehiculo(1L))
                .ejecutivoDto(new EjecutivoDto().builder().idEjecutivo(1L).build())
                .fechaElaboracion("2023-12-01")
                .mesesPlazo("12")
                .cuotas("24")
                .entrada("1000")
                .observacion("Solicitud de crédito para compra de vehículo")
                .estadoSolicitudCredito("R")
                .build();

        solicitudCreditoEntity = new SolicitudCreditoEntity();
        solicitudCreditoEntity.setIdSolicitudCreditos(1L);
        solicitudCreditoEntity.setIdCliente(1L);
        solicitudCreditoEntity.setIdPatio(1L);
        solicitudCreditoEntity.setIdVehiculo(1L);
        solicitudCreditoEntity.setIdEjecutivo(1L);
        solicitudCreditoEntity.setFechaElaboracion(LocalDate.now());
        solicitudCreditoEntity.setMesesPlazo("12");
        solicitudCreditoEntity.setCuotas("24");
        solicitudCreditoEntity.setEntrada("1000");
        solicitudCreditoEntity.setObservacion("Solicitud de crédito para compra de vehículo");
        solicitudCreditoEntity.setEstadoSolicitudCredito("R");

    }

    @Test
    void testSave() throws BussinesRuleException {

        // Configurar el comportamiento del mock
        when(solicitudCreditoRepository.existsByIdClienteAndFechaElaboracion(anyLong(), any()))
                .thenReturn(false); // Simula que no existe una solicitud previa

        when(solicitudCreditoResponseMapper.toSolicitudCreditoEntity(solicitudCreditoDto)).thenReturn(solicitudCreditoEntity);

        when(solicitudCreditoRepository.save(any())).thenReturn(solicitudCreditoEntity);

        when(solicitudCreditoResponseMapper.toSolicitudCreditoDTORequest(solicitudCreditoEntity)).thenReturn(solicitudCreditoDto);

        // Ejecutar el método que se está probando
        SolicitudCreditoDto result = solicitudCreditoService.save(solicitudCreditoDto);

        // Verificar que el método se llamó con los parámetros esperados
        verify(solicitudCreditoRepository).existsByIdClienteAndFechaElaboracion(any(), any());
        verify(solicitudCreditoRepository).save(any());
        verify(solicitudCreditoResponseMapper).toSolicitudCreditoDTORequest(any());

        // Realizar aserciones sobre el resultado
        assertNotNull(result);

    }

    @Test
    void testSaveWithExistingSolicitud() {

        // Simula que ya existe una solicitud previa
        when(solicitudCreditoRepository.existsByIdClienteAndFechaElaboracion(anyLong(), any()))
                .thenAnswer(invocation -> true);

        // Ejecutar el método que se está probando y verificar que se lanza la excepción esperada
        BussinesRuleException exception = assertThrows(BussinesRuleException.class,() -> solicitudCreditoService.save(solicitudCreditoDto));

        // Verificar el mensaje de la excepción
        assertEquals("Ya existe una solicitud de el cliente en la fecha enviada", exception.getType());
    }

}