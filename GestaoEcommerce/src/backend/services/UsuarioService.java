package backend.services;

import backend.repositories.usuario.UsuarioRepository;
import backend.repositories.usuario.UsuarioRepositoryImpl;
import frontend.utils.enums.TipoUsuario;

public class UsuarioService {

	UsuarioRepository repository = new UsuarioRepositoryImpl();
	
	public Boolean isDuplicateUser(String nome) {
		return repository.isDuplicateUser(nome);
	}
	
	public Boolean isValidLogin(String usuario, String senha) {
		return repository.isValidLogin(usuario, senha);
	}
	
	public void insertNewUser(String user, String senha, TipoUsuario tipo) {
		repository.insertNewUser(user, senha, tipo);
	}

}
