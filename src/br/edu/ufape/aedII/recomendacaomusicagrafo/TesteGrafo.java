package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TesteGrafo {
    public static void main(String[] args) throws Exception {
        // String localArquivo = "teste.txt";
        String localArquivo = "grafoteste.txt";
        File arquivo = new File(localArquivo);

        Scanner scanner = new Scanner(System.in);
        MusicaGrafoLista grafo = new MusicaGrafoLista();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Carregar Vértices e Arestas");
            System.out.println("2. Imprimir Vértices");
            System.out.println("3. Imprimir Arestas");
            System.out.println("4. Busca em Largura");
            System.out.println("5. Busca em Profundidade");
            System.out.println("6. Remover Vértice");
            System.out.println("7. Remover Aresta");
            System.out.println("8. Ranking de Música");
            System.out.println("9. Encontrar Caminho Mais Curto (Menor Peso)");
            System.out.println("10. Encontrar Caminho Mais Longo (Maior Peso)");
            System.out.println("11. Encontrar Menores Distâncias");
            System.out.println("99. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    grafo.carregarArquivo(grafo, arquivo);
                    break;
                case 2:
                    // Lógica para imprimir vértices
                    grafo.imprimirVertices();
                    break;
                case 3:
                    // Lógica para imprimir vértices
                    grafo.imprimirArestas();
                    break;
                case 4:
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
                    List<MusicaVertice> recomendacoes = recomendacaoBFS.recomendarMusicas(verticeInicial,
                            quantidadeRecomendacoes);
                    System.out.println("Músicas recomendadas usando BFS:");
                    for (MusicaVertice musica : recomendacoes) {
                        grafo.imprimirMusica(musica);
                    }
                    break;
                case 5:
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
                        grafo.imprimirMusica(musica);
                    }
                    break;
                case 6:
                    // Lógica para remover vértice
                    System.out.println("Digite o ID do vertice: ");
                    int idV = scanner.nextInt();
                    if (grafo.getVerticeById(idV) == null) {
                        System.out.println("Vertice (ID:" + idV + ") não existe!");
                    } else {
                        grafo.removerVertice(idV);
                        if (grafo.getVerticeById(idV) == null) {
                            System.out.println("Vertice (ID:" + idV + ") removido com sucesso!");
                        } else {
                            System.out.println("Falha ao remover Vertice (ID: " + idV + ")!");
                        }
                    }
                    break;
                case 7:
                    // Lógica para remover aresta
                    System.out.println("Digite o ID da aresta: ");
                    int idA = scanner.nextInt();
                    if (grafo.getArestaById(idA) == null) {
                        System.out.println("Aresta (ID:" + idA + ") não existe!");
                    } else {
                        grafo.removerAresta(idA);
                        if (grafo.getArestaById(idA) == null) {
                            System.out.println("Aresta (ID:" + idA + ") removida com sucesso!");
                        } else {
                            System.out.println("Falha ao remover Aresta (ID: " + idA + ")!");
                        }
                    }
                    break;
                case 8:
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
                        System.out.println((rankingVertices.indexOf(x) + 1) + "º ->");
                        grafo.imprimirMusica(x);
                    }
                    break;
                case 9:
                    // Lógica para encontrar o caminho mais curto (menor peso)
                    ArvoreGeradoraMinima agmin = new ArvoreGeradoraMinima();
                    List<MusicaAresta> arvoreGeradoraMinima = agmin.arvoreGeradoraMinima(grafo);

                    System.out.println("Árvore Geradora Mínima:");
                    for (MusicaAresta aresta : arvoreGeradoraMinima) {
                        grafo.imprimirAresta(aresta);
                    }

                    break;
                case 10:
                    // Lógica para encontrar o caminho mais longo (maior peso)
                    ArvoreGeradoraMaxima agmax = new ArvoreGeradoraMaxima();
                    List<MusicaAresta> arvoreGeradoraMaxima = agmax.arvoreGeradoraMaxima(grafo);

                    System.out.println("Árvore Geradora Máxima:");
                    for (MusicaAresta aresta : arvoreGeradoraMaxima) {
                        grafo.imprimirAresta(aresta);
                    }
                    break;
                case 11:
                    // Lógica para encontrar distâncias minimas
                    Map<MusicaVertice, Double> distancias = MenorDistancia.encontrarCaminhoMaisCurto(grafo, grafo.getVerticeById(1));
                    MenorDistancia.imprimirDistancias(grafo, distancias);
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
