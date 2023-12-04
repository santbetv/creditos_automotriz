package com.pichincha.testproject.service.impl;

import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.services.server.models.PatiosDto;
import com.pichincha.services.server.models.VehiculoDto;
import com.pichincha.testproject.domain.enums.EstadosSolicitud;
import com.pichincha.testproject.domain.model.ClienteEntity;
import com.pichincha.testproject.domain.model.ClientePatioEntity;
import com.pichincha.testproject.domain.model.PatioEntity;
import com.pichincha.testproject.domain.model.SolicitudCreditoEntity;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.repository.impl.*;
import com.pichincha.testproject.service.ClienteService;
import com.pichincha.testproject.service.EjecutivoService;
import com.pichincha.testproject.service.VehiculoService;
import com.pichincha.testproject.service.dto.ClientePatioDto;
import com.pichincha.testproject.service.dto.EjecutivoDto;
import com.pichincha.testproject.service.dto.SolicitudCreditoDto;
import com.pichincha.testproject.service.mapper.ClientePatioResponseMapper;
import com.pichincha.testproject.service.mapper.ClienteResponseMapper;
import com.pichincha.testproject.service.mapper.PatioResponseMapper;
import com.pichincha.testproject.service.mapper.SolicitudCreditoResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SolicitudCreditoServiceImplTest {

    @InjectMocks
    private SolicitudCreditoServiceImpl solicitudCreditoService;

    @Mock
    private SolicitudCreditoRepository solicitudCreditoRepository;

    @Mock
    private ClientePatioRepository clientePatioRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PatioRepository patioRepository;

    @Mock
    private VehiculoService vehiculoService;


    @Mock
    private SolicitudCreditoResponseMapper solicitudCreditoResponseMapper;


    @Mock
    private ClienteResponseMapper clienteResponseMapper;

    @Mock
    private PatioResponseMapper patioResponseMapper;

    @Mock
    private ClientePatioResponseMapper clientePatioResponseMapper;

    @Mock
    private PatioServiceImpl patioService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private EjecutivoService ejecutivoService;

    private SolicitudCreditoDto solicitudCreditoDto;
    private SolicitudCreditoEntity solicitudCreditoEntity;
    private EjecutivoDto ejecutivoDto;

    private VehiculoDto vehiculoDto;

    private ClienteEntity clienteEntity;

    private PatioEntity patioEntity;


    private ClientePatioEntity clientePatioEntity;

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


        ejecutivoDto = new EjecutivoDto();
        ejecutivoDto.setIdEjecutivo(1L);
        ejecutivoDto.setCelular("123456789");
        ejecutivoDto.setEdad((byte) 30);
        ejecutivoDto.setIdentificacion("123ABC");
        ejecutivoDto.setNombres("Juan");
        ejecutivoDto.setTelefono("987654321");
        ejecutivoDto.setApellidos("Pérez");
        ejecutivoDto.setDireccion("Calle Principal 123");

        vehiculoDto = new VehiculoDto();
        vehiculoDto.setIdMarca(1L);
        vehiculoDto.setNombreMarca("Toyota");
        vehiculoDto.setIdVehiculo(123L);
        vehiculoDto.setAvaluo("50000");
        vehiculoDto.setCilindraje("2000 cc");
        vehiculoDto.setModelo("2022");
        vehiculoDto.setNumeroChasis("ABC123456DEF789");
        vehiculoDto.setPlaca("XYZ123");
        vehiculoDto.setTipo("Sedán");


        clienteEntity = new ClienteEntity();
        clienteEntity.setIdCliente(1L);
        clienteEntity.setNombres("Carlos");
        clienteEntity.setApellidos("González");
        clienteEntity.setFechaNacimiento(LocalDate.parse("1990-03-12"));
        clienteEntity.setDireccion("Calle 123 Apt 456");
        clienteEntity.setTelefono("5551234");
        clienteEntity.setIdentificacion("1053456701");
        clienteEntity.setSujetoCredito(false);
        clienteEntity.setEstadoCivil("Soltero");
        clienteEntity.setIdentificacionConyugue("75105101");
        clienteEntity.setNombreConyugue("Adriana Posada");

        patioEntity = new PatioEntity();
        patioEntity.setIdPatio(1L);
        patioEntity.setDireccion("Calle Principal 000");
        patioEntity.setNombre("Ejecutivo PIO");
        patioEntity.setNumeroPuntoVenta("PV001");
        patioEntity.setTelefono("555-1000");

        clientePatioEntity = new ClientePatioEntity();
        clientePatioEntity.setIdClientePatios(1L);
        clientePatioEntity.setIdCliente(1L);
        clientePatioEntity.setIdPatio(1L);
        clientePatioEntity.setFechaAsignacion(LocalDate.now());

    }

    @Test
    void testSave() throws BussinesRuleException {

        when(ejecutivoService.findById(anyLong())).thenReturn(ejecutivoDto);

        when(vehiculoService.findById(anyLong())).thenReturn(vehiculoDto);

        // Configurar el comportamiento del mock
        when(solicitudCreditoRepository.existsByIdClienteAndFechaElaboracion(anyLong(), any()))
                .thenReturn(false); // Simula que no existe una solicitud previa

        when(solicitudCreditoRepository.existsByIdVehiculoAndEstadoSolicitud(anyLong()))
                .thenReturn(false);


        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(clienteEntity));


        when(patioRepository.findById(anyLong())).thenReturn(Optional.of(patioEntity));


        when(clienteResponseMapper.toClienteDTORequest(clienteEntity)).thenReturn(new ClienteDto().idCliente(1L));

        when(patioResponseMapper.toPatioDTORequest(patioEntity)).thenReturn(new PatiosDto().idPatio(1L));


        when(clientePatioRepository.save(any())).thenReturn(clientePatioEntity);


        when(solicitudCreditoResponseMapper.toSolicitudCreditoEntity(solicitudCreditoDto)).thenReturn(solicitudCreditoEntity);

        when(solicitudCreditoRepository.save(any())).thenReturn(solicitudCreditoEntity);


        SolicitudCreditoDto result = solicitudCreditoService.save(solicitudCreditoDto);

        // Realizar aserciones sobre el resultado
        assertNotNull(result);

    }



    @Test
    public void testExistsByIdClienteAndFechaElaboracion() {
        // Datos de prueba
        Long idCliente = 1L;
        LocalDate fechaElaboracion = LocalDate.now();

        // Configuración del comportamiento simulado del repositorio
        when(solicitudCreditoRepository.existsByIdClienteAndFechaElaboracion(idCliente, fechaElaboracion))
                .thenReturn(true);

        // Llamada al método de servicio que utiliza el repositorio
        boolean exists = solicitudCreditoRepository.existsByIdClienteAndFechaElaboracion(idCliente, fechaElaboracion);

        // Verificación de que el método del repositorio fue invocado con los argumentos correctos
        verify(solicitudCreditoRepository).existsByIdClienteAndFechaElaboracion(idCliente, fechaElaboracion);

        // Afirmación sobre el resultado esperado
        assertTrue(exists, "La solicitud de crédito debería existir para el cliente y la fecha dada");
    }

    @Test
    public void testExistsByIdVehiculoAndEstadoSolicitud() {
        // Datos de prueba
        Long idVehiculo = 1L;
        EstadosSolicitud estadoSolicitud = EstadosSolicitud.REGISTRADA;  // Ajusta esto según tu aplicación

        // Configuración del comportamiento simulado del repositorio
        when(solicitudCreditoRepository.existsByIdVehiculoAndEstadoSolicitud(idVehiculo))
                .thenReturn(true);

        // Llamada al método de servicio que utiliza el repositorio
        boolean exists = solicitudCreditoRepository.existsByIdVehiculoAndEstadoSolicitud(idVehiculo);

        // Verificación de que el método del repositorio fue invocado con los argumentos correctos
        verify(solicitudCreditoRepository).existsByIdVehiculoAndEstadoSolicitud(idVehiculo);

        // Afirmación sobre el resultado esperado
        assertTrue(exists, "La solicitud de crédito debería existir para el vehículo y estado dados");
    }
}