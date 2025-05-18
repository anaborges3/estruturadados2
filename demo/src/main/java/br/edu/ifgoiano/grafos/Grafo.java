package br.edu.ifgoiano.grafos;

import java.util.*;

public class Grafo {
    private final Map<Vertice, List<Aresta>> adjacencias = new HashMap<>();
    private final boolean direcionado;

    public Grafo() {
        this(false);
    }

    public Grafo(boolean direcionado) {
        this.direcionado = direcionado;
    }

    public void adicionarVertice(String nome) {
        adjacencias.putIfAbsent(new Vertice(nome), new ArrayList<>());
    }

    public void adicionarAresta(String origem, String destino, double peso) {
        Vertice v1 = new Vertice(origem);
        Vertice v2 = new Vertice(destino);
        adjacencias.get(v1).add(new Aresta(v2, peso));

        if (!direcionado && !v1.equals(v2)) {
            adjacencias.get(v2).add(new Aresta(v1, peso));
        }
    }

    // Dijkstra para menor caminho
    public List<String> menorCaminho(String origem, String destino) {
        Vertice inicio = new Vertice(origem);
        Vertice fim = new Vertice(destino);

        Map<Vertice, Double> distancias = new HashMap<>();
        Map<Vertice, Vertice> anteriores = new HashMap<>();
        PriorityQueue<Vertice> fila = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));

        for (Vertice v : adjacencias.keySet()) {
            distancias.put(v, Double.POSITIVE_INFINITY);
            anteriores.put(v, null);
        }
        distancias.put(inicio, 0.0);
        fila.add(inicio);

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();
            for (Aresta aresta : adjacencias.getOrDefault(atual, new ArrayList<>())) {
                Vertice vizinho = aresta.getDestino();
                double novaDist = distancias.get(atual) + aresta.getPeso();
                if (novaDist < distancias.get(vizinho)) {
                    distancias.put(vizinho, novaDist);
                    anteriores.put(vizinho, atual);
                    fila.add(vizinho);
                }
            }
        }

        // Reconstroi o caminho
        List<String> caminho = new LinkedList<>();
        for (Vertice v = fim; v != null; v = anteriores.get(v)) {
            caminho.add(0, v.getNome());
        }
        return caminho.size() > 1 || origem.equals(destino) ? caminho : Collections.emptyList();
    }

    public String toDOT() {
        StringBuilder sb = new StringBuilder(direcionado ? "digraph G {\n" : "graph G {\n");
        Set<String> adicionadas = new HashSet<>();

        for (Map.Entry<Vertice, List<Aresta>> entrada : adjacencias.entrySet()) {
            Vertice origem = entrada.getKey();
            for (Aresta aresta : entrada.getValue()) {
                Vertice destino = aresta.getDestino();
                String conector = direcionado ? " -> " : " -- ";
                String representacao = origem + conector + destino + " [label=" + aresta.getPeso() + "]";
                String reversa = destino + conector + origem + " [label=" + aresta.getPeso() + "]";
                if (direcionado || (!adicionadas.contains(reversa))) {
                    sb.append("  ").append(representacao).append(";\n");
                    adicionadas.add(representacao);
                }
            }
        }

        sb.append("}");
        return sb.toString();
    }

    public int grau(String nome) {
        Vertice v = new Vertice(nome);
        List<Aresta> arestas = adjacencias.get(v);
        return arestas != null ? arestas.size() : 0;
    }

    public int contarLacos() {
        int laços = 0;
        for (Map.Entry<Vertice, List<Aresta>> entrada : adjacencias.entrySet()) {
            Vertice v = entrada.getKey();
            for (Aresta a : entrada.getValue()) {
                if (a.getDestino().equals(v)) {
                    laços++;
                }
            }
        }
        return laços;
    }

}
