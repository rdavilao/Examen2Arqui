/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.examen.documental.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import ec.edu.espe.examen.documental.exception.InsertException;
import ec.edu.espe.examen.documental.model.Detalle;
import ec.edu.espe.examen.documental.model.Factura;
import ec.edu.espe.examen.documental.repository.FacturaRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author fanto
 */
@Slf4j
@Service
public class FacturaService {
    
    private final FacturaRepository facturaRepo;

    public FacturaService(FacturaRepository facturaRepo) {
        this.facturaRepo = facturaRepo;
    }
    
    public void create(Factura factura) throws InsertException {
        try {
             HttpResponse<JsonNode> request = Unirest.get("http://localhost:8082/api/examen/client/findClientByCedula/{cedula}")
                        .routeParam("cedula", factura.getCedula()).asJson();
             
             factura.setCorreo(request.getBody().getObject().getString("correo"));
             factura.setDireccion(request.getBody().getObject().getString("direccion"));
             factura.setNombre(request.getBody().getObject().getString("nombre"));
             factura.setTelefono(request.getBody().getObject().getString("telefono"));
             List<Detalle> detalles = factura.getDetalles();
             BigDecimal total = new BigDecimal("0");
             for(int i = 0; i < detalles.size(); i++){
                 BigDecimal subtotal = new BigDecimal("0");
                 HttpResponse<JsonNode> requestProd = Unirest.get("http://localhost:8081/api/examen/producto/findProductoById/{id}")
                        .routeParam("id", detalles.get(i).getCodProducto()).asJson();
                subtotal = requestProd.getBody().getObject().getBigDecimal("valorUnitario");
                BigDecimal cantidad = new BigDecimal(detalles.get(i).getCantidad().toString());
                subtotal = subtotal.multiply(cantidad);
                if("S".equals(requestProd.getBody().getObject().getString("aplicaIva"))){
                     subtotal = subtotal.multiply(new BigDecimal("1.12"));
                }
                total = total.add(subtotal);
             }
            factura.setTotal(total);
        } catch (Exception e) {
            throw new InsertException("factura", "Erro al crear factura");
        }
    }
}
