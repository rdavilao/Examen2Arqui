/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.examen.relacional.service;

import ec.edu.espe.examen.relacional.exception.DocumentNotFoundException;
import ec.edu.espe.examen.relacional.exception.InsertException;
import ec.edu.espe.examen.relacional.exception.UpdateException;
import ec.edu.espe.examen.relacional.model.Producto;
import ec.edu.espe.examen.relacional.repository.ProductoRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductoService {

    private final ProductoRepository productoRepo;

    public ProductoService(ProductoRepository productoRepo) {
        this.productoRepo = productoRepo;
    }

    public void create(Producto producto) throws InsertException {
        try {
            log.info("Creando producto");
            Producto lastProduct = this.productoRepo.findTopByOrderByCodigoDesc();
            producto.setCodigo(String.valueOf(Integer.parseInt(lastProduct.getCodigo())));
            this.productoRepo.save(producto);
        } catch (Exception e) {
            throw new InsertException("producto", "Error al crear un producto: " + producto.getNombre());
        }
    }

    public Producto findByCod(String codigo) throws DocumentNotFoundException {
        try {
            log.info("Buscando producto "+codigo);
            Optional<Producto> productFind = this.productoRepo.findById(codigo);
            if (!productFind.isEmpty()) {
                log.info("Buscando producto con codigo: "+codigo);
                Producto producto = productFind.get();
                return producto;
            } else {
                throw new DocumentNotFoundException("No existe el producto con codigo: " + codigo);
            }
        } catch (Exception e) {
            throw new DocumentNotFoundException("Error al buscar producto " + codigo);
        }
    }

    public void update(Producto producto) throws UpdateException {
        try {
            log.info("Actualizando  producto");
            Optional<Producto> productUpdate = this.productoRepo.findById(producto.getCodigo());            
            if(!productUpdate.isEmpty()){
                productUpdate.get().setNombre((producto.getNombre() != null) ? producto.getNombre() : productUpdate.get().getNombre());
                productUpdate.get().setExistencia((producto.getExistencia() != null) ? producto.getExistencia(): productUpdate.get().getExistencia());
                productUpdate.get().setValorUnitario((producto.getValorUnitario()!= null) ? producto.getValorUnitario(): productUpdate.get().getValorUnitario());
                productUpdate.get().setAplicaIva((producto.getAplicaIva()!= null) ? producto.getAplicaIva(): productUpdate.get().getAplicaIva());
                this.productoRepo.save(productUpdate.get());    
                
            }else{
                throw new UpdateException("producto", "No existe el producto " + producto.getNombre());
            }
        } catch (Exception e) {
             throw new UpdateException("producto", "Ocurrio un error al actualizar el producto: " + producto.getNombre(), e);
        }

    }
}
