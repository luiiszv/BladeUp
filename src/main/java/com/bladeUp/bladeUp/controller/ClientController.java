package com.bladeUp.bladeUp.controller;

import com.bladeUp.bladeUp.model.Client;
import com.bladeUp.bladeUp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // 🔵 Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Client>> getAllClientes() {
        List<Client> clientes = clienteService.getAllClients();
        return ResponseEntity.ok(clientes);
    }

    // 🔵 Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Long id) {
        Optional<Client> cliente = clienteService.getClientById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
    }

    // 🟢 Registrar un nuevo cliente
    @PostMapping("/register")
    public ResponseEntity<?> registerCliente(@RequestBody Client cliente) {
        Client nuevoCliente = clienteService.saveOrUpdateClient(cliente);
        return ResponseEntity.status(201).body(nuevoCliente);
    }

    // 🔴 Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        Optional<Client> cliente = clienteService.getClientById(id);
        if (cliente.isPresent()) {
            clienteService.deleteClientById(id);
            return ResponseEntity.ok("Cliente eliminado exitosamente");
        } else {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
    }
}