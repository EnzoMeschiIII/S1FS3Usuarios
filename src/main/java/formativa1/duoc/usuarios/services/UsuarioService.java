package formativa1.duoc.usuarios.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formativa1.duoc.usuarios.repository.UsuarioRepository;
import formativa1.duoc.usuarios.entidades.Usuario;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodosLosUsuarios()
    {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(int id)
    {
        return usuarioRepository.findById(id);
    }

        public Usuario login(String email, String clave) {
        return usuarioRepository.findByEmailAndClave(email, clave);
    }

    public Usuario guardarUsuario(Usuario usuario)
    {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(int id)
    {
        usuarioRepository.deleteById(id);
    }
}
