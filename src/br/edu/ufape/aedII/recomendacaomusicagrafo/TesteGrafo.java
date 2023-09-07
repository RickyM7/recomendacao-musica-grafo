package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;

public class TesteGrafo {
    public static void main(String[] args) throws Exception {
        String localArquivo = "teste.txt";
        File arquivo = new File(localArquivo);

        String titulo, artista, genero;

        int v1, v2, vertice_id;
        double peso;
        MusicaVertice vertic1, vertic2;

        Scanner scanner = new Scanner(System.in);
        MusicaGrafo grafo = new MusicaGrafoLista();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Carregar Vértices e Arestas");
            System.out.println("2. Imprimir Vértices");
            System.out.println("3. Busca em Largura");
            System.out.println("4. Busca em Profundidade");
            System.out.println("5. Remover Vértice");
            System.out.println("6. Remover Aresta");
            System.out.println("7. Ranking de Música");
            System.out.println("8. Encontrar Caminho Mais Curto");
            System.out.println("99. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    try {
                        FileInputStream inputStream = new FileInputStream(arquivo);

                        Scanner s = new Scanner(inputStream);
                        String inicio = s.next().toString();

                        if (inicio.equals("#INICIO#")) {
                            while (!s.hasNext("#FIM#")) {
                                s.findInLine("VERTICE:");
                                s.next();
                                s.findInLine("ID:");
                                vertice_id = Integer.parseInt(s.next());
                                s.findInLine("TITULO:");
                                titulo = s.next();
                                s.findInLine("ARTISTA:");
                                artista = s.next();
                                s.findInLine("GENERO:");
                                genero = s.next();
                            
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
                                s.findInLine("A1_ID:");
                                v1 = Integer.parseInt(s.next());
                                s.findInLine("A2_ID:");
                                v2 = Integer.parseInt(s.next());
                                s.findInLine("PESO:");
                                peso = s.nextDouble();
                            
                                vertic1 = grafo.getVerticeById(v1);
                                vertic2 = grafo.getVerticeById(v2);
                            
                                grafo.adicionarAresta(vertic1, vertic2, peso);
                            }
                        }
                    
                        s.close();
                        inputStream.close();
                    } catch (Exception e) {
                        System.out.println("Erro ao carregar: " + e);
                    }
                    break;
                case 2:
                    // Lógica para imprimir vértices
                    grafo.imprimirLista();
                    break;
                case 3:
                    BuscaLarguraMusica recomendacaoBFS = new BuscaLarguraMusica(grafo);
                    MusicaVertice verticeInicial = null;
                    int numVertices = grafo.getNumVertices();

                    if (numVertices > 0) {
                        // Escolha um ID aleatório entre os IDs dos vértices disponíveis
                        int indiceAleatorio = (int) (Math.random() * numVertices);
                        indiceAleatorio = Math.max(0, indiceAleatorio);

                        verticeInicial = grafo.getVerticeById(indiceAleatorio);
                    } else {
                        System.out.println("O grafo não possui vértices.");
                    }
 
                    int quantidadeRecomendacoes = 3;
                    List<MusicaVertice> recomendacoes = recomendacaoBFS.recomendarMusicas(verticeInicial, quantidadeRecomendacoes);
                    System.out.println("Músicas recomendadas usando BFS:");
                    for (MusicaVertice musica : recomendacoes) {
                        System.out.println("Título: " + musica.getTitulo() + ", Artista: " + musica.getArtista() + ", Gênero: " + musica.getGenero());
                    }
                    break;
                case 4:
                    BuscaProfundidadeMusica recomendacaoDFS = new BuscaProfundidadeMusica(grafo);
                    verticeInicial = null;
                    numVertices = grafo.getNumVertices();

                    if (numVertices > 0) {
                        // Escolha um ID aleatório entre os IDs dos vértices disponíveis
                        int indiceAleatorio = (int) (Math.random() * numVertices);
                        indiceAleatorio = Math.max(0, indiceAleatorio);

                        verticeInicial = grafo.getVerticeById(indiceAleatorio);
                    } else {
                        System.out.println("O grafo não possui vértices.");
                    }

                    quantidadeRecomendacoes = 3;
                    recomendacoes = recomendacaoDFS.recomendarMusicas(verticeInicial, quantidadeRecomendacoes);
                    System.out.println("Músicas recomendadas usando DFS:");
                    for (MusicaVertice musica : recomendacoes) {
                        System.out.println("Título: " + musica.getTitulo() + ", Artista: " + musica.getArtista() + ", Gênero: " + musica.getGenero());
                    }
                    break;
                case 5:
                    // Lógica para remover vértice
                    break;
                case 6:
                    // Lógica para remover aresta
                    break;
                case 7:
                    // Lógica para ranking de música
                    System.out.println("Digite o ID da música: ");
                    int id = scanner.nextInt();
                    System.out.println("Digite o tamanho do Ranking desejado: ");
                    int topRanking = scanner.nextInt();

                    MusicaVertice vertice = grafo.getVerticeById(id);
                    List<MusicaVertice> rankingVertices;
                    rankingVertices = grafo.getRankingRecomendacaoMusica(vertice, topRanking);

                    System.out.println("TOP " + topRanking + " Músicas Parecidas");
                    for (MusicaVertice x : rankingVertices) {
                        System.out.println ((rankingVertices.indexOf(x)+1) + "º ->");
                        System.out.println("Título: " + x.getTitulo());
                        System.out.println("Artista: " + x.getArtista());
                        System.out.println("Gênero: " + x.getGenero());
                    }
                    break;
                case 8:
                    // Lógica para encontrar caminho mais curto
                    break;
                case 99:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}

