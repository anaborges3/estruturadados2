package br.edu.ifgoiano.grafo;

import org.junit.jupiter.api.Test;

import br.edu.ifgoiano.grafos.Grafo;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GrafoTest {

    @Test
    public void testAdicionarVerticeEAresta() {
        Grafo g = new Grafo();
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarAresta("A", "B", 1.0);
        assertEquals(1, g.grau("A"));
        assertEquals(1, g.grau("B"));
    }

    @Test
    public void testContarLacos() {
        Grafo g = new Grafo();
        g.adicionarVertice("A");
        g.adicionarAresta("A", "A", 2.5);
        assertEquals(1, g.contarLacos());
    }

    @Test
    public void testToDOTNaoDirecionado() {
        Grafo g = new Grafo(); // não direcionado
        g.adicionarVertice("X");
        g.adicionarVertice("Y");
        g.adicionarAresta("X", "Y", 4.2);
        String esperado = "graph G {\n  X -- Y [label=4.2];\n}";
        assertEquals(esperado, g.toDOT().trim());
    }

    @Test
    public void testGrauVertice() {
        Grafo g = new Grafo(); // não direcionado
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarVertice("C");
        g.adicionarAresta("A", "B", 1.0);
        g.adicionarAresta("A", "C", 1.0);
        assertEquals(2, g.grau("A")); // A está conectado a B e C
    }

    @Test
    public void testMenorCaminhoGrafoNaoDirecionado() {
        Grafo g = new Grafo(); // não direcionado
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarVertice("C");
        g.adicionarVertice("D");

        g.adicionarAresta("A", "B", 1);
        g.adicionarAresta("B", "C", 2);
        g.adicionarAresta("A", "D", 5);
        g.adicionarAresta("D", "C", 1);

        List<String> esperado = List.of("A", "B", "C");
        assertEquals(esperado, g.menorCaminho("A", "C"));
    }

    @Test
    public void testMenorCaminhoDigrafo() {
        Grafo g = new Grafo(true); // direcionado
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarVertice("C");

        g.adicionarAresta("A", "B", 3);
        g.adicionarAresta("B", "C", 2);

        List<String> esperado = List.of("A", "B", "C");
        assertEquals(esperado, g.menorCaminho("A", "C"));

        List<String> vazio = g.menorCaminho("C", "A");
        assertTrue(vazio.isEmpty());
    }

    @Test
    public void testToDOTComPesosEDirecao() {
        Grafo g = new Grafo(true);
        g.adicionarVertice("X");
        g.adicionarVertice("Y");
        g.adicionarAresta("X", "Y", 7.5);
        String esperado = "digraph G {\n  X -> Y [label=7.5];\n}";
        assertEquals(esperado, g.toDOT().trim());
    }
}
