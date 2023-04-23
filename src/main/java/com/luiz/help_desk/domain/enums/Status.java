package com.luiz.help_desk.domain.enums;

public enum Status {
	
	// Admin = 0
	// Cliente = 1...
	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

	private Integer codigo;
	private String descricao;
	private Status(Integer codigo, String descricao) {
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
	public static Status toEnum(Integer cod) {
		if (cod == null) {
			return null;
		};
		
		// Faz um for para ver se x.getCodigo existe.
		for (Status x : Status.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		};
		
		throw new IllegalArgumentException("Status inválido");
	}
}
