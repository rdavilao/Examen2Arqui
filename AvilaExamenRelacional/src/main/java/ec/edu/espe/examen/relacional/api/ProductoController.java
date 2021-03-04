/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.examen.relacional.api;

import ec.edu.espe.examen.relacional.exception.DocumentNotFoundException;
import ec.edu.espe.examen.relacional.exception.InsertException;
import ec.edu.espe.examen.relacional.exception.UpdateException;
import ec.edu.espe.examen.relacional.model.Producto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ec.edu.espe.examen.relacional.service.ProductoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author fanto
 */
@CrossOrigin(maxAge = 3600)
@RestController

@RequestMapping("/api/examen/producto")
@Slf4j

public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping("/findProductoById/{cod}")
    @ApiOperation(value = "Busqueda de prodcuto por codigo", notes = "Busqueda de producto por su codigo.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Producto encontrado"),
        @ApiResponse(code = 404, message = "Producto no existente")
    })
    public ResponseEntity findProductoById(@PathVariable String cod) {
        try {
            return ResponseEntity.ok(this.service.findByCod(cod));
        } catch (DocumentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "Actualiza campos de un producto", notes = "Actualiza campos permitidos de un producto.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente actualizado"),
        @ApiResponse(code = 400, message = "Error al actualizar cliente")
    })
    public ResponseEntity update(@RequestBody Producto updateProducto) {
        try {
            this.service.update(updateProducto);
            return ResponseEntity.ok().build();
        } catch (UpdateException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/create")
    @ApiOperation(value = "Crea un producto", notes = "Crea un producto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Producto creado"),
        @ApiResponse(code = 400, message = "Error al crear producto")
    })
    public ResponseEntity create(@RequestBody Producto producto) {
        try {
            this.service.create(producto);
            return ResponseEntity.ok().build();
        } catch (InsertException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
