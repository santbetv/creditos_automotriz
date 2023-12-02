package com.pichincha.testproject.controller;


import com.pichincha.services.server.VehiculosApi;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.service.SolicitudCreditoService;
import com.pichincha.testproject.service.dto.ClientePatioDto;
import com.pichincha.testproject.service.dto.SolicitudCreditoDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "ClienteRestController API", description = "This API service all funcionality for management ClienteRestController")
@RestController
public class SolicitudCreditoApiRestController {

    private final SolicitudCreditoService solicitudCreditoService;


    @Autowired
    public SolicitudCreditoApiRestController(SolicitudCreditoService solicitudCreditoService) {
        this.solicitudCreditoService = solicitudCreditoService;
    }

    @PostMapping("/solicitudCredito")
    public ResponseEntity<SolicitudCreditoDto> addSolicitudCredito(@Valid @RequestBody SolicitudCreditoDto SolicitudCreditoDto) {
        SolicitudCreditoDto save = null;
        try {
            save = solicitudCreditoService.save(SolicitudCreditoDto);
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(201).body(save);
    }
}
