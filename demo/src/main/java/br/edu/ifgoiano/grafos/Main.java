package br.edu.ifgoiano.grafos;

public class Main {
    public static void main(String[] args) {
        Grafo g = new Grafo(true); // true = direcionado (dígrafo)
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarVertice("C");
        g.adicionarAresta("A", "B", 2);
        g.adicionarAresta("B", "C", 3);
        g.adicionarAresta("A", "C", 10);

        System.out.println("Menor caminho A -> C: " + g.menorCaminho("A", "C"));
        System.out.println("Formato DOT para edotor.net:\n" + g.toDOT());
    }
}

