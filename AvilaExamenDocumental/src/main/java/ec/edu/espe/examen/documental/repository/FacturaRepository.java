/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.examen.documental.repository;

import ec.edu.espe.examen.documental.model.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author fanto
 */
public interface FacturaRepository extends MongoRepository<Factura, String> {
    
} 
