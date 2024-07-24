package backend.utils;

public enum Cor {

	PRETO(1),
	MARROM(2),
	WHISKY(3),
	ARGILA(4),
	PRETO_PRETO(11),
	PRETO_MARROM(12),
	PRETO_WHISKY(13),
	MARROM_WHISKY(23),
	PRETO_ARGILA(14),
	MARROM_MARROM(22),
	MARROM_ARGILA(24),
	WHISKY_WHISKY(33),
	ARGILA_ARGILA(44);
	
	
	public Integer value;
	
	private Cor(Integer value) {
		this.value = value;
	}
	
}
