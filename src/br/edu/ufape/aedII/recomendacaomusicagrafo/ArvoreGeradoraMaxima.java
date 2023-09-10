package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArvoreGeradoraMaxima {

    //implementação do algoritmo de Kruskal, porém invertido para listar por maiores pesos
    public List<MusicaAresta> arvoreGeradoraMaxima(MusicaGrafoLista grafo) {
        List<MusicaAresta> arestasOrdenadas = new ArrayList<>(grafo.getArestas());
        Collections.sort(arestasOrdenadas, (a1, a2) -> Double.compare(a2.getPeso(), a1.getPeso())); // Invertido a2.getPeso() e a1.getPeso()
        
        List<MusicaAresta> arvoreMaxima = new ArrayList<>();
        AcharUniao acharUniao = new AcharUniao(grafo.getNumVertices()*2);

        for (MusicaAresta aresta : arestasOrdenadas) {

            MusicaVertice v1 = aresta.getMusica1();
            MusicaVertice v2 = aresta.getMusica2();
            if (v1 == null || v2 == null) {
                return arvoreMaxima;
            }

            int raizV1 = acharUniao.encontrar(v1.getId());
            int raizV2 = acharUniao.encontrar(v2.getId());

            if (raizV1 != raizV2) {
                arvoreMaxima.add(aresta);
                acharUniao.unir(raizV1, raizV2);
            }
        }

        return arvoreMaxima;
    }

    private static class AcharUniao {
        private final int[] pai;
        
        public AcharUniao(int n) {
            pai = new int[n+1];
            for (int i = 0; i < n; i++) {
                pai[i] = i;
            }
        }
        
        public int encontrar(int x) {
            if (pai[x] == x) {
                return x;
            }
            return pai[x] = encontrar(pai[x]);
        }
        
        public void unir(int x, int y) {
            int raizX = encontrar(x);
            int raizY = encontrar(y);
            if (raizX != raizY) {
                pai[raizX] = raizY;
            }
        }
    }
}
