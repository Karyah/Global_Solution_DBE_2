package com.fiap.gs.MHealth.model;

import com.fiap.gs.MHealth.dto.DadosPedidoComida;
import com.fiap.gs.MHealth.dto.DadosPedidoRemedio;
import com.fiap.gs.MHealth.dto.DadosRequisicao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean foiAceito;
	@Column(nullable = true)
	private String localDor;
	@Column(nullable = true)
	private Integer nivelDor;
	@Column(nullable = true)
	private String tipoMedicacao;
	@Column(nullable = true)
	private String tipoComida;
	@Column(nullable = true)
	private String temperatura;
	@Column(nullable = true)
	private String acompanhante;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	public Usuario usuario;
	
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Pedido(DadosPedidoComida dados) {
		this.foiAceito = true;
		this.tipoComida = dados.tipoComida();
		this.temperatura = dados.temperatura();
		this.acompanhante = dados.acompanhante();
	}

	public Pedido(DadosPedidoRemedio dados) {
		this.foiAceito = true;
		this.nivelDor = dados.nivelDor();
		this.localDor = dados.localDor();
		this.tipoMedicacao = dados.tipoMedicacao();
	}
	
	public Pedido(DadosRequisicao dados) {
		super();
		this.foiAceito = dados.foiAceito();
		this.localDor = dados.localDor();
		this.nivelDor = dados.nivelDor();
		this.tipoMedicacao = dados.tipoMedicacao();
		this.tipoComida = dados.tipoComida();
		this.acompanhante = dados.acompanhante();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isFoiAceito() {
		return foiAceito;
	}

	public void setFoiAceito(boolean foiAceito) {
		this.foiAceito = foiAceito;
	}

	public String getLocalDor() {
		return localDor;
	}

	public void setLocalDor(String localDor) {
		this.localDor = localDor;
	}
	
	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getTipoMedicacao() {
		return tipoMedicacao;
	}

	public void setTipoMedicacao(String tipoMedicacao) {
		this.tipoMedicacao = tipoMedicacao;
	}

	public String getTipoComida() {
		return tipoComida;
	}

	public void setTipoComida(String tipoComida) {
		this.tipoComida = tipoComida;
	}

	public String getAcompanhante() {
		return acompanhante;
	}

	public void setAcompanhante(String acompanhante) {
		this.acompanhante = acompanhante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setNivelDor(Integer nivelDor) {
		this.nivelDor = nivelDor;
	}
	
	
	
}
