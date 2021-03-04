/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.relacional1.service;

import ec.edu.espe.relacional1.exception.DocumentNotFoundException;
import ec.edu.espe.relacional1.exception.InsertException;
import ec.edu.espe.relacional1.model.Cliente;
import ec.edu.espe.relacional1.repository.ClienteRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

/**
 *
 * @author fanto
 */
@Slf4j
@Service
public class ClienteService {

    private final ClienteRepository clientRepo;

    public ClienteService(ClienteRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public void create(Cliente client) throws InsertException {
        try {
            if (validarCed(client.getCedula())) {
                log.info("Creando cliente");
                this.clientRepo.save(client);
            } else {
                log.error("Cedula incorrecta");
                throw new InsertException("client", "Cedula incorrecta");
            }
        } catch (Exception e) {
            throw new InsertException("client", "Erro al crear cliente");
        }
    }

    public Cliente findByCedula(String cedula) throws DocumentNotFoundException {
        try {
            log.info("Buscando cliente");
            Optional<Cliente> clientFind = this.clientRepo.findById(cedula);
            if (clientFind.isPresent()) {
                return clientFind.get();
            } else {
                throw new DocumentNotFoundException("Cliente no existe");
            }
        } catch (Exception e) {
            throw new DocumentNotFoundException("Error al buscar cliente");
        }
    }

    private Boolean validarCed(String cedula) {
        Integer suma = 0;
        Integer mul = 2;
        Integer res = 0;
        if (cedula.length() == 10) {
            for (int i = 0; i < 9; i++) {
                int num = Integer.parseInt(Character.toString(cedula.charAt(i))) * mul;
                suma += num >= 10 ? num - 9 : num;
                mul = i % 2 == 0 ? 1 : 2;
            }

            Integer superior = ((suma / 10) * 10) + 10;
            res = superior - suma >= 10 ? (superior - suma) - 10 : superior - suma;

            if (res == Integer.parseInt(Character.toString(cedula.charAt(9)))) {
                return true;
            }
        }
        return false;
    }

}
