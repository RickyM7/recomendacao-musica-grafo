package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BuscaProfundidadeMusica {
    private MusicaGrafo grafo;

    public BuscaProfundidadeMusica(MusicaGrafo grafo) {
        this.grafo = grafo;
    }

    public List<MusicaVertice> recomendarMusicas(MusicaVertice inicio, int quantidadeRecomendacoes) {
        List<MusicaVertice> recomendacoes = new ArrayList<>();
        boolean[] visitado = new boolean[grafo.getNumVertices()];
        Stack<MusicaVertice> pilha = new Stack<>();

        pilha.push(inicio);

        while (!pilha.isEmpty() && recomendacoes.size() < quantidadeRecomendacoes) {
            MusicaVertice atual = pilha.pop();
            if (!visitado[grafo.getIndiceVertice(atual)]) {
                visitado[grafo.getIndiceVertice(atual)] = true;
                recomendacoes.add(atual);

                List<MusicaVertice> vizinhos = grafo.listarAdjacencias(grafo.getIndiceVertice(atual));
                for (MusicaVertice vizinho : vizinhos) {
                    pilha.push(vizinho);
                }
            }
        }

        return recomendacoes;
    }
}