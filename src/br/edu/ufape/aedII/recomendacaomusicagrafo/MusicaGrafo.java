package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.util.List;

public interface MusicaGrafo {
    void adicionarVertice(MusicaVertice v);

	int getIndiceVertice(MusicaVertice v);

	void adicionarAresta(MusicaVertice a1, MusicaVertice a2, double peso);

	void imprimirLista();

	List<MusicaVertice> listarAdjacencias(int v);

	void imprimir();
	
	void removerVertice();

	void removerAresta();

	int getNumVertices();
	
	MusicaVertice getVerticeById (int id);
}
