package backend.controllers;

import backend.services.UsuarioService;
import frontend.utils.enums.TipoUsuario;

public class UsuarioController {
	
	UsuarioService service = new UsuarioService();
	
	public Boolean isDuplicateUser(String nome) {
		return service.isDuplicateUser(nome);
	}
	
	public Boolean isValidLogin(String usuario, String senha) {
		return service.isValidLogin(usuario, senha);
	}
	
	public void insertNewUser(String user, String senha, TipoUsuario tipo) {
		service.insertNewUser(user, senha, tipo);
	}
	
}
