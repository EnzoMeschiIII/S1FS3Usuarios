package formativa1.duoc.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import formativa1.duoc.usuarios.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    Usuario findByEmailAndClave(String email, String clave);
}
