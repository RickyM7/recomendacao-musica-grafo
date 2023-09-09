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

    public List<MusicaVertice> getVertices() {
        return vertices;
    }

    public void setVertices(List<MusicaVertice> vertices) {
        this.vertices = vertices;
    }

    public List<MusicaAresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<MusicaAresta> arestas) {
        this.arestas = arestas;
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
    public void adicionarAresta(int aresta_id, MusicaVertice a1, MusicaVertice a2, double peso) {
       MusicaAresta a = new MusicaAresta(aresta_id, a1, a2, peso);
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
    public List<MusicaVertice> listarAdjacencias(MusicaVertice v) {
        List<MusicaVertice> adjacencias = new ArrayList<>();

        // Percorra a lista de arestas e adicione os vértices adjacentes a 'v' em 'adjacencias'
        for (MusicaAresta aresta : arestas) {
            if (aresta.getMusica1().equals(v)) {
                adjacencias.add(aresta.getMusica2());
            } else if (aresta.getMusica2().equals(v)) {
                adjacencias.add(aresta.getMusica1());
            }
        }
        return adjacencias;
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
    public int getNumArestas() {
        return arestas.size();
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
    public MusicaAresta getArestaById(int id) {
        for (MusicaAresta aresta : arestas) {
            if (aresta.getId() == id) {
                return aresta;
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
                MusicaAresta aresta = getArestaByVertices(vertice1, vertice2);
                return aresta.getPeso();
            }
        }
        return 0.0;
    }

    @Override
    public MusicaAresta getArestaByVertices(MusicaVertice v1, MusicaVertice v2) {
        for (MusicaAresta aresta : arestas) {
            if (aresta.getMusica1() == v1 && aresta.getMusica2() == v2) {
                return aresta;
            }
        }
        return null;
    }
}
