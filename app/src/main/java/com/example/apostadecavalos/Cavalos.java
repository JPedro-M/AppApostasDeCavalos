package com.example.apostadecavalos;

public class Cavalos {

    private int id, qualidade, descanso , imagem;
    private String nome;

    public Cavalos(int id, int qualidade, int descanso, String nome, int img) {
        this.id = id;
        this.qualidade = qualidade;
        this.descanso = descanso;
        this.nome = nome;
        this.imagem = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQualidade() {
        return qualidade;
    }

    public void setQualidade(int qualidade) {
        this.qualidade = qualidade;
    }

    public int getDescanso() {
        return descanso;
    }

    public void setDescanso(int descanso) {
        this.descanso = descanso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "Cavalos{" +
                "id=" + id +
                ", qualidade=" + qualidade +
                ", descanso=" + descanso +
                ", nome='" + nome + '\'' +
                ", imagem='" + imagem + '\'' +
                '}';
    }
}
