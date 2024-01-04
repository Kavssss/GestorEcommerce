package backend.repositories.usuario;

import frontend.utils.enums.TipoUsuario;

public interface UsuarioRepository {

	Boolean findUsuarioByName(String nome);
	
	void insertNewUser(String user, String senha, TipoUsuario tipo);
	
}
