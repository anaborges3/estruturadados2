package br.edu.ifgoiano.grafos;

public class Main {
    public static void main(String[] args) {
        Grafo g = new Grafo();
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarVertice("C");
        g.adicionarAresta("A", "B");
        g.adicionarAresta("B", "C");
        g.adicionarAresta("C", "A");

        System.out.println("Laços: " + g.contarLacos());
        System.out.println("Grafo completo? " + g.ehCompleto());
        System.out.println("Grau de B: " + g.grau("B"));
        System.out.println("Caminho A -> C: " + g.caminho("A", "C"));
        System.out.println("Formato DOT:\n" + g.toDOT());
    }
}
