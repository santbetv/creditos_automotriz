package com.pichincha.testproject.controller;


import com.pichincha.services.server.VehiculosApi;
import com.pichincha.services.server.models.PatiosDto;
import com.pichincha.services.server.models.VehiculoDto;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.service.VehiculoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "ClienteRestController API", description = "This API service all funcionality for management ClienteRestController")
@RestController
public class VehiculosApiRestController implements VehiculosApi {

    private final VehiculoService  vehiculoService;

    public VehiculosApiRestController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @Override
    @GetMapping("/vehiculos/{id}")
    public ResponseEntity<VehiculoDto> findVehiculoById(@PathVariable String id) {
        VehiculoDto data = null;
        try {
            data = vehiculoService.findById(Long.parseLong(id));
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(200).body(data);
    }

    @Override
    @GetMapping("/vehiculos")
    public ResponseEntity<List<VehiculoDto>> getAllVehiculos() {
        List<VehiculoDto> vehiculoDtos = vehiculoService.findAll();
        if (vehiculoDtos == null || vehiculoDtos.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(vehiculoDtos);
        }
    }


    @Override
    @PostMapping("/vehiculos")
    public ResponseEntity<VehiculoDto> addVehiculo(@RequestBody VehiculoDto vehiculoDto) {
        VehiculoDto save = null;
        try {
            save = vehiculoService.save(vehiculoDto);
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(201).body(save);
    }


    @Override
    @PutMapping("/vehiculos/{id}")
    public ResponseEntity<VehiculoDto> updateVehiculoById(@PathVariable String id, @RequestBody VehiculoDto vehiculoDto) {
        VehiculoDto save = null;
        try {
            save = vehiculoService.put(vehiculoDto,Long.parseLong(id));
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(200).body(save);
    }
}
