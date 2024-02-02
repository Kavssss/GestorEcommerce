package backend.entities;

import backend.utils.TipoLogin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginEntity {

	private Long id;
	private String usuario;
	private String senha;
	private TipoLogin tipoLogin;
	
}
