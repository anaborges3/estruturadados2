package br.edu.ifgoiano.recursividade;

public class Ex04 {
    // 4. Inverter um Número
    public static int inverterNumero(int n, int reverso) {
        if (n == 0) return reverso;
        return inverterNumero(n / 10, reverso * 10 + n % 10);
    }

    public static int inverterNumero(int n) {
        return inverterNumero(n, 0);
    }

    public static void main(String[] args) {
        System.out.println("Inverter número 1234: " + inverterNumero(1234));
    }
}
