package br.edu.ifgoiano.recursividade;

public class Ex05 {
    // 5. Contar ocorrência de um dígito
    public static int contarDigito(int n, int k) {
        if (n == 0) return 0;
        return (n % 10 == k ? 1 : 0) + contarDigito(n / 10, k);
    }

    public static void main(String[] args) {
        System.out.println("Dígito 2 ocorre em 762021192: " + contarDigito(762021192, 2) + " vezes");
    }
}
