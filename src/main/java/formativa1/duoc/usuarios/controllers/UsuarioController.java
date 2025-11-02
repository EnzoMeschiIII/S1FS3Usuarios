package formativa1.duoc.usuarios.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import formativa1.duoc.usuarios.entidades.Usuario;
import formativa1.duoc.usuarios.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    //Login primer intento
     @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario datosLogin) {
        Usuario usuario = usuarioService.login(datosLogin.getEmail(), datosLogin.getClave());

        if (usuario == null) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        // Simulación de redirección
        if (usuario.getRol().equalsIgnoreCase("Admin")) {
            return ResponseEntity.ok("Bienvenido ADMIN");
        } else {
            return ResponseEntity.ok("Bienvenido USUARIO");
        }
    }

    //Metodos de solicitudes http
    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios()
    {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obtenerUsuarioPorId(@PathVariable int id)
    {
        return  usuarioService.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario)
    {
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario)
    {
        Optional<Usuario> usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
        if(usuarioExistente.isPresent())
        {
            Usuario user = usuarioExistente.get();
            user.setNombre(usuario.getNombre());
            user.setEmail(usuario.getEmail());
            user.setClave(usuario.getClave());
            user.setRol(usuario.getRol());
            Usuario usuarioActualizado = usuarioService.guardarUsuario(user);
            return ResponseEntity.ok(usuarioActualizado);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id)
    {
        Optional<Usuario> usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
        if(usuarioExistente.isPresent())
        {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
