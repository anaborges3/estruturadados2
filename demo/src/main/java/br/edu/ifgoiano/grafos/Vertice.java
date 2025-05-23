package br.edu.ifgoiano.grafos;

public class Vertice {
    private final String nome;

    public Vertice(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vertice)) return false;
        Vertice outro = (Vertice) obj;
        return nome.equals(outro.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    @Override
    public String toString() {
        return nome;
    }
}

