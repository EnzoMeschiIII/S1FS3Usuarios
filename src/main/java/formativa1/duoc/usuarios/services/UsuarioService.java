package formativa1.duoc.usuarios.services;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formativa1.duoc.usuarios.repository.UsuarioRepository;
import formativa1.duoc.usuarios.entidades.Usuario;

@Service
public class UsuarioService {
    
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class.getName());

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodosLosUsuarios()
    {
        logger.info("Obteniendo todos los Usuarios");
        logger.debug("Query ejecutada: SELECT * FROM Usuarios");
        List<Usuario> usuario = usuarioRepository.findAll();
        if(usuario.isEmpty()){
            logger.warn("No se encontraron datos de Usuario en la BD");
        }
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(int id)
    {
        logger.info("Obteniendo usuario de ID: "+ id);
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            logger.warn("Usuario de ID: "+id+" no encontrado");
        }
        return usuarioRepository.findById(id);
    }

        public Usuario login(String email, String clave) {
        return usuarioRepository.findByEmailAndClave(email, clave);
    }

    public Usuario guardarUsuario(Usuario usuario)
    {
        logger.info("Guardando usuario: "+ usuario.getNombre());
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(int id)
    {
        logger.info("Eliminando usuario de ID: "+id);
        if(!usuarioRepository.existsById(id)){
            logger.warn("No se puede eliminar el usuario de ID: "+id+" ya que no se encontro");
            return;
        }
        usuarioRepository.deleteById(id);
    }
}
