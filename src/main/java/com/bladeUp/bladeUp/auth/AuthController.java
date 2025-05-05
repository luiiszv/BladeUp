package com.bladeUp.bladeUp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bladeUp.bladeUp.exception.ErrorResponse;
import com.bladeUp.bladeUp.model.Barbero;
import com.bladeUp.bladeUp.model.Cliente;
import com.bladeUp.bladeUp.repository.BarberoRepository;
import com.bladeUp.bladeUp.repository.ClienteRepository;
import com.bladeUp.bladeUp.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarberoRepository barberoRepository;

    private ErrorResponse errores = new ErrorResponse("Login Exitoso", 200);

    @PostMapping("/register/cliente")
    public Cliente registrarCliente(@RequestBody Cliente cliente) {
        cliente.setRol("CLIENTE");
        return clienteRepository.save(cliente);
    }

    @PostMapping("/register/barbero")
    public Barbero registrarBarbero(@RequestBody Barbero barbero) {
        barbero.setRol("BARBERO");
        return barberoRepository.save(barbero);
    }

    @PostMapping("/login/cliente")
    public String loginCliente(@RequestBody LoginDTO loginDTO) {
        Cliente cliente = clienteRepository.findByCorreo(loginDTO.getCorreo());
        if (cliente != null && cliente.getPassword().equals(loginDTO.getPassword())) {
            String token = JwtUtils.generateToken(cliente.getCorreo(), cliente.getRol());
            return "Token: " + token + errores.getStatus();

        } else {
            return "Credenciales de Cliente incorrectas" + errores.getStatus();
        }
    }

    @PostMapping("/login/barbero")
    public String loginBarbero(@RequestBody LoginDTO loginDTO) {
        Barbero barbero = barberoRepository.findByCorreo(loginDTO.getCorreo());
        if (barbero != null && barbero.getPassword().equals(loginDTO.getPassword())) {
            String token = JwtUtils.generateToken(barbero.getCorreo(), barbero.getRol());
            return "Token: " + token;
        } else {
            return "Credenciales de Barbero incorrectas";
        }
    }
}