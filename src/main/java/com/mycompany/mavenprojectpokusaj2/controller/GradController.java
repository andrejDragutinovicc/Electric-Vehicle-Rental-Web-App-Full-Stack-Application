/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.controller;

import com.mycompany.mavenprojectpokusaj2.dto.impl.GradDto;
import com.mycompany.mavenprojectpokusaj2.dto.impl.TipVozilaDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Grad;
import com.mycompany.mavenprojectpokusaj2.servise.GradServis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/grad")
public class GradController {

    private final GradServis servis;

    public GradController(GradServis servis) {
        this.servis = servis;
    }

    @GetMapping
    @Operation(summary = "Retrive all Grad entities")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Grad.class), mediaType = "application/json")
    })
    public ResponseEntity<List<GradDto>> getAll() {
        return new ResponseEntity<>(servis.findAll(), HttpStatus.OK);

    }

    @PostMapping
    @Operation(summary = "Create new Grad")
    public ResponseEntity<GradDto> create(@RequestBody GradDto gradDto) {
        try {
            GradDto sacuvan = servis.create(gradDto);
            return new ResponseEntity<>(sacuvan, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Grad nije sacuvan");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradDto> getById(
            @NotNull(message = "id ne sme da bude null ili prazan")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(servis.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Grad nije pronadjen");
        }
    }

}
