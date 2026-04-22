/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenprojectpokusaj2.controller;

import com.mycompany.mavenprojectpokusaj2.dto.impl.VoznjaDto;
import com.mycompany.mavenprojectpokusaj2.entity.impl.Voznja;
import com.mycompany.mavenprojectpokusaj2.servise.VoznjaServis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/voznja")
public class VoznjaController {

    private final VoznjaServis Servis;

    public VoznjaController(VoznjaServis v) {
        this.Servis = v;
    }

    @GetMapping
    @Operation(summary = "Retrive all Voznja entities")
    public ResponseEntity<List<VoznjaDto>> getAll() {
        return new ResponseEntity(Servis.findAll(), HttpStatus.OK);

    }

    @GetMapping("/history")
    public ResponseEntity<Object> getHistory() {
        return ResponseEntity.ok(Servis.getHistory());
    }

    @PostMapping
    @Operation(summary = "Create new Voznja")
    public ResponseEntity<VoznjaDto> addVoznja(@Valid @RequestBody @NotNull VoznjaDto v) {
        System.out.println(v);
        VoznjaDto saved = Servis.create(v);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {

        Servis.delete(id);
        return new ResponseEntity<>("Voznja uspesno obrisano", HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Voznja")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = Voznja.class), mediaType = "application/json")
    })
    public ResponseEntity<VoznjaDto> updateTipVozila(@PathVariable Long id, @Valid @RequestBody VoznjaDto v) {
        try {
            v.setId(id);
            VoznjaDto update = Servis.update(v);
            return new ResponseEntity<>(update, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greska prilikom updateovanja");

        }

    }

}
