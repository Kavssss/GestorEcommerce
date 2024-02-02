package backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteEntity {

	private Long id;
	
	private String nome;
	private String usuario;
	private String cpf;
	
}
