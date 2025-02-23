package br.edu.ifgoiano.recursividade;

public class Ex03 {
    // 3. Soma dos Elementos de um Array
    public static int somaArray(int[] array, int tamanho) {
        if (tamanho == 0) return 0;
        return array[tamanho - 1] + somaArray(array, tamanho - 1);
    }

    public static void main(String[] args) {
        System.out.println("Recursividade de um array de tamanho 5: " + somaArray(new int[]{1,2,3,4,5} , 5));
    }
}
