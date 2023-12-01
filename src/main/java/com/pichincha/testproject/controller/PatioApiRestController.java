package com.pichincha.testproject.controller;


import com.pichincha.services.server.PatioApi;
import com.pichincha.services.server.models.PatiosDto;
import com.pichincha.testproject.exception.BussinesRuleException;
import com.pichincha.testproject.service.PatioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "ClienteRestController API", description = "This API service all funcionality for management ClienteRestController")
@RestController
public class PatioApiRestController implements PatioApi {


    private final PatioService patioService;


    @Autowired
    public PatioApiRestController(PatioService patioService) {
        this.patioService = patioService;
    }

    @Override
    @GetMapping("/patio/{id}")
    public ResponseEntity<PatiosDto> findPatioByIdPatio(@PathVariable String id) {
        PatiosDto data = null;
        try {
            data = patioService.findById(Long.parseLong(id));
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(200).body(data);
    }

    @Override
    @GetMapping("/patio")
    public ResponseEntity<List<PatiosDto>> getAllPatios() {
        List<PatiosDto> patiosDtos = patioService.findAll();
        if (patiosDtos == null || patiosDtos.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(patiosDtos);
        }
    }

    @Override
    @PostMapping("/patio")
    public ResponseEntity<PatiosDto> addPatio(@RequestBody PatiosDto patiosDto) {
        PatiosDto save = null;
        try {
            save = patioService.save(patiosDto);
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(201).body(save);
    }

    @Override
    @PutMapping("/patio/{id}")
    public ResponseEntity<PatiosDto> updatePatioById(@PathVariable String id, @RequestBody PatiosDto patiosDto) {
        PatiosDto save = null;
        try {
            save = patioService.put(patiosDto,Long.parseLong(id));
        } catch (BussinesRuleException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(200).body(save);
    }
}
