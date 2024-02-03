package backend.utils;

public enum Categoria {

	CINTO,
	CHAVEIRO;

	
	public static Categoria obterPorValor(Integer valor) {
        for (Categoria cat : values()) 
            if (cat.ordinal() == valor)
                return cat;
        throw new IllegalArgumentException("Valor inválido: " + valor);
    }
	
}
