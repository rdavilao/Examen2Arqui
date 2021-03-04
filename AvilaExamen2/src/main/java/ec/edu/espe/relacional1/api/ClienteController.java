/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.relacional1.api;

import ec.edu.espe.relacional1.exception.DocumentNotFoundException;
import ec.edu.espe.relacional1.exception.InsertException;
import ec.edu.espe.relacional1.model.Cliente;
import ec.edu.espe.relacional1.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

@RequestMapping("/api/examen/client")
@Slf4j

public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }
    
    @GetMapping("/findClientByCedula/{cedula}")
    @ApiOperation(value = "Busqueda de cliente por su cedula", notes = "Busqueda de cliente por su cedula.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente encontrado"),
        @ApiResponse(code = 404, message = "Cliente no existente")
    })
    public ResponseEntity findClientByCedula(@PathVariable String cedula) {        
        try {
            return ResponseEntity.ok(this.service.findByCedula(cedula));
        }catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/create")
    @ApiOperation(value = "Crea un cliente", notes = "Crea un cliente del Banco.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente creado"),
        @ApiResponse(code = 400, message = "Error al crear cliente")
    })
    public ResponseEntity create(@RequestBody Cliente client) {
        try {
            this.service.create(client);
            return ResponseEntity.ok().build();
        } catch (InsertException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
    
}
