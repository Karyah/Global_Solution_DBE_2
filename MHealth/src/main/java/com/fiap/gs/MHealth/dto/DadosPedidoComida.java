package com.fiap.gs.MHealth.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosPedidoComida(
		@NotBlank
		String cpfUsuario,
		@NotBlank
		String tipoComida,
		@NotBlank
		String acompanhante,
		@NotBlank
		String temperatura){

}
