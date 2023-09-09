package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArvoreGeradoraMaxima {

    // Função para encontrar a aresta com maior peso
    static MusicaAresta encontrarArestaMaxPeso(List<MusicaAresta> arestas, boolean[] visitadas) {
        MusicaAresta arestaMaxPeso = null;
        double pesoMaximo = Double.NEGATIVE_INFINITY;

        for (MusicaAresta aresta : arestas) {
            MusicaVertice v1 = aresta.getMusica1();
            MusicaVertice v2 = aresta.getMusica2();

            if (!visitadas[v1.getId()] || !visitadas[v2.getId()]) {
                if (aresta.getPeso() > pesoMaximo) {
                    arestaMaxPeso = aresta;
                    System.out.println("ID DA Arestaaaa: " + aresta.getId() + " Pesoooo: " + aresta.getPeso());
                    pesoMaximo = aresta.getPeso();
                }
            }
        }

        // Verifique se uma aresta válida foi encontrada antes de retornar
        if (arestaMaxPeso == null) {
            throw new IllegalArgumentException("Não foi possível encontrar uma aresta válida.");
        }

        return arestaMaxPeso;
    }

    // Função para encontrar a Árvore Geradora Máxima
    public List<MusicaAresta> arvoreGeradoraMaxima(MusicaGrafoLista grafo) {
        List<MusicaAresta> arvoreGeradoraMaxima = new ArrayList<>();
        List<MusicaAresta> arestas = grafo.getArestas();

        Map<Integer, Integer> idToIndex = new HashMap<>();
        int numVertices = grafo.getNumVertices();
        boolean[] visitadas = new boolean[numVertices + 1]; // +1 para acomodar IDs começando em 1

        // Mapeia IDs de vértices para índices do array visitadas
        for (int i = 0; i < numVertices; i++) {
            idToIndex.put(grafo.getVertices().get(i).getId(), i);
        }

        // Inicializa os vértices como não visitados
        for (int i = 0; i < numVertices; i++) {
            visitadas[i] = false;
        }

        // Seleciona o primeiro vértice como ponto de partida
        // Encontre o menor ID entre os vértices
        int menorId = Integer.MAX_VALUE;
        for (MusicaVertice vertice : grafo.getVertices()) {
            if (vertice.getId() < menorId) {
                menorId = vertice.getId();
            }
        }

        // Inicializa os vértices como não visitados
        for (int i = 0; i < numVertices; i++) {
            visitadas[i] = false;
        }

        // Seleciona o primeiro vértice como ponto de partida
        visitadas[menorId] = true;

        // Itera até que todos os vértices estejam na Árvore Geradora Máxima
        for (int count = 1; count < numVertices; count++) {
            // Encontra a aresta com maior peso
            var arestaMaxPeso = encontrarArestaMaxPeso(arestas, visitadas);

            // Marca os vértices da aresta como visitados
            int indexV1 = idToIndex.get(arestaMaxPeso.getMusica1().getId());
            int indexV2 = idToIndex.get(arestaMaxPeso.getMusica2().getId());
            visitadas[indexV1] = true;
            visitadas[indexV2] = true;

            // Adiciona a aresta à Árvore Geradora Máxima
            arvoreGeradoraMaxima.add(arestaMaxPeso);
        }

        return arvoreGeradoraMaxima;
    }
}
