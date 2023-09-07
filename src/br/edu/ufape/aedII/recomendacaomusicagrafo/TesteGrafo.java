package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.io.File;
import java.io.FileInputStream;
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
                    // Lógica para busca em largura
                    break;
                case 4:
                    // Lógica para busca em profundidade
                    break;
                case 5:
                    // Lógica para remover vértice
                    break;
                case 6:
                    // Lógica para remover aresta
                    break;
                case 7:
                    // Lógica para ranking de música
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

        /* Teste BuscaLargura:

        BuscaLarguraMusica recomendacaoBFS = new BuscaLarguraMusica(grafo);

        MusicaVertice verticeInicial = vertice3; //Escolha um vértice de início 
        int quantidadeRecomendacoes = 0; //Defina o número de recomendações desejadas 

        List<MusicaVertice> recomendacoes = recomendacaoBFS.recomendarMusicas(verticeInicial, quantidadeRecomendacoes);

        System.out.println("Músicas recomendadas:");
        for (MusicaVertice musica : recomendacoes) {
            System.out.println("Título: " + musica.getTitulo() + ", Artista: " + musica.getArtista() + ", Gênero: " + musica.getGenero());
        } 
        
        */

        /* Teste BuscaProfundidade:
        MusicaVertice verticeInicial = vertice3;
        int quantidadeRecomendacoes = 0;

        // Realizar recomendação de músicas com DFS
        BuscaProfundidadeMusica recomendacaoDFS = new BuscaProfundidadeMusica(grafo);
        List<MusicaVertice> recomendacoes = recomendacaoDFS.recomendarMusicas(verticeInicial, quantidadeRecomendacoes);

        System.out.println("Músicas recomendadas:");
        for (MusicaVertice musica : recomendacoes) {
            System.out.println("Título: " + musica.getTitulo() + ", Artista: " + musica.getArtista() + ", Gênero: " + musica.getGenero());
        }
        */
    }
}

