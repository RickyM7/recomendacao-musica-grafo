package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class TesteGrafo {
    public static void main(String[] args) throws Exception {
        String localArquivo = "teste.txt";
        File arquivo = new File(localArquivo);

        String titulo, artista, genero;
        MusicaGrafo grafo = new MusicaGrafoLista();

        int v1, v2, vertice_id;
        double peso;
        MusicaVertice vertic1, vertic2;

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
            grafo.imprimirLista();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
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

