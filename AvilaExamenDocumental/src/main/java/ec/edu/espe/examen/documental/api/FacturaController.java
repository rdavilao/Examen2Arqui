/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.examen.documental.api;

import ec.edu.espe.examen.documental.exception.InsertException;
import ec.edu.espe.examen.documental.model.Factura;
import ec.edu.espe.examen.documental.service.FacturaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fanto
 */
@CrossOrigin(maxAge=3600)
@RestController

@RequestMapping("/api/examen/factura")
@Slf4j
public class FacturaController {
    private final FacturaService service;

    public FacturaController(FacturaService service) {
        this.service = service;
    }
    
    @PostMapping("/create")
    @ApiOperation(value = "Crea una factura", notes = "Crea una factura")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Factura creada"),
        @ApiResponse(code = 400, message = "Error al crear factra")
    })
    public ResponseEntity create(@RequestBody Factura factura) {
        try {
            this.service.create(factura);
            return ResponseEntity.ok().build();
        } catch (InsertException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
