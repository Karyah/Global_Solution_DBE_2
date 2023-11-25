package com.fiap.gs.MHealth.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosUsuario(
		@NotBlank
		String cpfUsuario,
		@NotBlank
		String senhaUsuario,
		@NotBlank
		String nomeUsuario,
	
		int idadeUsuario,
		@NotBlank
		String emailUsuario,
		@NotBlank
		String rgUsuario,
		@NotBlank
		String tipoUsuario,
		@NotBlank
		String descricaoUsuario) {

}
