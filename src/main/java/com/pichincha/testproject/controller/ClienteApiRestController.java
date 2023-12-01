package com.pichincha.testproject.controller;


import com.pichincha.services.server.ClienteApi;
import com.pichincha.services.server.models.ClienteDto;
import com.pichincha.testproject.exception.BussinesRuleException;
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

//    @Override
//    @PostMapping("/cliente")
//    public ResponseEntity<ClienteDto> addCliente(@Valid ClienteDto clienteDto) {
//        ClienteDto save = clienteService.save(clienteDto);
//        return ResponseEntity.ok(save);
//    }


    @Override
    @PostMapping("/cliente")
    public ResponseEntity<ClienteDto> addCliente(@RequestBody ClienteDto clienteDto) {
        ClienteDto save = null;
        try {
            save = clienteService.save(clienteDto);
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(201).body(save);
    }


    @Override
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<ClienteDto> findPersonByIdCliente(@PathVariable String idCliente) {
        ClienteDto data = null;
        try {
            data = clienteService.findById(Long.parseLong(idCliente));
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(200).body(data);
    }

    @Override
    @PutMapping("/cliente/{idCliente}")
    public ResponseEntity<ClienteDto> updatePersonByIdCliente(@PathVariable String idCliente, @RequestBody ClienteDto clienteDto) {

        ClienteDto save = null;
        try {
            save = clienteService.put(clienteDto,Long.parseLong(idCliente));
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(200).body(save);
    }
}
