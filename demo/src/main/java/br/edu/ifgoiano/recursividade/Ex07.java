package br.edu.ifgoiano.recursividade;

import java.io.File;

public class Ex07 {
    // 7. Buscar Arquivo Recursivamente com logs apenas para arquivos encontrados
    public static boolean buscarArquivo(String caminhoOrigem, String nomeArquivo) {
        File diretorio = new File(caminhoOrigem);
        if (!diretorio.exists() || !diretorio.isDirectory()) return false;
        
        File[] arquivos = diretorio.listFiles();
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile() && arquivo.getName().equals(nomeArquivo)) {
                    System.out.println("Absolute path: " + arquivo.getAbsolutePath());
                    System.out.println("Name: " + arquivo.getName());
                    System.out.println("Is directory: " + arquivo.isDirectory());
                    System.out.println("Is file: " + arquivo.isFile());
                    System.out.println("");
                    return true;
                } else if (arquivo.isDirectory()) {
                    if (buscarArquivo(arquivo.getAbsolutePath(), nomeArquivo)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println("Arquivo encontrado: " + buscarArquivo("./", "teste.txt"));
    }
}
