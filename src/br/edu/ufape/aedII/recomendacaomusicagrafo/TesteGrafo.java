package br.edu.ufape.aedII.recomendacaomusicagrafo;

public class TesteGrafo {
    public static void main(String[] args) throws Exception {
        MusicaGrafo grafo = new MusicaGrafoLista();

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

        grafo.imprimirLista();
    }
}

