package com.luiz.help_desk.domain.enums;

public enum Perfil {
	
	// Admin = 0
	// Cliente = 1...
	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");

	private Integer codigo;
	private String descricao;
	private Perfil(Integer codigo, String descricao) {
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
	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		};
		
		// Faz um for para ver se x.getCodigo existe.
		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		};
		
		throw new IllegalArgumentException("Perfil inválido");
	}
}
