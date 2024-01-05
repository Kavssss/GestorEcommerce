package backend.repositories.usuario;

import frontend.utils.enums.TipoUsuario;

public interface UsuarioRepository {

	Boolean isDuplicateUser(String nome);
	
	Boolean isValidLogin(String usuario, String senha);
	
	void insertNewUser(String user, String senha, TipoUsuario tipo);
	
}
