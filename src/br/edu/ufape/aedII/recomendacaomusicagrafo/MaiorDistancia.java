package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.util.*;

public class MaiorDistancia {
    // Algoritmo de Dijkstra para encontrar as maiores distâncias
    public static Map<MusicaVertice, Double> encontrarCaminhoMaisLongo(MusicaGrafoLista grafo, MusicaVertice origem) {
        Map<MusicaVertice, Double> distancias = new HashMap<>();
        
        // Inicializa todas as distâncias como menos infinito (ou seja, valores negativos), exceto a origem como 0
        for (MusicaVertice vertice : grafo.getVertices()) {
            distancias.put(vertice, Double.NEGATIVE_INFINITY);
        }
        distancias.put(origem, 0.0);
        
        boolean[] visitados = new boolean[grafo.getNumVertices()];
        
        for (int i = 0; i < grafo.getNumVertices() - 1; i++) {
            MusicaVertice atual = obterVerticeNaoVisitadoComMaiorDistancia(grafo, distancias, visitados);
            
            if (atual == null) {
                break;
            }
            
            visitados[grafo.getIndiceVertice(atual)] = true;
            
            for (MusicaVertice vizinho : grafo.listarAdjacencias(atual)) {
                if (!visitados[grafo.getIndiceVertice(vizinho)]) {
                    double pesoAresta = grafo.getPeso(atual, vizinho);
                    double distanciaNova = distancias.get(atual) + pesoAresta;
                    
                    if (distanciaNova > distancias.get(vizinho)) {
                        distancias.put(vizinho, distanciaNova);
                    }
                }
            }
        }
        
        return distancias;
    }

    public static void imprimirDistancias(MusicaGrafoLista grafo, Map<MusicaVertice, Double> distancias, int idorigem) {
        // Converta o mapa em uma lista para classificar as distâncias
        List<Map.Entry<MusicaVertice, Double>> listaDistancias = new ArrayList<>(distancias.entrySet());
        
        // Classifique a lista com base nas distâncias (em ordem decrescente)
        listaDistancias.sort((entry1, entry2) -> -entry1.getValue().compareTo(entry2.getValue()));
        
        System.out.printf("Maiores Distâncias da origem(V%d) em ordem decrescente:\n", idorigem + 1);
        for (Map.Entry<MusicaVertice, Double> entry : listaDistancias) {
            MusicaVertice vertice = entry.getKey();
            double distancia = entry.getValue();
            
            if (distancia == Double.NEGATIVE_INFINITY) {
                System.out.printf("[Distância de V%d a V%d: \u221E] - ", (idorigem + 1), vertice.getId());
            } else {
                System.out.printf("[Distância de V%d a V%d: %.1f] - ", (idorigem + 1), vertice.getId(), distancia);
            }
            grafo.imprimirVertice(vertice);
        }
    }
    
    private static MusicaVertice obterVerticeNaoVisitadoComMaiorDistancia(MusicaGrafoLista grafo, Map<MusicaVertice, Double> distancias, boolean[] visitados) {
        double maiorDistancia = Double.NEGATIVE_INFINITY;
        MusicaVertice verticeComMaiorDistancia = null;
        
        for (MusicaVertice vertice : grafo.getVertices()) {
            if (!visitados[grafo.getIndiceVertice(vertice)] && distancias.get(vertice) > maiorDistancia) {
                maiorDistancia = distancias.get(vertice);
                verticeComMaiorDistancia = vertice;
            }
        }
        
        return verticeComMaiorDistancia;
    }
}
