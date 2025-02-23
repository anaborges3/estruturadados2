package br.edu.ifgoiano.recursividade;

public class Ex06 {
    public static String converterParaBinario(int n) {
        if (n == 0) return "0";
        if (n == 1) return "1";
        return converterParaBinario(n / 2) + (n % 2);
    }

    public static void main(String[] args) {
        System.out.println("Conversão de 10 para binário: " + converterParaBinario(10));
    }
}