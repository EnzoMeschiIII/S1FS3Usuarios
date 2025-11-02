package formativa1.duoc.usuarios.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacio")
    @Size(min = 2, max = 150, message = "El email debe tener entre 2 y 150 caracteres")
    private String email;

    @NotBlank(message = "La clave no puede estar vacia")
    @Size(min = 2, max = 150, message = "La clave debe tener entre 2 y 50 caracteres")
    private String clave;

    @NotBlank(message = "El rol no puede estar vacio")
    @Pattern(regexp = "^(usuario|admin)$", message = "El rol debe ser 'usuario' o 'admin'")
    private String rol;
}
