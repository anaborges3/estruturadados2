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
        g.adicionarAresta("A", "B");
        assertEquals(1, g.grau("A"));
        assertEquals(1, g.grau("B"));
    }

    @Test
    public void testContarLacos() {
        Grafo g = new Grafo();
        g.adicionarVertice("A");
        g.adicionarAresta("A", "A");
        assertEquals(1, g.contarLacos());
    }

    @Test
    public void testEhCompleto() {
        Grafo g = new Grafo();
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarVertice("C");
        g.adicionarAresta("A", "B");
        g.adicionarAresta("A", "C");
        g.adicionarAresta("B", "C");
        assertTrue(g.ehCompleto());
    }

    @Test
    public void testCaminho() {
        Grafo g = new Grafo();
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarVertice("C");
        g.adicionarAresta("A", "B");
        g.adicionarAresta("B", "C");
        List<String> esperado = List.of("A", "B", "C");
        assertEquals(esperado, g.caminho("A", "C"));
    }

    @Test
    public void testToDOT() {
        Grafo g = new Grafo();
        g.adicionarVertice("X");
        g.adicionarVertice("Y");
        g.adicionarAresta("X", "Y");
        String esperado = "graph G {\n  X -- Y;\n}";
        assertEquals(esperado, g.toDOT().trim());
    }

    @Test
    public void testGrauVertice() {
        Grafo g = new Grafo();
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarVertice("C");
        g.adicionarAresta("A", "B");
        g.adicionarAresta("A", "C");
        assertEquals(2, g.grau("A")); // A está conectado a B e C
    }

}
