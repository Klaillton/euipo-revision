package com.euipo.linkedhashsethashmap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LinkedhashsethashmapApplication {

	// Crie um método que recebe List<String> e remove duplicatas mantendo ordem de
	// inserção
	// Use LinkedHashSet ou LinkedHashMap

	private static final Logger logger = LoggerFactory.getLogger(LinkedhashsethashmapApplication.class);
	private static final String LISTA_VAZIA_MSG = "A lista está vazia. Não há nomes para remover duplicatas.";

	private final List<String> lista = new ArrayList<>();

	public boolean nomeExiste(final String nome) {
		return lista.contains(nome);
	}

	public void adicionarNome(final String nome) {
		lista.add(nome);
	}

	public List<String> exibirNomes() {
		if (lista.isEmpty()) {
			logger.info("A lista está vazia.");
		} else {
			logger.info("Nomes na lista:");
			for (String nome : lista) {
				logger.info(nome);
			}
		}
		return lista;
	}

	public Set<String> removerDuplicatasHashSet(List<String> lista) {
		if (exibirNomes().isEmpty()) {
			logger.info(LISTA_VAZIA_MSG);
			return new LinkedHashSet<>();
		} else {
			return new LinkedHashSet<>(lista);
		}
	}

	public Map<String, Integer> removerDuplicatasHashMap(List<String> lista) {
		LinkedHashMap<String, Integer> mapa = new LinkedHashMap<>();
		if (exibirNomes().isEmpty()) {
			logger.info(LISTA_VAZIA_MSG);
			return mapa;
		} else {
			for (String nome : lista) {
				mapa.put(nome, mapa.getOrDefault(nome, 0) + 1);
			}
			return mapa;
		}
	}

	public List<String> removerDuplicatasStream(List<String> lista) {
		if (exibirNomes().isEmpty()) {
			logger.info(LISTA_VAZIA_MSG);
			return new ArrayList<>();
		} else {
			return lista.stream().distinct().toList();
		}
	}

	public List<String> removerDuplicatasStreamOrdenadas(List<String> lista) {
		if (exibirNomes().isEmpty()) {
			logger.info(LISTA_VAZIA_MSG);
			return new ArrayList<>();
		} else {
			return lista.stream().distinct().sorted().toList();
		}
	}

	public void todasAsOperacoes(List<String> lista) {
		long startTimeLinkedHashSet = System.currentTimeMillis();
		logger.info("Removendo duplicatas usando LinkedHashSet: " + removerDuplicatasHashSet(lista));
		long endTimeLinkedHashSet = System.currentTimeMillis();
		logger.info("Tempo de execução (LinkedHashSet): " + (endTimeLinkedHashSet - startTimeLinkedHashSet) + " ms");

		long startTimeHashMap = System.currentTimeMillis();
		logger.info("Contagem de nomes usando LinkedHashMap: " + removerDuplicatasHashMap(lista));
		long endTimeHashMap = System.currentTimeMillis();
		logger.info("Tempo de execução (LinkedHashMap): " + (endTimeHashMap - startTimeHashMap) + " ms");

		long startTimeStream = System.currentTimeMillis();
		logger.info("Removendo duplicatas usando Stream: " + removerDuplicatasStream(lista));
		long endTimeStream = System.currentTimeMillis();
		logger.info("Tempo de execução (Stream): " + (endTimeStream - startTimeStream) + " ms");

		long startTimeStreamOrdenadas = System.currentTimeMillis();
		logger.info("Removendo duplicatas ordenadas: " + removerDuplicatasStreamOrdenadas(lista));
		long endTimeStreamOrdenadas = System.currentTimeMillis();
		logger.info("Tempo de execução (Stream Ordenadas): " + (endTimeStreamOrdenadas - startTimeStreamOrdenadas) + " ms");
	}

	public static void main(String[] args) {
		SpringApplication.run(LinkedhashsethashmapApplication.class, args);

		LinkedhashsethashmapApplication app = new LinkedhashsethashmapApplication();
            try (Scanner scanner = new Scanner(System.in)) {
                int opt;
                do {
                    logger.info("\n=== MENU ===");
                    logger.info("1. Adicionar nome");
                    logger.info("2. Exibir nomes");
                    logger.info("3. Remover duplicatas (LinkedHashSet)");
                    logger.info("4. Remover duplicatas (LinkedHashMap)");
                    logger.info("5. Remover duplicatas (Stream)");
                    logger.info("6. Remover duplicatas ordenadas (Stream)");
					logger.info("7. Todas as operações");
                    logger.info("0. Sair");
                    logger.info("Escolha: ");
                    
                    opt = scanner.hasNextInt() ? scanner.nextInt() : -1;
                    scanner.nextLine(); // consumir newline
                    
                    switch (opt) {
                        case 1 -> {
                            logger.info("Digite um nome: ");
                            String nome = scanner.nextLine();
                            app.adicionarNome(nome);
                        }
                        case 2 -> app.exibirNomes();
                        case 3 -> logger.info("Únicos (LinkedHashSet): " + app.removerDuplicatasHashSet(app.exibirNomes()));
                        case 4 -> logger.info("Contagem (LinkedHashMap): " + app.removerDuplicatasHashMap(app.exibirNomes()));
                        case 5 -> logger.info("Únicos (Stream): " + app.removerDuplicatasStream(app.exibirNomes()));
                        case 6 -> logger.info("Únicos ordenados: " + app.removerDuplicatasStreamOrdenadas(app.exibirNomes()));
						case 7 -> app.todasAsOperacoes(app.exibirNomes());
                        case 0 -> logger.info("Saindo...");
                        default -> logger.info("Opção inválida!");
                    }
                } while (opt != 0);
            }
	}

}
