/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.controller;

import com.mycompany.mavenprojectpokusaj2.dto.impl.VoziloDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Vozilo;
import com.mycompany.mavenprojectpokusaj2.servise.VoziloServis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author andre
 */
@RestController
@RequestMapping("/api/vozilo")
public class VoziloController {

    private final VoziloServis voziloServis;

    public VoziloController(VoziloServis voziloServis) {
        this.voziloServis = voziloServis;
    }

    @GetMapping
    @Operation(summary = "Retrive all Vozilo entities")
    public ResponseEntity<List<VoziloDto>> getAll() {
        return new ResponseEntity(voziloServis.findAll(), HttpStatus.OK);

    }

    @PostMapping
    @Operation(summary = "Create new Vozilo")
    public ResponseEntity<VoziloDto> addTipVozila(@Valid @RequestBody @NotNull VoziloDto voziloDto) {
        System.out.println(voziloDto);
        VoziloDto saved = voziloServis.create(voziloDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {

        voziloServis.delete(id);
        return new ResponseEntity<>("Vozilo uspesno obrisano", HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Vozilo by id")
    public ResponseEntity<VoziloDto> getById(
            @PathVariable("id") Long id) {
        try {
            VoziloDto vozilo = voziloServis.findById(id);
            return new ResponseEntity<>(vozilo, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vozilo nije pronadjeno");
        }
    }

    @PutMapping
    @Operation(summary = "Update Vozilo")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Vozilo.class), mediaType = "application/json")
    })
    public ResponseEntity<VoziloDto> updateTipVozila(@PathVariable Long id, @Valid @RequestBody VoziloDto tipVozilaDto) {
        try {
            tipVozilaDto.setId(id);
            VoziloDto update = voziloServis.update(tipVozilaDto);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom updateovanja");

        }

    }

}
