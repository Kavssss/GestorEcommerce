package backend.entities.usuario;

import frontend.utils.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioEntity {

	private Long id;
	private String nome;
	private String senha;
	private TipoUsuario tipo;
	
}
