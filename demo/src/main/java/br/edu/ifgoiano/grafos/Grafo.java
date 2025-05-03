package br.edu.ifgoiano.grafos;

import java.util.*;

public class Grafo {
    private final Map<Vertice, Set<Vertice>> adjacencias = new HashMap<>();

    public void adicionarVertice(String nome) {
        adjacencias.putIfAbsent(new Vertice(nome), new HashSet<>());
    }

    public void adicionarAresta(String origem, String destino) {
        Vertice v1 = new Vertice(origem);
        Vertice v2 = new Vertice(destino);
        adjacencias.get(v1).add(v2);
        adjacencias.get(v2).add(v1); // bidirecional
    }

    public int contarLacos() {
        int laços = 0;
        for (Map.Entry<Vertice, Set<Vertice>> entrada : adjacencias.entrySet()) {
            if (entrada.getValue().contains(entrada.getKey())) {
                laços++;
            }
        }
        return laços;
    }    

    public boolean ehCompleto() {
        int n = adjacencias.size();
        for (Map.Entry<Vertice, Set<Vertice>> entrada : adjacencias.entrySet()) {
            if (entrada.getValue().size() != n - 1) {
                return false;
            }
        }
        return true;
    }    

    public int grau(String nome) {
        Vertice v = new Vertice(nome);
        return adjacencias.getOrDefault(v, Collections.emptySet()).size();
    }

    public List<String> caminho(String origem, String destino) {
        Vertice start = new Vertice(origem), end = new Vertice(destino);
        Map<Vertice, Vertice> anterior = new HashMap<>();
        Queue<Vertice> fila = new LinkedList<>();
        Set<Vertice> visitado = new HashSet<>();
        fila.add(start);
        visitado.add(start);

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();
            if (atual.equals(end)) break;
            for (Vertice vizinho : adjacencias.getOrDefault(atual, new HashSet<>())) {
                if (!visitado.contains(vizinho)) {
                    fila.add(vizinho);
                    visitado.add(vizinho);
                    anterior.put(vizinho, atual);
                }
            }
        }

        List<String> caminho = new LinkedList<>();
        for (Vertice v = end; v != null; v = anterior.get(v)) {
            caminho.add(0, v.getNome());
        }
        return caminho.size() > 1 ? caminho : Collections.emptyList();
    }

    public String toDOT() {
        StringBuilder sb = new StringBuilder("graph G {\n");
        Set<String> adicionadas = new HashSet<>();
        for (Map.Entry<Vertice, Set<Vertice>> entrada : adjacencias.entrySet()) {
            for (Vertice vizinho : entrada.getValue()) {
                String aresta = entrada.getKey() + " -- " + vizinho;
                String reversa = vizinho + " -- " + entrada.getKey();
                if (!adicionadas.contains(aresta) && !adicionadas.contains(reversa)) {
                    sb.append("  ").append(aresta).append(";\n");
                    adicionadas.add(aresta);
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }    
}
