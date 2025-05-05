package com.bladeUp.bladeUp.controller;

import com.bladeUp.bladeUp.model.dto.request.ClientRequestDto;
import com.bladeUp.bladeUp.payload.ApiResponse;
import com.bladeUp.bladeUp.model.dto.response.ClientResponseDTO;
import com.bladeUp.bladeUp.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    private final ClientService clientService;


    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDTO>> getClienteById(@PathVariable Long id) {
        try {
            ClientResponseDTO cliente = clientService.getClientById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Customer found", cliente)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientResponseDTO>>> getAllClientes() {
        List<ClientResponseDTO> clientes = clientService.getAllClients();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Customers found", clientes)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCliente(@PathVariable Long id) {
        if (clientService.existsClientById(id)) {
            clientService.deleteClientById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Customer successfully deleted", null)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "Customer not found", null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientResponseDTO>> register(@Valid @RequestBody ClientRequestDto dto) {
        ClientResponseDTO client = clientService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Customer successfully registered", client));
    }
}