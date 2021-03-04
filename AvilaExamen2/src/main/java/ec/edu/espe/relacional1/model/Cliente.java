/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.relacional1.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author fanto
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cliente")

public class Cliente {    
    @Id
    @Column(name = "CEDULA")
    private String cedula;
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "DIRECCION")
    private String direccion;
    
    @Column(name = "TELEFONO")
    private String telefono;
    
    @Column(name = "CORREO")
    private String correo;
}
