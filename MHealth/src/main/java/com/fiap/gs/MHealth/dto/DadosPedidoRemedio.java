package com.fiap.gs.MHealth.dto;

import jakarta.validation.constraints.NotBlank;


public record DadosPedidoRemedio(
		@NotBlank
		String cpfUsuario,
		int nivelDor,
		@NotBlank
		String localDor,
		@NotBlank
		String tipoMedicacao) {

}
