package com.pichincha.testproject.service.impl;

import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.services.server.models.PatiosDto;
import com.pichincha.testproject.domain.model.ClienteEntity;
import com.pichincha.testproject.domain.model.ClientePatioEntity;
import com.pichincha.testproject.domain.model.PatioEntity;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.repository.impl.ClientePatioRepository;
import com.pichincha.testproject.repository.impl.ClienteRepository;
import com.pichincha.testproject.repository.impl.PatioRepository;
import com.pichincha.testproject.service.dto.ClientePatioDto;
import com.pichincha.testproject.service.mapper.ClientePatioResponseMapper;
import com.pichincha.testproject.service.mapper.ClienteResponseMapper;
import com.pichincha.testproject.service.mapper.PatioResponseMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientePatioServiceImplTest {

    @Mock
    private ClientePatioRepository clientePatioRepositoryMock;

    @Mock
    private ClienteRepository clienteRepositoryMock;

    @Mock
    private PatioRepository patioRepositoryMock;

    @Mock
    private ClientePatioResponseMapper clientePatioResponseMapper;
    @Mock
    private ClienteResponseMapper clienteResponseMapper;
    @Mock
    private PatioResponseMapper patioResponseMapper;

    @InjectMocks
    private ClientePatioServiceImpl patioService;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//    }

    @Test
    public void testPut() throws BussinesRuleException {
        // Configura el estado de los mocks según sea necesario
        ClientePatioEntity clientePatioEntity = new ClientePatioEntity();
        clientePatioEntity.setIdClientePatios(1L);
        clientePatioEntity.setIdCliente(1L);
        clientePatioEntity.setIdPatio(1L);
        clientePatioEntity.setFechaAsignacion(LocalDate.now());
        when(clientePatioRepositoryMock.save(any(ClientePatioEntity.class))).thenReturn(clientePatioEntity);
        when(clientePatioRepositoryMock.findById(anyLong())).thenReturn(Optional.of(clientePatioEntity));


        ClienteEntity clienteEntity = new ClienteEntity();
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
        when(clienteRepositoryMock.findById(anyLong())).thenReturn(Optional.of(clienteEntity));

        PatioEntity patioEntity = new PatioEntity();
        patioEntity.setIdPatio(1L);
        patioEntity.setDireccion("Calle Principal 000");
        patioEntity.setNombre("Ejecutivo PIO");
        patioEntity.setNumeroPuntoVenta("PV001");
        patioEntity.setTelefono("555-1000");
        when(patioRepositoryMock.findById(anyLong())).thenReturn(Optional.of(patioEntity));

        // Ejecuta el método que deseas probar
        ClientePatioDto clientePatioDto = new ClientePatioDto();
        clientePatioDto.setClienteDto(new ClienteDto().idCliente(1L).nombres("Andres"));
        clientePatioDto.setPatiosDto(new PatiosDto().idPatio(1L).direccion("Calle 16 51 14"));
        clientePatioDto.setFechaAsignacion(LocalDate.now());

        ClientePatioDto resultado = patioService.put(clientePatioDto, 1L); // 1L es un ejemplo de id

        // Verificar que el método se llamó con los parámetros esperados
        verify(clientePatioRepositoryMock,atLeast(1)).findById(anyLong());
        verify(clientePatioRepositoryMock,atLeast(1)).save(any(ClientePatioEntity.class));
        verify(clienteRepositoryMock,atLeast(1)).findById(anyLong());
        verify(patioRepositoryMock,atLeast(1)).findById(anyLong());


        assertEquals(LocalDate.now(), resultado.getFechaAsignacion());

        // Realiza las aserciones necesarias
//        verify(clientePatioRepositoryMock,times(1)).findById(1L);
//        verify(clienteRepositoryMock,times(1)).findById(1L);

    }
}
