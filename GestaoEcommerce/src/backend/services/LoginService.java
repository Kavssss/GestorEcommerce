package backend.services;

import backend.repositories.login.LoginRepository;
import backend.repositories.login.LoginRepositoryImpl;
import backend.utils.TipoLogin;

public class LoginService {

	LoginRepository repository = new LoginRepositoryImpl();
	
	public Boolean isDuplicateUser(String nome) {
		return repository.isDuplicateUser(nome);
	}
	
	public Boolean isValidLogin(String usuario, String senha) {
		return repository.isValidLogin(usuario, senha);
	}
	
	public void insertNewUser(String user, String senha, TipoLogin tipo) {
		repository.insertNewUser(user, senha, tipo);
	}

}
