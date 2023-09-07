package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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
    public List<MusicaVertice> listarAdjacencias(int v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarAdjacencias'");
    }

    @Override
    public void imprimir() {
    
    }

    @Override
    public void removerVertice() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removerVertice'");
    }

    @Override
    public void removerAresta() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removerAresta'");
    }

    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    @Override
    public MusicaVertice getVerticeById(int id) {
        for (MusicaVertice vertice : vertices) {
            if (vertice.getId() == id) {
                return vertice;
            }
        }
        return null;
    }

    @Override
    public List<MusicaVertice> getRankingRecomendacaoMusica(MusicaVertice musica, int topRanking) {
        // Usar uma PriorityQueue para armazenar os vizinhos ordenados por peso
        PriorityQueue<MusicaVertice> ranking = new PriorityQueue<>(
            topRanking,
            new Comparator<MusicaVertice>() {
                public int compare(MusicaVertice v1, MusicaVertice v2) {
                    // Comparador personalizado para classificar pelo peso da aresta
                    double weight1 = getPeso(musica, v1);
                    double weight2 = getPeso(musica, v2);
                    return Double.compare(weight2, weight1); // Ordernar decrescentemente
                }
            }
        );

        for (MusicaVertice vizinho : musica.getVizinhos()) {
            if (!vizinho.equals(musica)) {
                ranking.add(vizinho);
                if (ranking.size() > topRanking) {
                    ranking.poll(); // Remover o nó com menor peso se exceder o limite
                }
            }
        }

        List<MusicaVertice> rankingVertices = new ArrayList<>(ranking);
        rankingVertices.sort(Comparator.comparingDouble(node -> getPeso(musica, (MusicaVertice) node)).reversed());
        return rankingVertices;
    }

    public double getPeso(MusicaVertice vertice1, MusicaVertice vertice2) {

        for (MusicaVertice vizinho : vertice1.getVizinhos()) {
            if (vizinho.equals(vertice2)) {
                return 0.0;
                //return getPesoAresta();
            }
        }
        return 0.0;
    }
    
}
