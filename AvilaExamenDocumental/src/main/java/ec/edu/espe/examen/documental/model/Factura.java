/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.examen.documental.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "factura")
public class Factura {

    @Id
    private String id;

    private String cedula;
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo;
    private List<Detalle> detalles;

    private BigDecimal total;

}
