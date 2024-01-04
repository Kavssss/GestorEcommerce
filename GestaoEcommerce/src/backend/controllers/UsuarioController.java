package backend.controllers;

import backend.services.UsuarioService;
import frontend.utils.enums.TipoUsuario;

public class UsuarioController {
	
	UsuarioService service = new UsuarioService();
	
	public Boolean findUsuarioByName(String nome) {
		return service.findUsuarioByName(nome);
	}
	
	public void insertNewUser(String user, String senha, TipoUsuario tipo) {
		service.insertNewUser(user, senha, tipo);
	}
	
}
