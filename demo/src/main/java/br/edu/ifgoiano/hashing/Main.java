package br.edu.ifgoiano.hashing;

import java.util.Scanner;

import br.edu.ifgoiano.hashing.model.HashTable;
import br.edu.ifgoiano.hashing.service.HashList;
import br.edu.ifgoiano.hashing.service.IHashing;

/*
 * @author: Ana Carolina Silva Borges; Danielly Maria dos Santos Oliveira; Stephany Lima Vital
 * @date: 2025-04-13
 * @description: implementação do arquivo executável do projeto de Hashing. Solicitado no Ex 4 do trabalho de Estruturas de Dados II.
 * @version: 1.0
 */

public class Main {

    private static final int TABLE_SIZE = 26;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        IHashing<String> hashingService = new HashList<>();
        HashTable<String> hashTable = hashingService.createHashTable(TABLE_SIZE);

        int option;

        do {
            System.out.println("\nMENU:");
            System.out.println("1 - Inserir (apenas o nome)");
            System.out.println("2 - Consultar todas as pessoas");
            System.out.println("3 - Consultar uma pessoa");
            System.out.println("4 - Consultar as pessoas com uma inicial digitada");
            System.out.println("5 - Excluir uma pessoa");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            option = Integer.parseInt(scanner.nextLine().trim());

            switch (option) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String nomeInserir = scanner.nextLine().trim();
                    hashingService.insert(hashTable, nomeInserir);
                    break;
                case 2:
                    System.out.println(hashingService.toString(hashTable));
                    break;
                case 3:
                    System.out.print("Digite o nome: ");
                    String nomeBuscar = scanner.nextLine().trim();
                    String encontrado = hashingService.find(hashTable, nomeBuscar);
                    System.out.println(encontrado != null ? "Encontrado: " + encontrado : "Pessoa não encontrada.");
                    break;
                case 4:
                    System.out.print("Digite a inicial: ");
                    char inicial = scanner.nextLine().trim().toLowerCase().charAt(0);
                    if (inicial >= 'a' && inicial <= 'z') {
                        java.util.List<String> matchingNames = hashTable.getItems().stream()
                                .filter(node -> node != null && node.getValue() != null
                                        && node.getValue().toLowerCase().charAt(0) == inicial)
                                .map(node -> node.getValue())
                                .collect(java.util.stream.Collectors.toList());
                        if (!matchingNames.isEmpty()) {
                            System.out.println("Pessoas encontradas com a inicial '" + inicial + "':");
                            matchingNames.forEach(System.out::println);
                        } else {
                            System.out.println("Nenhuma pessoa encontrada com essa inicial.");
                        }
                    } else {
                        System.out.println("Inicial inválida.");
                    }
                    break;
                case 5:
                    System.out.print("Digite o nome a ser removido: ");
                    String nomeRemover = scanner.nextLine().trim();
                    boolean removed = hashingService.remove(hashTable, nomeRemover);
                    System.out
                            .println(removed ? "Pessoa removida com sucesso." : "Pessoa não encontrada para remoção.");
                    break;
                case 6:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (option != 6);
    }
}