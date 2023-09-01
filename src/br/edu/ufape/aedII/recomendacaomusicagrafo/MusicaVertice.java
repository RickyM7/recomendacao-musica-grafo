package br.edu.ufape.aedII.recomendacaomusicagrafo;

import java.util.ArrayList;
import java.util.List;

public class MusicaVertice {
    private int id;
    private String titulo;
    private String artista;
    private String genero;
    private List<MusicaVertice> vizinhos;

    public MusicaVertice(String titulo, String artista, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.vizinhos = new ArrayList<>();
    }

    public void addVizinho(MusicaVertice vizinho){
        vizinhos.add(vizinho);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<MusicaVertice> getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(List<MusicaVertice> vizinhos) {
        this.vizinhos = vizinhos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
