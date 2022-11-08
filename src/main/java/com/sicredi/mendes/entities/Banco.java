package com.sicredi.mendes.entities;

public class Banco {
	
	private String agencia;
	private String conta;
	private Double saldo;
	private String status;
	private Boolean atualizouConta;
	
	public Banco(String agencia, String conta, Double saldo, String status, Boolean atualizouConta) {
		this.agencia = agencia;
		this.conta = conta;
		this.saldo = saldo;
		this.status = status;
		this.atualizouConta = atualizouConta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Boolean getAtualizouConta() {
		return atualizouConta;
	}

	public void setAtualizouConta(Boolean atualizouConta) {
		this.atualizouConta = atualizouConta;
	}
	
}
