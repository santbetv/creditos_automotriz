package com.pichincha.testproject.controller;


import com.pichincha.services.server.models.VehiculoDto;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.service.ClientePatioService;
import com.pichincha.testproject.service.dto.ClientePatioDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "ClienteRestController API", description = "This API service all funcionality for management ClienteRestController")
@RestController
public class ClientePatioApiRestController {

    private final ClientePatioService clientePatioService;

    public ClientePatioApiRestController(ClientePatioService clientePatioService) {
        this.clientePatioService = clientePatioService;
    }

    @GetMapping("/clientePatio/{id}")
    public ResponseEntity<ClientePatioDto> findClientePatioById(@PathVariable String id) {
        ClientePatioDto data = null;
        try {
            data = clientePatioService.findById(Long.parseLong(id));
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(200).body(data);
    }


//    @GetMapping("/clientePatio")
//    public ResponseEntity<List<VehiculoDto>> getAllVehiculos() {
//        List<VehiculoDto> vehiculoDtos = vehiculoService.findAll();
//        if (vehiculoDtos == null || vehiculoDtos.isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        } else {
//            return ResponseEntity.ok(vehiculoDtos);
//        }
//    }


    @PostMapping("/clientePatio")
    public ResponseEntity<ClientePatioDto> addClientePatio(@Valid @RequestBody ClientePatioDto clientePatioDto) {
        ClientePatioDto save = null;
        try {
            save = clientePatioService.save(clientePatioDto);
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(201).body(save);
    }

    @PutMapping("/clientePatio/{id}")
    public ResponseEntity<ClientePatioDto> updateVehiculoById(@PathVariable String id, @RequestBody ClientePatioDto clientePatioDto) {
        ClientePatioDto save = null;
        try {
            save = clientePatioService.put(clientePatioDto,Long.parseLong(id));
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(200).body(save);
    }
}
