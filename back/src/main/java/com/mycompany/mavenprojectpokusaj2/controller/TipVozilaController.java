/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.controller;

import com.mycompany.mavenprojectpokusaj2.dto.impl.TipVozilaDto;
import com.mycompany.mavenprojectpokusaj2.servise.TipVozilaServis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.data.util.TypeCollector;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/tipvozila")
public class TipVozilaController {

    private final TipVozilaServis tipVozilaServis;

    public TipVozilaController(TipVozilaServis tipVozilaServis) {
        this.tipVozilaServis = tipVozilaServis;
    }

    @GetMapping
    @Operation(summary = "Retrive all TipVozila entities")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = TipVozilaDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<TipVozilaDto>> getAll() {
        return new ResponseEntity<>(tipVozilaServis.findAll(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TipVozilaDto> getById(
            @NotNull(message = "id ne sme da bude null ili prazan")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(tipVozilaServis.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tip vozila nije pronadjen");
        }
    }

    @PostMapping
    @Operation(summary = "Create new TipVozila")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(implementation = TipVozilaDto.class), mediaType = "application/json")
    })
    public ResponseEntity<TipVozilaDto> addTipVozila(@Valid @RequestBody @NotNull TipVozilaDto tipVozilaDto) {
        System.out.println(tipVozilaDto);
        TipVozilaDto saved = tipVozilaServis.create(tipVozilaDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {

        tipVozilaServis.delete(id);
        return new ResponseEntity<>("Tip uspesno obrisan", HttpStatus.OK);

    }

    @PutMapping
    @Operation(summary = "uBDATE new TipVozila")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = TipVozilaDto.class), mediaType = "application/json")
    })
    public ResponseEntity<TipVozilaDto> updateTipVozila(@PathVariable Long id,@Valid @RequestBody TipVozilaDto tipVozilaDto){
        try {
            tipVozilaDto.setId(id);
            TipVozilaDto update = tipVozilaServis.update(tipVozilaDto);
            return new ResponseEntity<>(update,HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Greska prilikom updateovanja");
            
        }
        
        
    }
    

}
