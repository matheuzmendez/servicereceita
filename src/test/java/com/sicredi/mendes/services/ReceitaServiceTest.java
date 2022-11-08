package com.sicredi.mendes.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ReceitaServiceTest {

	String agenciaCerta = "0101";
	String agenciaErrada = "010";
	String conta = "12225-6";
	double saldo = 100.00;

	@Test
	void contaDeveSerAtualizada() throws NumberFormatException, RuntimeException, InterruptedException {
		ReceitaService receitaService = new ReceitaService();
		Boolean atualizouConta = receitaService.atualizarConta(agenciaCerta, conta.replaceAll("-", ""), saldo, "P");
		assertTrue(atualizouConta);
	}

	@Test
	void agenciaErrada() throws NumberFormatException, RuntimeException, InterruptedException {
		ReceitaService receitaService = new ReceitaService();
		Boolean atualizouConta = receitaService.atualizarConta(agenciaErrada, conta.replaceAll("-", ""), saldo, "I");
		assertFalse(atualizouConta);
	}

	@Test
	void contaErrada() throws NumberFormatException, RuntimeException, InterruptedException {
		ReceitaService receitaService = new ReceitaService();
		Boolean atualizouConta = receitaService.atualizarConta(agenciaErrada, conta, saldo, "A");
		assertFalse(atualizouConta);
	}

	@Test
	void statusErrado() throws NumberFormatException, RuntimeException, InterruptedException {
		ReceitaService receitaService = new ReceitaService();
		Boolean atualizouConta = receitaService.atualizarConta(agenciaErrada, conta.replaceAll("-", ""), saldo, "C");
		assertFalse(atualizouConta);
	}
}
