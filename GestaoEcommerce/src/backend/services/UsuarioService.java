package backend.services;

import backend.repositories.usuario.UsuarioRepository;
import backend.repositories.usuario.UsuarioRepositoryImpl;
import frontend.utils.enums.TipoUsuario;

public class UsuarioService {

	UsuarioRepository repository = new UsuarioRepositoryImpl();
	
	public Boolean findUsuarioByName(String nome) {
		return repository.findUsuarioByName(nome);
	}
	
	public void insertNewUser(String user, String senha, TipoUsuario tipo) {
		repository.insertNewUser(user, senha, tipo);
	}

}
