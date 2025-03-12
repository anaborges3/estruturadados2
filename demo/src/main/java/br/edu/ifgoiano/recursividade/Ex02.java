package br.edu.ifgoiano.recursividade;

public class Ex02 {
    public static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println("Recursividade em Fibonacci para o número 7: " + fibonacci(7));
    }
}
