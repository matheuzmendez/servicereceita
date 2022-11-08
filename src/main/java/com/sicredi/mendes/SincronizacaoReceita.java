package com.sicredi.mendes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sicredi.mendes.entities.Banco;
import com.sicredi.mendes.services.ReceitaService;

@SpringBootApplication
public class SincronizacaoReceita {

	public static void main(String[] args) throws ParseException {

		Boolean atualizouConta = false;
		File pathDir = new File(".");
		List<Banco> listBanco = new ArrayList<>();

		try (BufferedReader origem = new BufferedReader(new FileReader(new File(args[0])))) {

			String linhaCsv = origem.readLine();
			linhaCsv = origem.readLine();

			while (linhaCsv != null) {

				String[] fieldsCsv = linhaCsv.split(",");

				String agencia = fieldsCsv[0];
				String conta = fieldsCsv[1];
				double saldo = Double.parseDouble(fieldsCsv[2].concat(".").concat(fieldsCsv[3]).replaceAll("\"", ""));
				String status = fieldsCsv[4];

				try {
					ReceitaService receitaService = new ReceitaService();
					atualizouConta = receitaService.atualizarConta(agencia, conta.replaceAll("-", ""), saldo, status);
				} catch (Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}

				listBanco.add(new Banco(agencia, conta, saldo, status, atualizouConta));
				linhaCsv = origem.readLine();
			}
			
			origem.close();
			System.out.println("Arquivo Lido!");

			try (BufferedWriter escreveArquivo = new BufferedWriter(
					new FileWriter(pathDir.getCanonicalPath().concat("\\saida.csv")))) {

				escreveArquivo.write("agencia, conta, saldo, status, atualizou");
				escreveArquivo.newLine();

				for (Banco item : listBanco) {
					if (item.getAtualizouConta()) {
						escreveArquivo.write(item.getAgencia() + "," + item.getConta() + "," + item.getStatus() + ","
								+ "\"" + String.format("%.2f", item.getSaldo()) + "\"" + "," + "VERDADEIRO");
						escreveArquivo.newLine();
					} else {
						escreveArquivo.write(item.getAgencia() + "," + item.getConta() + "," + item.getStatus() + ","
								+ "\"" + String.format("%.2f", item.getSaldo()) + "\"" + "," + "FALSO");
						escreveArquivo.newLine();
					}
				}

				escreveArquivo.close();
				System.out.println("Arquivo Criado!");

			} catch (IOException e) {
				System.out.println("Erro durante a escrita do arquivo: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Erro durante a leitura do arquivo: " + e.getMessage());
		}

	}

}
