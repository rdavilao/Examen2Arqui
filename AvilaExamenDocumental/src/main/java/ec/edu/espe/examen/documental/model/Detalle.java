/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.examen.documental.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author fanto
 */
@Data
@Builder
public class Detalle {
    
    private String codProducto;
    private Integer cantidad;
    private BigDecimal subtotal;
}
