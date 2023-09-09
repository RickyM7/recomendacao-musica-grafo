package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.util.*;

public class MenorDistancia {
    // Algoritmo de Dijkstra para encontar distancias minimas
    public static Map<MusicaVertice, Double> encontrarCaminhoMaisCurto(MusicaGrafoLista grafo, MusicaVertice origem) {
        Map<MusicaVertice, Double> distancias = new HashMap<>();
        PriorityQueue<MusicaVertice> filaPrioridade = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));
        
        // Inicializa todas as distâncias como infinito, exceto a origem como 0
        for (MusicaVertice vertice : grafo.getVertices()) {
            distancias.put(vertice, Double.POSITIVE_INFINITY);
        }
        distancias.put(origem, 0.0);
        
        filaPrioridade.add(origem);
        
        while (!filaPrioridade.isEmpty()) {
            MusicaVertice atual = filaPrioridade.poll();
            
            for (MusicaVertice vizinho : grafo.listarAdjacencias(atual)) {
                double pesoAresta = grafo.getPeso(atual, vizinho);
                double distanciaNova = distancias.get(atual) + pesoAresta;
                
                if (distanciaNova < distancias.get(vizinho)) {
                    distancias.put(vizinho, distanciaNova);
                    filaPrioridade.add(vizinho);
                }
            }
        }
        
        return distancias;
    }

    public static void imprimirDistancias(MusicaGrafoLista grafo, Map<MusicaVertice, Double> distancias) {
        for (Map.Entry<MusicaVertice, Double> entry : distancias.entrySet()) {
            MusicaVertice vertice = entry.getKey();
            double distancia = entry.getValue();
            
            grafo.imprimirVertice(vertice);
            System.out.println("Distancia: " + distancia + "\n");
        }
    }
}
