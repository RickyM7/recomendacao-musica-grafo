package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

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
            System.out.println("{ TÍTULO: " + vertice.getTitulo().replaceAll("\\\\", " ") + ", ARTISTA: "
                    + vertice.getArtista().replaceAll("\\\\", " ") + ", GÊNERO: "
                    + vertice.getGenero().replaceAll("\\\\", " "));
            System.out.print("VIZINHOS: ");
            for (MusicaVertice vizinho : vertice.getVizinhos()) {
                System.out.print("[" + vizinho.getTitulo().replaceAll("\\\\", " ") + "] ");
            }
            System.out.println("}");
        }
    }

    @Override
    public List<MusicaVertice> listarAdjacencias(MusicaVertice v) {
        List<MusicaVertice> adjacencias = new ArrayList<>();

        // Percorra a lista de arestas e adicione os vértices adjacentes a 'v' em
        // 'adjacencias'
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
    public void imprimirVertice(MusicaVertice vertice) {
        System.out.print("ID da Vertice: " + vertice.getId() + " ");
        imprimirMusica(vertice);
    }

    @Override
    public void imprimirAresta(MusicaAresta aresta) {
        System.out.println("ID da Aresta: " + aresta.getId() +
                ", Peso: " + aresta.getPeso() +
                ", Vértices: " + aresta.getMusica1().getTitulo().replaceAll("\\\\", " ") + " (ID:" + aresta.getMusica1().getId() + ")" + " - "
                + aresta.getMusica2().getTitulo().replaceAll("\\\\", " ") + " (ID:" + aresta.getMusica2().getId() + ")");
    }

    @Override
    public void imprimirMusica(MusicaVertice musica) {
        System.out.println("TÍTULO: " + musica.getTitulo().replaceAll("\\\\", " ") + ", ARTISTA: "
                + musica.getArtista().replaceAll("\\\\", " ") + ", GÊNERO: "
                + musica.getGenero().replaceAll("\\\\", " "));
    }

    @Override
    public void removerVertice(int id) {
        MusicaVertice verticeParaRemover = null;

        // Encontre a aresta com o ID especificado
        for (MusicaVertice vertice : vertices) {
            if (vertice.getId() == id) {
                verticeParaRemover = vertice;
                break;
            }
        }

        // Remova a vertice da lista de arestas
        if (verticeParaRemover != null) {
            vertices.remove(verticeParaRemover);
        }
    }

    @Override
    public void removerAresta(int id) {
        MusicaAresta arestaParaRemover = null;

        // Encontre a aresta com o ID especificado
        for (MusicaAresta aresta : arestas) {
            if (aresta.getId() == id) {
                arestaParaRemover = aresta;
                break;
            }
        }

        // Remova a aresta da lista de arestas
        if (arestaParaRemover != null) {
            arestas.remove(arestaParaRemover);
        }
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
                });

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
    
    public void carregarArquivo(MusicaGrafoLista grafo, File arquivo) {
        try {
            FileInputStream inputStream = new FileInputStream(arquivo);
            Scanner s = new Scanner(inputStream);
            String inicio = s.next().toString();
    
            if (inicio.equals("#INICIO#")) {
                while (!s.hasNext("#FIM#")) {
                    s.findInLine("VERTICE:");
                    s.next();
                    s.findInLine("ID:");
                    int vertice_id = Integer.parseInt(s.next().replaceAll("[^0-9]", ""));
                    s.findInLine("TITULO:");
                    String titulo = s.next();
                    s.findInLine("ARTISTA:");
                    String artista = s.next();
                    s.findInLine("GENERO:");
                    String genero = s.next();
    
                    MusicaVertice vertice = new MusicaVertice(vertice_id, titulo, artista, genero);
                    grafo.adicionarVertice(vertice);
                }
            }
    
            s.nextLine();
            s.nextLine();
            String inicio_arestas = s.nextLine();
    
            if (inicio_arestas.equals("#INICIO#ARESTAS#")) {
                while (!s.hasNext("#FIM#ARESTAS#")) {
                    s.findInLine("ARESTA:");
                    s.next();
                    s.findInLine("ID:");
                    int aresta_id = Integer.parseInt(s.next().replaceAll("[^0-9]", ""));
                    s.findInLine("A1_ID:");
                    int v1 = Integer.parseInt(s.next().replaceAll("[^0-9]", ""));
                    s.findInLine("A2_ID:");
                    int v2 = Integer.parseInt(s.next().replaceAll("[^0-9]", ""));
                    s.findInLine("PESO:");
                    double peso = Double.parseDouble(s.next().replace(',', '.'));
    
                    MusicaVertice vertic1 = grafo.getVerticeById(v1);
                    MusicaVertice vertic2 = grafo.getVerticeById(v2);
                    grafo.adicionarAresta(aresta_id, vertic1, vertic2, peso);
                }
            }
    
            s.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar: " + e);
        }
    }
}