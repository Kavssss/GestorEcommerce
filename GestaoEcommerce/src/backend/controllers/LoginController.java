package backend.controllers;

import backend.services.LoginService;
import backend.utils.TipoLogin;

public class LoginController {
	
	LoginService service = new LoginService();
	
	public Boolean isDuplicateUser(String nome) {
		return service.isDuplicateUser(nome);
	}
	
	public Boolean isValidLogin(String usuario, String senha) {
		return service.isValidLogin(usuario, senha);
	}
	
	public void insertNewUser(String user, String senha, TipoLogin tipo) {
		service.insertNewUser(user, senha, tipo);
	}
	
}
