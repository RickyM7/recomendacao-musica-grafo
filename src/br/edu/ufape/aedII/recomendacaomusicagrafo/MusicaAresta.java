package br.edu.ufape.aedII.recomendacaomusicagrafo;

public class MusicaAresta {
    private int id;
    private MusicaVertice musica1;
    private MusicaVertice musica2;
    private double peso;
    
    public MusicaAresta(int aresta_id, MusicaVertice musica1, MusicaVertice musica2, double peso) {
        this.id = aresta_id;
        this.musica1 = musica1;
        this.musica2 = musica2;
        this.peso = peso;
    }

    public MusicaVertice getMusica1() {
        return musica1;
    }

    public void setMusica1(MusicaVertice musica1) {
        this.musica1 = musica1;
    }

    public MusicaVertice getMusica2() {
        return musica2;
    }

    public void setMusica2(MusicaVertice musica2) {
        this.musica2 = musica2;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
