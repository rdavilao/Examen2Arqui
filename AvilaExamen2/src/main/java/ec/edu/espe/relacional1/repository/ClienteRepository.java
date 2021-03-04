/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.relacional1.repository;

import ec.edu.espe.relacional1.model.Cliente;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author fanto
 */
public interface ClienteRepository extends CrudRepository<Cliente, String>{
    
}
