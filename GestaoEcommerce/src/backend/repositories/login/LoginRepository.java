package backend.repositories.login;

import backend.utils.TipoLogin;

public interface LoginRepository {

	Boolean isDuplicateUser(String nome);
	
	Boolean isValidLogin(String usuario, String senha);
	
	void insertNewUser(String user, String senha, TipoLogin tipo);
	
}
