package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class BuscaLarguraMusica {
    private MusicaGrafo grafo;

    public BuscaLarguraMusica(MusicaGrafo grafo) {
        this.grafo = grafo;
    }

    public List<MusicaVertice> recomendarMusicas(MusicaVertice inicio, int quantidadeRecomendacoes) {
        List<MusicaVertice> recomendacoes = new LinkedList<>();
        boolean[] visitado = new boolean[grafo.getNumVertices()];
        Queue<MusicaVertice> fila = new LinkedList<>();

        visitado[grafo.getIndiceVertice(inicio)] = true;
        fila.add(inicio);

        while (!fila.isEmpty() && recomendacoes.size() < quantidadeRecomendacoes) {
            MusicaVertice atual = fila.poll();
            recomendacoes.add(atual);

            List<MusicaVertice> vizinhos = grafo.listarAdjacencias(atual);
            for (MusicaVertice vizinho : vizinhos) {
                int indiceVizinho = grafo.getIndiceVertice(vizinho);
                if (!visitado[indiceVizinho]) {
                    visitado[indiceVizinho] = true;
                    fila.add(vizinho);
                }
            }
        }

        return recomendacoes;
    }
}
