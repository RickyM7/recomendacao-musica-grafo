package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.util.ArrayList;
import java.util.List;

public class MusicaGrafoLista implements MusicaGrafo {

    private List<MusicaVertice> vertices;
    private List<MusicaAresta> arestas;

    public MusicaGrafoLista() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    @Override
    public void adicionarVertice(MusicaVertice v) {
        this.vertices.add(v);
    }

    @Override
    public int getIndiceVertice(MusicaVertice v) {
        return this.vertices.indexOf(v);
    }

    @Override
    public void adicionarAresta(MusicaVertice a1, MusicaVertice a2, double peso) {
       MusicaAresta a = new MusicaAresta(a1, a2, peso);
       this.arestas.add(a);
       a1.addVizinho(a2);
    }

    @Override
    public void imprimirLista() {
        for (MusicaVertice vertice : vertices) {
            System.out.println("{" + "Título: " + vertice.getTitulo() + ", Artist: " + vertice.getArtista() + ", Gênero: " + vertice.getGenero());
            System.out.print("Vizinhos: ");
            for (MusicaVertice vizinho : vertice.getVizinhos()) {
                System.out.print("[" + vizinho.getTitulo() + "] ");
            }
            System.out.println("}");
        }
    }

    @Override
    public List<Integer> listarAdjacencias(int v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarAdjacencias'");
    }

    @Override
    public void imprimir() {
    
    }
    
}
