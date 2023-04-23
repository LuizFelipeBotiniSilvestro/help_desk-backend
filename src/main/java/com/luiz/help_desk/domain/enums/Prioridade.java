package com.luiz.help_desk.domain.enums;

public enum Prioridade {
	
	// Admin = 0
	// Cliente = 1...
	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

	private Integer codigo;
	private String descricao;
	private Prioridade(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	
	// Static por que não precisa criar uma instancia para chamar em outras partes do código.
	public static Prioridade toEnum(Integer cod) {
		if (cod == null) {
			return null;
		};
		
		// Faz um for para ver se x.getCodigo existe.
		for (Prioridade x : Prioridade.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		};
		
		throw new IllegalArgumentException("Prioridade inválida");
	}
}
