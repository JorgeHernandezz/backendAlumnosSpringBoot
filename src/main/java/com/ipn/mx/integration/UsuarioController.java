package com.ipn.mx.integration;

import com.ipn.mx.domain.entity.Usuario;
import com.ipn.mx.service.EmailService;
import com.ipn.mx.service.UsuarioService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/apiUsuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private EmailService emailService;

    
    
    
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obtenerTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No existe ningún alumno con id: " + id);
        }
    }


    
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioActualizado) {
        Optional<Usuario> optionalUsuario = usuarioService.findById(id);
        
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setPaterno(usuarioActualizado.getPaterno());
            usuario.setMaterno(usuarioActualizado.getMaterno());
            usuario.setEmail(usuarioActualizado.getEmail());
            
            Usuario usuarioActualizadoDb = usuarioService.save(usuario);
            return ResponseEntity.ok("Se actualizó correctamente el alumno con id: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No existe ningún alumno con id: " + id);
        }
    }


    
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.ok("Se eliminó correctamente el alumno con id: " + id);
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No existe ningún alumno con id: " + id);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Ocurrió un error al intentar eliminar el alumno");
        }
    }



    
    
    
    
    
    @PostMapping("/usuarios")
    public ResponseEntity<?> insertarUsuario(@RequestBody Usuario usuario) {
        try {
            // Verificar si ya existe un usuario con el mismo email
            Optional<Usuario> usuarioExistente = usuarioService.findByEmail(usuario.getEmail());
            if (usuarioExistente.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("Ya existe un alumno registrado con el email: " + usuario.getEmail());
            }

            // Si no existe, proceder con la inserción
            Usuario nuevoUsuario = usuarioService.save(usuario);
            String to = nuevoUsuario.getEmail();
            String subject = "Bienvenido a API alumnos";
            String text = "¡Gracias por registrarte en nuestra aplicación!";
            emailService.sendSimpleMessage(to, subject, text);
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body("Se insertó correctamente el alumno con id: " + nuevoUsuario.getIdusuario());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Ocurrió un error al intentar insertar el usuario");
        }
    }


}
