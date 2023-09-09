package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.util.List;

public interface MusicaGrafo {
    void adicionarVertice(MusicaVertice v);

	int getIndiceVertice(MusicaVertice v);

	void adicionarAresta(int aresta_id, MusicaVertice a1, MusicaVertice a2, double peso);

	void imprimirLista();

	List<MusicaVertice> listarAdjacencias(MusicaVertice v);

	void imprimir();
	
	void removerVertice(int id);

	void removerAresta(int id);

	int getNumVertices();

	int getNumArestas();
	
	MusicaVertice getVerticeById (int id);

	MusicaAresta getArestaById(int id);

	MusicaAresta getArestaByVertices (MusicaVertice v1, MusicaVertice v2);

	List<MusicaVertice> getRankingRecomendacaoMusica(MusicaVertice musica, int topRanking);
}
