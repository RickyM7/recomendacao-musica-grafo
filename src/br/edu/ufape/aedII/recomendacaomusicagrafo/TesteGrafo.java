package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class TesteGrafo {
    public static void main(String[] args) throws Exception {
        String localArquivo = "";
        File arquivo = new File(localArquivo);

        String titulo, artista, genero;
        MusicaGrafo grafo = new MusicaGrafoLista();

        try {
            FileInputStream inputStream = new FileInputStream(arquivo);

            Scanner s = new Scanner(inputStream);

            String inicio = s.next().toString();
            String linha = "";
                        
            if (inicio.equals("#INICIO#")) {
                while (!s.hasNext("#FIM#")) {
                    linha = s.nextLine();
                    s.findInLine("VERTICE:");
                    s.findInLine("TITULO:");
                    titulo = s.next();
                    s.findInLine("ARTISTA:");
                    artista = s.next();
                    s.findInLine("GENERO:");
                    genero = s.next();

                    MusicaVertice vertice = new MusicaVertice(titulo, artista, genero);
                    grafo.adicionarVertice(vertice);
                }
            }

            s.close();
            inputStream.close();
            grafo.imprimirLista();

        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }

        // PENSAR NA INSERÇÃO DE ARESTAS
        MusicaVertice vertice1 = new MusicaVertice("Tudo Pra Amar Você", "Marina Sena", "Pop");
        MusicaVertice vertice2 = new MusicaVertice("Leave the Door Open", "Bruno Mars", "Pop");
        MusicaVertice vertice3 = new MusicaVertice("Baby 95", "Liniker", "Pop");
        MusicaVertice vertice4 = new MusicaVertice("Graveto", "Marília Mendonça", "Sertanejo");

        grafo.adicionarVertice(vertice1);
        grafo.adicionarVertice(vertice2);
        grafo.adicionarVertice(vertice3);
        grafo.adicionarVertice(vertice4);

        grafo.adicionarAresta(vertice3, vertice1, 0.8);
        grafo.adicionarAresta(vertice1, vertice2, 1.0);
        grafo.adicionarAresta(vertice2, vertice4, 0.1);
        grafo.adicionarAresta(vertice2, vertice3, 0.7);

        //grafo.imprimirLista();
    }
}

