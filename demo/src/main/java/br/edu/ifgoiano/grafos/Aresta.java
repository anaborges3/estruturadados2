package br.edu.ifgoiano.grafos;

public class Aresta {
    private final Vertice destino;
    private final double peso;

    public Aresta(Vertice destino, double peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }
}
