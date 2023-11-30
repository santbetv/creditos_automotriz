package com.pichincha.testproject.controller;


import com.pichincha.services.server.ClienteApi;
import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.testproject.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "ClienteRestController API", description = "This API service all funcionality for management ClienteRestController")
@RestController
public class ClienteApiRestController implements ClienteApi {

    private final ClienteService clienteService;

    @Autowired
    public ClienteApiRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    @GetMapping("/cliente")
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<ClienteDto> clienteEntitys = clienteService.findAll();
        if (clienteEntitys == null || clienteEntitys.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(clienteEntitys);
        }
    }

    @Override
    @PostMapping("/cliente")
    public ResponseEntity<ClienteDto> addCliente(ClienteDto clienteDto) {
        return ClienteApi.super.addCliente(clienteDto);
    }

    @Override
    @PutMapping("/cliente/{id}")
    public ResponseEntity<ClienteDto> updateCliente(ClienteDto clienteDto) {
        return ClienteApi.super.updateCliente(clienteDto);
    }
}
